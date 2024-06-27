##!/usr/bin/env python3

from sys import argv
from pika import BlockingConnection
from enum import Enum

if len(argv) < 2:
    print(f'USO: {argv[0]} <id> [<v1> <v2> ...]')
    exit(1)

idx = argv[1]  
Nx = argv[2:]  

Estado = Enum('Estado', 'INICIADOR OCIOSO VISITADO OK')
estado = Estado.OCIOSO
     
a_visitar = []
entrada = None
iniciador = False

print("idx =", idx)
print("Nx =", Nx)

def recebendo(msg, origem, canal):
    global estado, a_visitar, entrada, iniciador
    
    print(f'Mensagem "{msg}" recebida de {origem}')
    
    if msg == 'T':
        if estado == Estado.OCIOSO:
            entrada = origem
            a_visitar = [n for n in Nx if n != origem]
            iniciador = False
            busca(canal)
            return
        
        if estado == Estado.VISITADO:
            a_visitar = [n for n in a_visitar if n != origem]
            envia('B', [origem], canal)
            return

    if msg == 'R' or msg == 'B':
        busca(canal)

def envia(msg, dests, canal):
    for d in dests:
        print(f"Mensagem '{msg}' de {idx} enviada para {d}")
        canal.basic_publish(exchange='',
                            routing_key=d,
                            body=f"{idx}:{msg}")

def espontaneamente(msg, canal):
    global estado, a_visitar, iniciador
    print("Mensagem recebida do starter: Iniciador")
    a_visitar = Nx[:]
    iniciador = True
    busca(canal)


def busca(canal):
    global estado, a_visitar, iniciador
    
    if a_visitar:
        proximo_no = a_visitar.pop(0)
        estado = Estado.VISITADO
        envia('T', [proximo_no], canal)
        return
    
    estado = Estado.OK
    if not iniciador:
        envia('R', [entrada], canal)

conexao = BlockingConnection()
canal = conexao.channel()

canal.queue_declare(queue=idx, auto_delete=True)
for v in Nx:
    canal.queue_declare(queue=v, auto_delete=True)

def callback(ch, metodo, props, corpo):
    mensagem = corpo.decode().split(":")
    if len(mensagem) < 2:
        print(f"Mensagem sem origem adequada: '{mensagem}'")
    else:
        origem, msg = mensagem
        if origem == "STARTER":
            espontaneamente(msg, canal)
        else:
            recebendo(msg, origem, canal)

canal.basic_consume(queue=idx,
                    on_message_callback=callback,
                    auto_ack=True)

try:
    print(f"{idx} aguardando mensagens")
    canal.start_consuming()
except KeyboardInterrupt:
    canal.stop_consuming()

conexao.close()