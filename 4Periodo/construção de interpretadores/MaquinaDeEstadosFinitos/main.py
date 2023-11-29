teste1 = [4, 'abbab', 'abbabb', 'abba', 'acacabb']
teste2 = [3, 'abbabbabb', 'abbabb', 'abb']
teste3 = [6, 'abbabbabbba', 'abbabb', 'abbabba', 'acbcabb', 'abbcabbbcabb', 'abb']


with open('teste1.txt','w') as arquivo1:
    for valor in teste1:
        arquivo1.write(str(valor) + '\n')

with open('teste2.txt','w') as arquivo2:
    for valor in teste2:
        arquivo2.write(str(valor) + '\n')

with open('teste3.txt','w') as arquivo3:
    for valor in teste3:
        arquivo3.write(str(valor) + '\n')


print('=-=-=-=ARQUIVO 1=-=-=-=')
arquivo1 = open('teste1.txt')
lerLinhas1 = arquivo1.readlines()

for indiceLinhas in lerLinhas1:
    if indiceLinhas[0] != 'a' and indiceLinhas[0] != 'b' and indiceLinhas[0] != 'c':
        print(indiceLinhas[0])
    else:
        valorLinhas = False
        for x in range(len(indiceLinhas)):
            if indiceLinhas[x] == 'a':
                if indiceLinhas[x + 1] == 'b' and indiceLinhas[x + 2] == 'b':
                    valorLinhas = True
                else:
                    valorLinhas = False
                    break

        if valorLinhas == True:

            print(indiceLinhas.rstrip() + ': pertence')
        else:
            print(indiceLinhas.rstrip() + ': não pertence.')



print('=-=-=-=ARQUIVO 2=-=-=-=')
arquivo2 = open('teste2.txt')
lerLinhas2 = arquivo2.readlines()

for indiceLinhas in lerLinhas2:
    if indiceLinhas[0] != 'a' and indiceLinhas[0] != 'b' and indiceLinhas[0] != 'c':
        print(indiceLinhas[0])
    else:
        valorLinhas = False
        for x in range(len(indiceLinhas)):
            if indiceLinhas[x] == 'a':
                if indiceLinhas[x + 1] == 'b' and indiceLinhas[x + 2] == 'b':
                    valorLinhas = True
                else:
                    valorLinhas = False
                    break

        if valorLinhas == True:
            print(indiceLinhas.rstrip() + ': pertence!')
        else:
            print(indiceLinhas.rstrip() + ' : não pertence!')

print('=-=-=-=ARQUIVO 3=-=-=-=')
arquivo3 = open('teste3.txt')
lerLinhas3 = arquivo3.readlines()

for indiceLinhas in lerLinhas3:
    if indiceLinhas[0] != 'a' and indiceLinhas[0] != 'b' and indiceLinhas[0] != 'c':
        print(indiceLinhas[0])
    else:
        valorLinhas = False
        for x in range(len(indiceLinhas)):
            if indiceLinhas[x] == 'a':
                if indiceLinhas[x + 1] == 'b' and indiceLinhas[x + 2] == 'b':
                    valorLinhas = True
                else:
                    valorLinhas = False
                    break
        if valorLinhas == True:
            print(indiceLinhas.rstrip() + ': pertence!')
        else:
            print(indiceLinhas.rstrip() + ' : não pertence!')
