
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Gerador extends Thread{
    private Semaphore semaforo1;
    private Semaphore semaforoDoPadronizador;
    protected String Frase;


    static String alfabeto(int n) {


        String alfabeto = "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {


            int index
                    = (int) (alfabeto.length() * Math.random());


            sb.append(alfabeto.charAt(index));
        }

        return sb.toString();
    }

    public Gerador(Semaphore semaforo1, Semaphore semaforoDoPadronizador){
        this.semaforo1 = semaforo1;
        this.semaforoDoPadronizador = semaforoDoPadronizador;
        this.Frase = "";

    }
    public void run(){
        try {
            semaforo1.acquire();
            Frase = "";
            int n = 10;
            System.out.println(alfabeto(n));
            this.Frase += (alfabeto(n));
            System.out.println(Frase);
            semaforo1.release();
            semaforoDoPadronizador.release();
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }
}

