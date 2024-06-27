import time
import random
from collections import defaultdict
import nltk
import numpy as np
nltk.download('punkt')

# Estrutura da Cache
class TextCache:
    def __init__(self, tamanho_maximo=10):
        self.tamanho_maximo = tamanho_maximo
        self.cache = {}
        self.cache_order = []

    def put(self, id_texto, texto):
        if len(self.cache) >= self.tamanho_maximo:
            antigo_id_texto = self.cache_order.pop(0)
            del self.cache[antigo_id_texto]

        self.cache[id_texto] = texto
        self.cache_order.append(id_texto)

    def get(self, id_texto):
        if id_texto in self.cache:
            self.cache_order.remove(id_texto)
            self.cache_order.append(id_texto)
            return self.cache[id_texto]
        else:
            return None


# Algoritmos de Cache
# LRU (Least Recently Used)
class LRUCache(TextCache):
    def get(self, id_texto):
        texto = super().get(id_texto)
        if texto:
            self.cache_order.remove(id_texto)
            self.cache_order.append(id_texto)
        return texto

# FIFO (First In First Out)
class FIFOCache(TextCache):
    def put(self, id_texto, texto):
        if len(self.cache) >= self.tamanho_maximo:
            antigo_id_texto = self.cache_order.pop(0)
            del self.cache[antigo_id_texto]
        super().put(id_texto, texto)

# LFU (Least Frequently Used)
class LFUCache(TextCache):
    def __init__(self, tamanho_maximo=10):
        super().__init__(tamanho_maximo)
        self.frequency = {}

    def put(self, id_texto, texto):
        if len(self.cache) >= self.tamanho_maximo:
            recente_id_texto = min(self.frequency, key=self.frequency.get)
            del self.cache[recente_id_texto]
            del self.frequency[recente_id_texto]
            self.cache_order.remove(recente_id_texto)

        super().put(id_texto, texto)
        self.frequency[id_texto] = 0

    def get(self, id_texto):
        texto = super().get(id_texto)
        if texto:
            self.frequency[id_texto] += 1
        return texto


# Função para simular acesso à cache
def simulate_access(algoritmo_cache, tamanho_cache, num_requisicoes=200):
    # Variáveis para contar os hits e misses na cache e para calcular o tempo total
    contador_cache_hit = 0
    contador_cache_miss = 0
    tempo_total = 0

    # Inicializa a cache com o algoritmo selecionado
    cache = algoritmo_cache(tamanho_maximo=tamanho_cache)

    # Marca o tempo inicial da simulação
    tempo_inicial = time.time()

    # Loop sobre o número de requisições especificado
    for _ in range(num_requisicoes):
        # Gera IDs de texto para os diferentes cenários
        id_texto_simples, id_texto_poisson, id_texto_33_pct = escolher_texto()

        # Marca o tempo inicial da requisição atual
        tempo_incial_req = time.time()

        # Variável para armazenar o ID do texto que foi encontrado na cache (hit)
        texto_id_hit = None

        # Loop sobre os IDs de texto, tentando encontrar um na cache
        for id_texto in [id_texto_simples, id_texto_poisson, id_texto_33_pct]:
            texto = cache.get(id_texto)
            if texto is not None:
                contador_cache_hit += 1
                texto_id_hit = id_texto
                break

        # Se nenhum texto foi encontrado na cache (miss), seleciona aleatoriamente um dos IDs e adiciona na cache
        if texto_id_hit is None:
            contador_cache_miss += 1
            time.sleep(0.1) # Simula um acesso à fonte de dados
            texto_id_hit = random.choice([id_texto_simples, id_texto_poisson, id_texto_33_pct])
            texto = f'Texto {texto_id_hit}'
            cache.put(texto_id_hit, texto)

        # Marca o tempo final da requisição atual
        tempo_final_req = time.time()

        # Calcula o tempo total gasto nas requisições
        tempo_total += tempo_final_req - tempo_incial_req

    # Marca o tempo final da simulação
    tempo_final = time.time()

    # Calcula o tempo médio por requisição
    tempo_por_requisicao = tempo_total / num_requisicoes

    # Retorna os contadores de hits e misses, e o tempo médio por requisição
    return contador_cache_hit, contador_cache_miss, tempo_por_requisicao


# Função para escolher o ID do texto de acordo com os critérios especificados
def escolher_texto():
    id_texto_simples = random.randint(1, 100)
    id_texto_poisson = np.random.poisson(50)
    if random.random() < 0.33:
        id_texto_33_pct = random.randint(30, 40)
    else:
        id_texto_33_pct = random.randint(1, 100)
    return id_texto_simples, id_texto_poisson, id_texto_33_pct


# Função para gerar o relatório
def gerar_relatorio(algoritmo_cache, tamanho_cache):
    report = defaultdict(lambda: defaultdict(dict))
    usuarios = ['Usuário 1', 'Usuário 2', 'Usuário 3']
    for usuario in usuarios:
        for nome_algoritmo, algo_class in algoritmo_cache.items():
            contador_cache_hit, contador_cache_miss, tempo_por_requisicao = simulate_access(algo_class, tamanho_cache)
            report[usuario][nome_algoritmo]['contador_cache_hit'] = contador_cache_hit
            report[usuario][nome_algoritmo]['contador_cache_miss'] = contador_cache_miss
            report[usuario][nome_algoritmo]['tempo_por_requisicao'] = tempo_por_requisicao
    return report


# Função para ler de acordo com seu número
def ler_texto(id_texto):
    try:
        with open(f'output_{id_texto}.txt', 'r') as file:
            texto = file.read()
            print(texto)
    except FileNotFoundError:
        print("Texto não encontrado.")


# Função principal
def main():


    tamanho_cache = 10
    algoritmos_cache = {
        'LRU': LRUCache,
        'FIFO': FIFOCache,
        'LFU': LFUCache
    }

    while True:
        teste = input("Digite o número que identifica o texto desejado (ou -1 para entrar no modo de simulação, 0 para sair): ")
        try:
            id_texto = int(teste)
            if id_texto == 0:
                print("Encerrando o programa...")
                break
            elif id_texto == -1:
                print("Entrando no modo de simulação...")
                report = gerar_relatorio(algoritmos_cache, tamanho_cache)
                with open('Relatorio_simulacao.txt', 'w') as file:
                    for usuario, data in report.items():
                        file.write(f"{usuario}:\n")
                        for nome_algoritmo, algo_data in data.items():
                            file.write(f"Algoritmo de cache: {nome_algoritmo}\n")
                            file.write(f"Cache hit: {algo_data['contador_cache_hit']}\n")
                            file.write(f"Cache miss: {algo_data['contador_cache_miss']}\n")
                            file.write(f"Tempo médio por solicitação: {algo_data['tempo_por_requisicao']:.4f} segundos\n\n")
                print("Relatório de simulação gerado com sucesso.")
            else:
                ler_texto(id_texto)
        except ValueError:
            print("Por favor, digite um número inteiro válido.")


if __name__ == "__main__":
    main()
