
from bs4 import BeautifulSoup
import requests
from tabulate import tabulate


# pagina 1
url = requests.get('https://www.datarobot.com/blog/what-is-natural-language-processing-introduction-to-nlp/')
pagina1 = BeautifulSoup(url.text, "html.parser")
frasesPagina1 = []
pegarTexto1 = pagina1.find_all("p")
for url in pegarTexto1:
    frasesPagina1.append(url.get_text())

# pagina2
url2 = requests.get('https://www.qualtrics.com/experience-management/customer/natural-language-processing/')
pagina2 = BeautifulSoup(url2.text, "html.parser")
frasesPagina2 = []
pegarTexto2 = pagina2.find_all("p")
for url2 in pegarTexto2:
    frasesPagina2.append(url2.get_text())

# pagina3
url3 = requests.get('https://www.oanayucel.ro/en/nlp-what-is-it-and-how-can-it-help-you/')
pagina3 = BeautifulSoup(url3.text, "html.parser")
frasesPagina3 = []
pegarTexto3 = pagina3.find_all("p")
for url3 in pegarTexto3:
    frasesPagina3.append(url3.get_text())

# pagina4
url4 = requests.get('https://viso.ai/deep-learning/natural-language-processing/')
pagina4 = BeautifulSoup(url4.text, "html.parser")
frasesPagina4 = []
pegarTexto4 = pagina4.find_all("p")
for url4 in pegarTexto4:
    frasesPagina4.append(url4.get_text())

# pagina5
url5 = requests.get('https://www.oracle.com/hk/artificial-intelligence/what-is-natural-language-processing/')
pagina5 = BeautifulSoup(url5.text, "html.parser")
frasesPagina5 = []
pegarTexto5 = pagina5.find_all("p")
for url5 in pegarTexto5:
    frasesPagina5.append(url5.get_text())

print("SEPARAÇÃO DE SENTENÇAS\n")

print(f'PÁGINA 1 - SENTENÇAS: {frasesPagina1}\n')
print(f'PÁGINA 2 - SENTENÇAS: {frasesPagina2}\n')
print(f'PÁGINA 3 - SENTENÇAS: {frasesPagina3}\n')
print(f'PÁGINA 4 - SENTENÇAS: {frasesPagina4}\n')
print(f'PÁGINA 5 - SENTENÇAS: {frasesPagina5}\n')

print('SEPARAÇÃO DE PALAVRAS\n')

# pagina1
sentencas1 = list(pegarTexto1)
palavrasPagina1 = []
for text in sentencas1:
    palavrasPagina1.append(text.get_text().split(" "))

# pagina2
sentencas2 = list(pegarTexto2)
palavrasPagina2 = []
for text in sentencas2:
    palavrasPagina2.append(text.get_text().split(" "))

# pagina3
sentencas3 = list(pegarTexto3)
palavrasPagina3 = []
for text in sentencas3:
    palavrasPagina3.append(text.get_text().split(" "))

# pagina4
sentencas4 = list(pegarTexto4)
palavrasPagina4 = []
for text in sentencas4:
    palavrasPagina4.append(text.get_text().split(" "))

# pagina5
sentencas5 = list(pegarTexto5)
palavrasPagina5 = []
for text in sentencas5:
    palavrasPagina5.append(text.get_text().split(" "))

print(f'PÁGINA 1 - PALAVRAS: {palavrasPagina1}\n')
print(f'PÁGiNA 2 - PALAVRAS: {palavrasPagina2}\n')
print(f'PÁGiNA 3 - PALAVRAS: {palavrasPagina3}\n')
print(f'PÁGiNA 4 - PALAVRAS: {palavrasPagina4}\n')
print(f'PÁGiNA 5 - PALAVRAS: {palavrasPagina5}\n')

listaDePalavras = []
sites = [palavrasPagina1, palavrasPagina2, palavrasPagina3, palavrasPagina4, palavrasPagina5]
vocabulario = set()
# print(sites)

for site in sites:
    for palavra in site:

        if palavra not in listaDePalavras:
            listaDePalavras.append(palavra)

print(listaDePalavras)
# print(teste)


for sentenca in listaDePalavras:
    for palavra in sentenca:
        vocabulario.add(palavra)

matriz = []
for sentenca in listaDePalavras:
    vetor = []
    for palavra in vocabulario:
        if palavra in sentenca:
            vetor.append(sentenca.count(palavra))
        else:
            vetor.append(0)
    matriz.append(vetor)

matrizFinal = tabulate(matriz)
print(matrizFinal)
