// GUSTAVO FURINI
// Curso: Ciência da Computação

#include "contiki.h"
#include <stdio.h>
#include <stdlib.h>

// Declaração dos processos com nomes e descrições
PROCESS(batimentosCardiacos, "Batimentos Cardíacos");
PROCESS(saturacaoOxigenio, "Saturação de Oxigênio");
PROCESS(temperatura, "Temperatura");
PROCESS(alerta, "Alerta");

// Inicializa os processos automaticamente no início do sistema
AUTOSTART_PROCESSES(&batimentosCardiacos, &saturacaoOxigenio, &temperatura, &alerta);

// Função para gerar valores aleatórios dentro de um intervalo
static int get_random_value(int min, int max) {
    return (rand() % (max - min + 1)) + min;
}

// Definição do processo de Monitoramento de Batimentos Cardíacos
PROCESS_THREAD(batimentosCardiacos, ev, data) {
    static struct etimer timer; // Timer para controlar a periodicidade
    static int frequenciaCardiaca; // Variável para armazenar a frequência cardíaca

    PROCESS_BEGIN(); // Início do processo

    while (1) {
        // Gera um valor aleatório para a frequência cardíaca entre 20 e 140 bpm
        frequenciaCardiaca = get_random_value(20, 140);
        printf("Batimentos Cardíacos: %d bpm\n", frequenciaCardiaca);

        // Envia alertas se a frequência cardíaca estiver fora dos limites normais
        if (frequenciaCardiaca < 50) {
            process_post(&alerta, PROCESS_EVENT_CONTINUE, "Batimento cardíaco baixo");
        } else if (frequenciaCardiaca > 90) {
            process_post(&alerta, PROCESS_EVENT_CONTINUE, "Batimento cardíaco alto");
        }

        // Aguarda 3 segundos antes de continuar a execução
        etimer_set(&timer, CLOCK_SECOND * 3);
        PROCESS_WAIT_EVENT_UNTIL(etimer_expired(&timer));
    }

    PROCESS_END(); // Fim do processo
}

// Definição do processo de Monitoramento de Saturação de Oxigênio
PROCESS_THREAD(saturacaoOxigenio, ev, data) {
    static struct etimer timer; // Timer para controlar a periodicidade
    static int sat_ox; // Variável para armazenar a saturação de oxigênio

    PROCESS_BEGIN(); // Início do processo

    while (1) {
        // Gera um valor aleatório para a saturação de oxigênio entre 80 e 100%
        sat_ox = get_random_value(80, 100);
        printf("Saturação de Oxigênio: %d%%\n", sat_ox);

        // Envia um alerta se a saturação de oxigênio estiver baixa
        if (sat_ox < 90) {
            process_post(&alerta, PROCESS_EVENT_CONTINUE, "Saturação baixa");
        }

        // Aguarda 3 segundos antes de continuar a execução
        etimer_set(&timer, CLOCK_SECOND * 3);
        PROCESS_WAIT_EVENT_UNTIL(etimer_expired(&timer));
    }

    PROCESS_END(); // Fim do processo
}

// Definição do processo de Monitoramento de Temperatura
PROCESS_THREAD(temperatura, ev, data) {
    static struct etimer timer; // Timer para controlar a periodicidade
    static int temp; // Variável para armazenar a temperatura

    PROCESS_BEGIN(); // Início do processo

    while (1) {
        // Gera um valor aleatório para a temperatura entre 34 e 41°C
        temp = get_random_value(34, 41);
        printf("Temperatura: %d°C\n", temp);

        // Envia alertas se a temperatura indicar hipotermia ou febre
        if (temp < 35) {
            process_post(&alerta, PROCESS_EVENT_CONTINUE, "Hipotermia");
        } else if (temp > 37) {
            process_post(&alerta, PROCESS_EVENT_CONTINUE, "Febre");
        }

        // Aguarda 3 segundos antes de continuar a execução
        etimer_set(&timer, CLOCK_SECOND * 3);
        PROCESS_WAIT_EVENT_UNTIL(etimer_expired(&timer));
    }

    PROCESS_END(); // Fim do processo
}

// Definição do processo de Alerta
PROCESS_THREAD(alerta, ev, data) {
    PROCESS_BEGIN(); // Início do processo

    while (1) {
        // Aguarda um evento
        PROCESS_WAIT_EVENT();
        if (ev == PROCESS_EVENT_CONTINUE) {
            // Exibe a mensagem de alerta recebida
            printf("Alerta: %s\n", (char *)data);
        }
    }

    PROCESS_END(); // Fim do processo
}
