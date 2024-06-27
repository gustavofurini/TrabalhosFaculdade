//Estudantes: Gustavo Furini e Leonardo Nervino
//Curso: Ciência da Computação.
//Periodo: 7 Periodo.

#include <FreeRTOS.h>
#include <task.h>
#include <semphr.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "basic_io.h"


SemaphoreHandle_t semaforo;

void vTask1(void* pvParameters) {
    (void)pvParameters;
    time_t segundos;
    struct tm* datetimeNow;
    char display[100];
    const long idTarefa = (long)pvParameters;

    for (;;) {
        time(&segundos);
        datetimeNow = localtime(&segundos);

        int dia = datetimeNow->tm_mday;
        int mes = datetimeNow->tm_mon + 1;
        int ano = datetimeNow->tm_year + 1900;

        xSemaphoreTake(semaforo, 1000);
        sprintf(display, "Task %ld - Data atual: %02d/%02d/%04d\n", idTarefa, dia, mes, ano);
        vPrintString(display);
        xSemaphoreGive(semaforo);

        vTaskDelay(1000);
    }
}

void vTask2(void* pvParameters) {
    (void)pvParameters;
    time_t segundos;
    struct tm* datetimeNow;
    char display[100];
    const long idTarefa = (long)pvParameters;

    for (;;) {
        time(&segundos);
        datetimeNow = localtime(&segundos);

        int hour = datetimeNow->tm_hour;
        int min = datetimeNow->tm_min;
        int sec = datetimeNow->tm_sec;

        xSemaphoreTake(semaforo, 1000);
        sprintf(display, "Task %ld - Hora atual: %02d:%02d:%02d\n", idTarefa, hour, min, sec);
        vPrintString(display);
        xSemaphoreGive(semaforo);

        vTaskDelay(1000);
    }
}

void vTask3(void* pvParameters) {
    (void)pvParameters;
    char display[100];
    const long idTarefa = (long)pvParameters;

    for (;;) {
        float temp = ((float)rand() / (float)(RAND_MAX)) * 40;

        xSemaphoreTake(semaforo, 1000);
        sprintf(display, "Task %ld - Curitiba %.2f graus celsius\n", idTarefa, temp);
        vPrintString(display);
        xSemaphoreGive(semaforo);

        vTaskDelay(1000);
    }
}

int main_(void) {
    semaforo = xSemaphoreCreateMutex();

    xTaskCreate(vTask1, "TaskData", 1000, (void*)1, 1, NULL);
    xTaskCreate(vTask2, "TaskHora", 1000, (void*)2, 1, NULL);
    xTaskCreate(vTask3, "TaskTemp", 1000, (void*)3, 1, NULL);

    vTaskStartScheduler();

    return 0;
}