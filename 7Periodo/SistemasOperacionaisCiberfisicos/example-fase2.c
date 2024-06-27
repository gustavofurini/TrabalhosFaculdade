// Alunos: Gustavo Furini, Leonardo Nervino e Lucca Libanori

#include "FreeRTOS.h"
#include "semphr.h" // Adicionar cabeçalho para semáforos
#include "task.h"
#include <stdio.h>

// Variáveis globais para armazenar a velocidade dos motores
volatile long velocidadeMotor0;
volatile long velocidadeMotor1;
volatile long velocidadeMotor2;
volatile long velocidadeMotor3;

// Variáveis globais para as manobras do quadricóptero
volatile char sentido[10];
volatile char direcao[10];
volatile char orientacao[10];

// Semáforo binário para garantir acesso exclusivo às variáveis globais
SemaphoreHandle_t semaforoManobras;

// Função para inicializar semáforo binário
void inicializarSemaforo() {
  semaforoManobras = xSemaphoreCreateBinary();
  if (semaforoManobras != NULL) {
    xSemaphoreGive(semaforoManobras); // Inicialmente liberar o semáforo
  }
}

// Função que simula a manobra de guinada
void guinada_task(void *pvParameters) {
  while (1) {
    // Aguardar pelo semáforo antes de acessar as variáveis globais
    xSemaphoreTake(semaforoManobras, portMAX_DELAY);

    if (sentido[0] == 'h') { // sentido horário
      velocidadeMotor0 -= 100;
      velocidadeMotor2 -= 100;
      velocidadeMotor1 += 100;
      velocidadeMotor3 += 100;
    } else { // sentido anti-horário
      velocidadeMotor0 += 100;
      velocidadeMotor2 += 100;
      velocidadeMotor1 -= 100;
      velocidadeMotor3 -= 100;
    }

    printf("Manobra: Guinada, Sentido: %s, Velocidade dos motores: %ld %ld %ld "
           "%ld\n",
           (sentido[0] == 'h') ? "Horário" : "Anti-horário", velocidadeMotor0,
           velocidadeMotor1, velocidadeMotor2, velocidadeMotor3);

    // Liberar o semáforo após acessar as variáveis globais
    xSemaphoreGive(semaforoManobras);

    vTaskDelay(portTICK_RATE_MS * 10); // Tempo de controle de guinada em Tick
  }
}

// Função que simula a manobra de arfagem
void arfagem_task(void *pvParameters) {
  while (1) {
    // Aguardar pelo semáforo antes de acessar as variáveis globais
    xSemaphoreTake(semaforoManobras, portMAX_DELAY);

    if (direcao[0] == 'f') { // mover para frente
      velocidadeMotor2 += 25;
      velocidadeMotor3 += 25;
      velocidadeMotor0 -= 25;
      velocidadeMotor1 -= 25;
    } else { // mover para trás
      velocidadeMotor2 -= 25;
      velocidadeMotor3 -= 25;
      velocidadeMotor0 += 25;
      velocidadeMotor1 += 25;
    }

    printf("Manobra: Arfagem, Direção: %s, Velocidade dos motores: %ld %ld %ld "
           "%ld\n",
           (direcao[0] == 'f') ? "Frente" : "Trás", velocidadeMotor0,
           velocidadeMotor1, velocidadeMotor2, velocidadeMotor3);

    // Liberar o semáforo após acessar as variáveis globais
    xSemaphoreGive(semaforoManobras);

    vTaskDelay(portTICK_RATE_MS * 40); // Tempo de controle de arfagem em Tick
  }
}

// Função que simula a manobra de rolagem
void rolagem_task(void *pvParameters) {
  while (1) {
    // Aguardar pelo semáforo antes de acessar as variáveis globais
    xSemaphoreTake(semaforoManobras, portMAX_DELAY);

    if (orientacao[0] == 'd') { // virar à direita
      velocidadeMotor0 += 50;
      velocidadeMotor3 += 50;
      velocidadeMotor1 -= 50;
      velocidadeMotor2 -= 50;
    } else { // virar à esquerda
      velocidadeMotor0 -= 50;
      velocidadeMotor3 -= 50;
      velocidadeMotor1 += 50;
      velocidadeMotor2 += 50;
    }

    printf("Manobra: Rolagem, Direção: %s, Velocidade dos motores: %ld %ld %ld "
           "%ld\n",
           (orientacao[0] == 'd') ? "Direita" : "Esquerda", velocidadeMotor0,
           velocidadeMotor1, velocidadeMotor2, velocidadeMotor3);

    // Liberar o semáforo após acessar as variáveis globais
    xSemaphoreGive(semaforoManobras);

    vTaskDelay(portTICK_RATE_MS * 20); // Tempo de controle de rolagem em Tick
  }
}

// Função que simula a tarefa de rádio frequência para alterar as manobras
void radio_frequencia_task(void *pvParameters) {
  while (1) {
    // Sortear os valores para as manobras
    int x = rand() % 100;
    int y = rand() % 100;
    int z = rand() % 100;

    // Verificar se os números sorteados são pares ou ímpares e atribuir as
    // manobras correspondentes
    if (x % 2 == 0) {
      sprintf(sentido, "horario");
    } else {
      sprintf(sentido, "antihorario");
    }

    if (y % 2 == 0) {
      sprintf(direcao, "frente");
    } else {
      sprintf(direcao, "tras");
    }

    if (z % 2 == 0) {
      sprintf(orientacao, "direita");
    } else {
      sprintf(orientacao, "esquerda");
    }

    // Aguardar um atraso em Tick de 100ms
    vTaskDelay(portTICK_RATE_MS * 100);
  }
}

int main_() {
  // Inicializar o semáforo
  inicializarSemaforo();

  // Criação das tarefas
  xTaskCreate(guinada_task, "Guinada Task", 1000, NULL, 2, NULL);
  xTaskCreate(arfagem_task, "Arfagem Task", 1000, NULL, 2, NULL);
  xTaskCreate(rolagem_task, "Rolagem Task", 1000, NULL, 2, NULL);
  xTaskCreate(radio_frequencia_task, "Radio Frequencia Task", 1000, NULL, 1,
              NULL); // Prioridade inferior

  // Inicialização do FreeRTOS scheduler
  vTaskStartScheduler();

  return 0;
}
