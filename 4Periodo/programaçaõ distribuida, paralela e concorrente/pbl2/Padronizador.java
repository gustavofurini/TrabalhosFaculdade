
import java.util.Locale;
import java.util.concurrent.Semaphore;

public class Padronizador extends Thread{
    private Semaphore semaforoDoPadronizador;
    private Semaphore semaforoDoContador;
    private Gerador gerador;
    protected String frase;

    public Padronizador(Semaphore semaforoDoPadronizador, Semaphore semaforoDoContador, Gerador gerador){
        this.semaforoDoPadronizador = semaforoDoPadronizador;
        this.semaforoDoContador = semaforoDoContador;
        this.gerador = gerador;
    }
    public void run(){
        try {
            semaforoDoPadronizador.acquire();
            this.frase = gerador.Frase.toUpperCase();
            System.out.println(frase);
            semaforoDoPadronizador.release();
            semaforoDoContador.release();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
