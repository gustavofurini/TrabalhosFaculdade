from bs4 import BeautifulSoup
import requests
import spacy
import string
import numpy as np


# ===================== CORPUS =======================

url1 = 'https://www.datarobot.com/blog/what-is-natural-language-processing-introduction-to-nlp/'
url2 = 'https://www.qualtrics.com/experience-management/customer/natural-language-processing/'
url3 = 'https://www.oanayucel.ro/en/nlp-what-is-it-and-how-can-it-help-you/'
url4 = 'https://viso.ai/deep-learning/natural-language-processing/'
url5 = 'https://www.oracle.com/hk/artificial-intelligence/what-is-natural-language-processing/'

teste1 = requests.get(url1)
teste2 = requests.get(url2)
teste3 = requests.get(url3)
teste4 = requests.get(url4)
teste5 = requests.get(url5)


pagina1 = BeautifulSoup(teste1.content, 'html.parser')
pagina2 = BeautifulSoup(teste2.content, 'html.parser')
pagina3 = BeautifulSoup(teste3.content, 'html.parser')
pagina4 = BeautifulSoup(teste4.content, 'html.parser')
pagina5 = BeautifulSoup(teste5.content, 'html.parser')
corpus = []
listaCorpus = []


pegarTexto1 = pagina1.find_all("p")
for url1 in pegarTexto1:
  corpus.append(url1.get_text())

pegarTexto2 = pagina2.find_all("p")
for url2 in pegarTexto2:
  corpus.append(url2.get_text())

pegarTexto3 = pagina3.find_all("p")
for url3 in pegarTexto3:
  corpus.append(url3.get_text())

pegarTexto4 = pagina4.find_all("p")
for url4 in pegarTexto4:
  corpus.append(url4.get_text())

pegarTexto5 = pagina5.find_all("p")
for url5 in pegarTexto5:
  corpus.append(url5.get_text())

listaCorpus.append(corpus)
 # ================== BAG OF WORDS ======================
termos = []
quantidadeSentencas = 0
for corpus in listaCorpus:
  for sentenca in corpus:
    quantidadeSentencas += 1
    for termo in sentenca.split(' '):
      if termo not in termos:
        termos.append(termo)
bagOfWords = np.zeros((quantidadeSentencas,len(termos)))
sentencaAtual = 0
for corpus in listaCorpus:
  for sentencas in corpus:
    for termo in sentencas.split(' '):
      bagOfWords[sentencaAtual][termos.index(termo)] += 1
    sentencaAtual += 1
# =================== TF =========================
tfs = np.zeros((len(bagOfWords),len(bagOfWords[0])))
sentencaAtual = 0
for corpus in listaCorpus:
  for sentencas in corpus:
    numeroDeTermos = len(sentencas.split(' '))
    for termo in sentencas.split(' '):
      tfs[sentencaAtual][termos.index(termo)] = bagOfWords[sentencaAtual][termos.index(termo)] / numeroDeTermos
    sentencaAtual += 1

print(f'TF:\n{tfs}')
# ================= IDF ===========================
idfs = []
for termo in range(len(termos)):
  termoVerificados = 0
  for sentencasVerificadas in bagOfWords:
    if sentencasVerificadas[termo] > 0: termoVerificados += 1
  idfs.append(np.log10(len(bagOfWords)/termoVerificados))


print(f'IDF:\n{idfs}')
# ====================== TFIDF =======================
tfidf = np.zeros((len(bagOfWords),len(bagOfWords[0])))
for i in range(len(tfidf)):
  for j in range(len(tfidf[0])):
    tfidf[i][j] = tfs[i][j] * idfs[j]

print(f'TFIDF:\n{tfidf}')
# ==================== MATRIZ DISTÂNCIA ===================
vetor = 0
matrizDistancia = np.zeros((len(tfidf),len(tfidf)))

for vector in tfidf:
  i = vetor
  while i < len(tfidf):
    distance = np.dot(vector,tfidf[i])/(np.linalg.norm(vector)*np.linalg.norm(tfidf[i]))
    matrizDistancia[vetor][i] = distance
    matrizDistancia[i][vetor] = distance
    i += 1
  vetor += 1

print(f'Matriz distância:\n{matrizDistancia}')


