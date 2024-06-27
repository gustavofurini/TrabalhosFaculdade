import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from scipy.stats import probplot

# Função para simular uma temporada
def simular_temporada(prob_vitoria, prob_empate, prob_derrota, num_jogos, num_simulacoes):
    resultados = []
    for _ in range(num_simulacoes):
        pontos = 0
        for _ in range(num_jogos):
            resultado = np.random.choice(['V', 'E', 'D'], p=[prob_vitoria, prob_empate, prob_derrota])
            if resultado == 'V':
                pontos += 3
            elif resultado == 'E':
                pontos += 1
        resultados.append(pontos)
    return resultados

# Parâmetros dos cenários
cenarios = {
    'Cenário 1': {'prob_vitoria': 0.40, 'prob_empate': 0.30, 'prob_derrota': 0.30},
    'Cenário 2': {'prob_vitoria': 0.50, 'prob_empate': 0.25, 'prob_derrota': 0.25},
    'Cenário 3': {'prob_vitoria': 0.30, 'prob_empate': 0.35, 'prob_derrota': 0.35},
}

# Simulação de 100 vezes para cada cenário
num_simulacoes = 100
num_jogos = 38

resultados_simulacoes = {}
for nome, params in cenarios.items():
    resultados_simulacoes[nome] = simular_temporada(params['prob_vitoria'], params['prob_empate'], params['prob_derrota'], num_jogos, num_simulacoes)

# Criar DataFrame para armazenar resultados
df_resultados = pd.DataFrame(resultados_simulacoes)

# Análise estatística
analise_estatistica = df_resultados.describe()


# Gráficos
plt.figure(figsize=(12, 8))
for cenario in df_resultados.columns:
    sns.histplot(df_resultados[cenario], kde=True, label=cenario)
plt.legend()
plt.title('Distribuição dos Pontos por Cenário')
plt.xlabel('Pontos Totais')
plt.ylabel('Frequência')
plt.show()

# Gráficos QQ
plt.figure(figsize=(18, 6))
for i, cenario in enumerate(df_resultados.columns, 1):
    plt.subplot(1, 3, i)
    probplot(df_resultados[cenario], dist="norm", plot=plt)
    plt.title(f'QQ Plot - {cenario}')
plt.tight_layout()
plt.show()

# Salvar resultados em Excel
df_resultados.to_excel('resultados_simulacoes_temporada.xlsx')

# Exibir análise estatística
print(analise_estatistica)
