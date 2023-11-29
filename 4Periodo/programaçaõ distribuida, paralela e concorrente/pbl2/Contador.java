
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Contador extends Thread {
    private Semaphore semaforoDoContador;
    private Semaphore semaforoDoPadronizador;
    private int contador;
    private Padronizador padronizador;
    protected String frase;

    public Contador(Semaphore semaforoDoContador, Semaphore semaforoDoPadronizador, Padronizador padronizador) {
        this.semaforoDoContador = semaforoDoContador;
        this.semaforoDoPadronizador = semaforoDoPadronizador;
        this.padronizador = padronizador;
    }

    public void run() {
        try {
            semaforoDoContador.acquire();
            this.frase = padronizador.frase;
            this.contador = 0;
            for (int i = 0; i < 10; i++) {
                if (frase.charAt(i) == 'A'|| frase.charAt(i) == 'E' || frase.charAt(i) == 'I' || frase.charAt(i) == 'O' || frase.charAt(i) == 'U'){
                    this.contador = contador + 1;
                }

            }
            System.out.println(contador);
            semaforoDoPadronizador.release();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}