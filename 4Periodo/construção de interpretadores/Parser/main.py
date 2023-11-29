valores = 'abcdefghjklmnopqrstuvwxyz0123456789'
operadoresBinarios = ["→", "^", "v", "↔"]

def separaValoresEquacao(equacao):
    indice = 0
    novoIndice = 0
    atualizaNovo = False
    listaValores = []
    while indice != len(equacao):
        i = equacao[indice]
        if atualizaNovo:
            novoIndice = indice
            atualizaNovo = False
        if i == "(" and indice != 0:
            quantidadeParenteses = 0
            valorNovaEquacao = 0
            for a in equacao[novoIndice:]:
                if a == "(":
                    quantidadeParenteses = quantidadeParenteses + 1
                if a == ")":
                    if quantidadeParenteses > 1:
                        quantidadeParenteses = quantidadeParenteses - 1
                    else:
                        listaValores.append(equacao[novoIndice(novoIndice + valorNovaEquacao + 1)])
                        indice = indice + valorNovaEquacao + 2
                        atualizaNovo = True
                        break
                valorNovaEquacao = valorNovaEquacao + 1
        if i == "":
            listaValores.append(equacao[novoIndice, indice])
            atualizaNovo = True
        if indice == (len(equacao) - 1):
            listaValores.append(equacao[novoIndice])
        indice = indice + 1
    return listaValores


def validacaoProposicao(proposicao):
    for i in proposicao:
        if i != valores:
            return False
    else:
        return True

def validacaoConstantes(valorConstante):
    if valorConstante in "F":
        return True
    elif valorConstante in "T":
        return True
    else:
        return False


def formulaUnaria(equacao):
    caracterEquacao = separaValoresEquacao(equacao)
    if len(caracterEquacao) > 4:
        return False
    if caracterEquacao[0] != "(":
        return False
    if caracterEquacao[1] != "¬":
        return False
    if caracterEquacao[3] != ")":
        return False
    return validaFormula(caracterEquacao[2])


def formulaBinaria(equacao):
    caracterEquacao = separaValoresEquacao(equacao)
    if len(caracterEquacao) > 5 or len(caracterEquacao) < 5:
        return False
    if caracterEquacao[0] != "(":
        return False
    if caracterEquacao[1] not in operadoresBinarios:
        return False
    if caracterEquacao[4] != ")":
        return False
    return validaFormula(caracterEquacao[2]) and validaFormula(caracterEquacao[3])


def validaFormula(formula):
    if validacaoConstantes(formula):
        return validacaoConstantes(formula)
    if validacaoProposicao(formula):
        return validacaoProposicao(formula)
    if formula[0] == "(":
        if formula[2] == "¬":
            return formulaUnaria(formula)
        elif formula[2] in operadoresBinarios:
            return formulaBinaria(formula)
    else:
        return False


print('=-=-=-=ARUQIVO 1=-=-=-=')
arquivo1 = open('teste1.txt')
lerLinhas1 = arquivo1.readlines()
quantidadeLinhas = True

for indiceLinhas in lerLinhas1:
    equacao = indiceLinhas.strip()
    if quantidadeLinhas:
        print(int(equacao))
        quantidadeLinhas = False
    else:
        print(f"EQUAÇÃO -> {equacao} :" + f"{'VÁLIDO' if validaFormula(equacao) else 'INVÁLIDO.'}")

print('=-=-=-=ARQUIVO 2=-=-=-=')
arquivo2 = open('teste2.txt')
lerLinhas2 = arquivo2.readlines()
quantidadeLinhas = True

for indiceLinhas in lerLinhas2:
    equacao = indiceLinhas.strip()
    if quantidadeLinhas:
        print(int(equacao))
        quantidadeLinhas = False
    else:
        print(f"EQUAÇÃO -> {equacao} :" + f"{'VÁLIDO' if validaFormula(equacao) else 'INVÁLIDO.'}")

print('=-=-=-=ARQUIVO 3=-=-=-=')
arquivo3 = open('teste3.txt')
lerLinhas3 = arquivo3.readlines()
quantidadeLinhas = True

for indiceLinhas in lerLinhas3:
    equacao = indiceLinhas.strip()
    if quantidadeLinhas:
        print(int(equacao))
        quantidadeLinhas = False
    else:
        print(f"EQUAÇÃO -> {equacao} :" + f"{'VÁLIDO' if validaFormula(equacao) else 'INVÁLIDO.'}")




