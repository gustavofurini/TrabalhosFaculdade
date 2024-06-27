
// Aluno: Gustavo Furini


#include <stdio.h>
#include <stdlib.h>
#include "FreeRTOS.h"
#include "task.h"
#include <basic_io.h>

// PARAMETROS DE SAUDE
#define MinimoFrequenciaCardiaca 20
#define MaximaFrequenciaCardiaca 140
#define MinimoSaturacaoOxigenio 80
#define MaximoSaturacaoOxigenio 100
#define MinimoTemperatura 34
#define MaximoTemperatura 41

// FUNCAO PARA GERAR UM NUMERO ALEATORIO ENTRE DOIS VALORES
int GerarNumeroAleatorio(int min, int max) {
    return (rand() % (max - min + 1)) + min;
}

// MONITORAR BATIMENTOS CARDIACOS
void BatimentosCardiacos(void* pvParameters) {
    while (1) {
        // FREQUENCIA CARDIACA ALEATORIA
        int FrequenciaCardiaca = GerarNumeroAleatorio(MaximaFrequenciaCardiaca, MaximaFrequenciaCardiaca);
        vPrintStringAndNumber("Batimentos Cardiaco do paciente:\n", FrequenciaCardiaca);

        // VERIFICANDO SE ESTÁ NO INTERVALO NORMAL
        if (FrequenciaCardiaca < 50 || FrequenciaCardiaca > 90) {
           vPrintStringAndNumber("Batimentos Cardiaco do paciente esta alterado:\n", FrequenciaCardiaca);
        }

        // AGUARDA PROXIMA ITERACAO
        vTaskDelay(1000); 
    }
    // DELETA A TAREFA QUANDO NAO E NECESSARIA
    vTaskDelete(NULL);
}

// MONITOR DE SATURACAO OXIGENIO
void SaturacaoDoOxigenio(void* pvParameters) {
    while (1) {
        // SATURACAO DE OXIGENIO ALEATORIA
        int SaturacaoOxigenio = GerarNumeroAleatorio(MinimoSaturacaoOxigenio, MaximoSaturacaoOxigenio);
        vPrintStringAndNumber("Saturacao de Oxigenio do paciente:\n", SaturacaoOxigenio);

        // VERIFICA SE A SATURACAO ESTA MENOR QQUE 90
        if (SaturacaoOxigenio < 90) {
            vPrintStringAndNumber("Saturacao de Oxigenio do paciente esta baixa:\n", SaturacaoOxigenio);
        }

      // AGUARDA PROXIMA ITERACAO
        vTaskDelay(1000);
    }
    // DELETA TAREFA QUANDO NAO E NECESSARIA
    vTaskDelete(NULL);
}

// MONITORA TEMPERATURA
void Temperatura(void* pvParameters) {
    while (1) {
        // GERA TEMPERATURA ALEATORIA
        int Temperatura = GerarNumeroAleatorio(MinimoTemperatura, MaximoTemperatura);
        vPrintStringAndNumber("Temperatura do paciente:\n", Temperatura);

        // VERIFICA SE A TEMPERATURA ESTA FORA DO INTERVALO CONSIDERADO NORMAL
        if (Temperatura < 35 || Temperatura > 37.5) {
            vPrintStringAndNumber("Temperatura do paciente esta alterada:\n",Temperatura);
        }

        // AGUARDA A PROXIMA ITERACAO
        vTaskDelay(1000);
    }
    // DELETA A TAREFA QUANDO NAO E NECESSARIA
    vTaskDelete(NULL);
}

// FUNCAO MAIN
int main_(void) {

    // CRIA TAREFA
    xTaskCreate(BatimentosCardiacos, "Verificador de Batimentos Cardiaco", 1000, NULL, 1, NULL);
    xTaskCreate(SaturacaoDoOxigenio, "Verificador de Saturacao de Oxigenio", 1000, NULL, 1, NULL);
    xTaskCreate(Temperatura, "Verificador de Temperatura", 1000, NULL, 1, NULL);

    // INICIALIZA
    vTaskStartScheduler();

    return 0;
}
