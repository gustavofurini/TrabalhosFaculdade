// Aluno: Gustavo Furini, Leonardo Nervino e Lucca Libanori

#include <stdio.h>
#include "FreeRTOS.h"
#include "task.h"

// Definição das variáveis globais que simulam a velocidade dos motores
int motor0_speed = 0;
int motor1_speed = 0;
int motor2_speed = 0;
int motor3_speed = 0;

// Função que simula a manobra de guinada
void guinada_task(void *pvParameters) {
    char *sentido = (char *) pvParameters;
    while (1) {
        vTaskDelay(10 / portTICK_PERIOD_MS); // Tempo de controle de guinada

        if (*sentido == 'h') { // sentido horário
            motor0_speed -= 100;
            motor2_speed -= 100;
            motor1_speed += 100;
            motor3_speed += 100;
        } else { // sentido anti-horário
            motor0_speed += 100;
            motor2_speed += 100;
            motor1_speed -= 100;
            motor3_speed -= 100;
        }

        printf("Manobra: Guinada, Sentido: %s, Velocidade dos motores: %d %d %d %d\n",
               (*sentido == 'h') ? "Horário" : "Anti-horário",
               motor0_speed, motor1_speed, motor2_speed, motor3_speed);
    }
    vTaskDelete(NULL);
}

// Função que simula a manobra de arfagem
void arfagem_task(void *pvParameters) {
    char *direcao = (char *) pvParameters;
    while (1) {
        vTaskDelay(40 / portTICK_PERIOD_MS); // Tempo de controle de arfagem

        if (*direcao == 'f') { // mover para frente
            motor2_speed += 25;
            motor3_speed += 25;
            motor0_speed -= 25;
            motor1_speed -= 25;
        } else { // mover para trás
            motor2_speed -= 25;
            motor3_speed -= 25;
            motor0_speed += 25;
            motor1_speed += 25;
        }

        printf("Manobra: Arfagem, Direção: %s, Velocidade dos motores: %d %d %d %d\n",
               (*direcao == 'f') ? "Frente" : "Trás",
               motor0_speed, motor1_speed, motor2_speed, motor3_speed);
    }
    vTaskDelete(NULL);
}

// Função que simula a manobra de rolagem
void rolagem_task(void *pvParameters) {
    char *direcao = (char *) pvParameters;
    while (1) {
        vTaskDelay(20 / portTICK_PERIOD_MS); // Tempo de controle de rolagem

        if (*direcao == 'd') { // virar à direita
            motor0_speed += 50;
            motor3_speed += 50;
            motor1_speed -= 50;
            motor2_speed -= 50;
        } else { // virar à esquerda
            motor0_speed -= 50;
            motor3_speed -= 50;
            motor1_speed += 50;
            motor2_speed += 50;
        }

        printf("Manobra: Rolagem, Direção: %s, Velocidade dos motores: %d %d %d %d\n",
               (*direcao == 'd') ? "Direita" : "Esquerda",
               motor0_speed, motor1_speed, motor2_speed, motor3_speed);
    }
    vTaskDelete(NULL);
}

int main() {
    // Criação das tarefas
    xTaskCreate(guinada_task, "Guinada Task", configMINIMAL_STACK_SIZE, "h", tskIDLE_PRIORITY, NULL);
    xTaskCreate(arfagem_task, "Arfagem Task", configMINIMAL_STACK_SIZE, "f", tskIDLE_PRIORITY, NULL);
    xTaskCreate(rolagem_task, "Rolagem Task", configMINIMAL_STACK_SIZE, "d", tskIDLE_PRIORITY, NULL);

    // Inicialização do FreeRTOS scheduler
    vTaskStartScheduler();

    return 0;
}
