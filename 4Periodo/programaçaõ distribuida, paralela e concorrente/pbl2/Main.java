

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        Semaphore semaforoDoGerador = new Semaphore(1);
        Semaphore semaforoPadronizador = new Semaphore(0);
        Semaphore semaforoDoContador = new Semaphore(0);

        String[] Frase = new String[10];

        Gerador gerador = new Gerador(semaforoDoGerador,semaforoPadronizador);
        Padronizador padronizador = new Padronizador(semaforoPadronizador,semaforoDoContador,gerador);
        Contador contador = new Contador(semaforoDoContador,semaforoPadronizador,padronizador);
        gerador.start();
        padronizador.start();
        contador.start();

        try {
            gerador.join();
            padronizador.join();
            contador.join();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
