from abc import abstractmethod
import sys


class IO:
    def __init__(self, entrada=sys.stdin, saida=sys.stdout):
        # saída-padrão = tela (stdout)
        self.entrada = entrada
        self.saida = saida

    def output(self, s):
        print(s, end='', file=self.saida)
    # Exceção (erro)


class EnderecoInvalido(Exception):
    def __init__(self, ender):
        self.ender = ender

    def __repr__(self):
        return self.ender


class Memoria:
    def __init__(self, capacidade):
        self._capacidade = capacidade

    def verifica_endereco(self, ender):
        if (ender < 0) or (ender >= self._capacidade):
            raise EnderecoInvalido(ender)

    def tamanho(self):
        return self._capacidade

    # métodos abstratos devem ser sobrescritos pelas subclasses
    @abstractmethod
    def read(self, ender): pass

    @abstractmethod
    def write(self, ender, val): pass


class RAM(Memoria):
    def __init__(self, k):
        Memoria.__init__(self, 2 ** k)
        self.memoria = [0] * self.tamanho()

    def read(self, ender):
        super().verifica_endereco(ender)
        return self.memoria[ender]

    def write(self, ender, val):
        super().verifica_endereco(ender)
        self.memoria[ender] = val


class Cache(Memoria):
    def __init__(self, capacidade, ram):
        super().__init__(ram.tamanho())
        self.ram = ram
        self.cache = [None] * self.tamanho()

    def read(self, ender):
        super().verifica_endereco(ender)
        if self.cache[ender] is None:
            print(f'Cache MISS: {ender}')
            self.cache[ender] = self.ram.read(ender)  # Se não estiver na cache, carrega da RAM
        else:
            print(f'Cache HIT: {ender}')
        return self.cache[ender]

    def write(self, ender, val):
        super().verifica_endereco(ender)
        self.cache[ender] = val
        self.ram.write(ender, val)  # Escreve na RAM também

class CPU:
    def __init__(self, mem, io):
        self.mem = mem
        self.io = io
        self.PC = 0
        self.A = self.B = self.C = 0

    def run(self, ender):
        self.PC = ender
        self.A = self.mem.read(self.PC)
        self.PC += 1
        self.B = self.mem.read(self.PC)
        self.PC += 1
        self.C = 1
        while self.A <= self.B:
            self.mem.write(self.A, self.C)
            self.io.output(f'> {self.A} = {self.C}\n')
            self.C += 1
            self.A += 1


def main():
    try:
        io = IO(sys.stdin, sys.stdout)
        ram = RAM(7)
        cache = Cache(128, ram)  # Ajustando a capacidade da Cache para a capacidade da RAM
        cpu = CPU(cache, io)

        inicio = 10
        ram.write(inicio, 118)
        ram.write(inicio + 1, 130)
        cpu.run(inicio)
    except EnderecoInvalido as e:
        print("Endereço inválido:", e.ender, file=sys.stderr)


if __name__ == '__main__':
    main()
