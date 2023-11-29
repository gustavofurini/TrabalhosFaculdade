import java.util.concurrent.Semaphore;
import java.util.Random;

public class Pessoa extends Thread {
    private Semaphore semaforo1;
    private Semaphore semaforo2;
    private Semaphore mutex;
    private int[] etapas;
    private int valorGasto;
    private Semaphore mutexTempoDeSonoThread;
    private int[] contador;
    private int[] n;
    private int[] compras;
    private Semaphore mutexCompras;
    private int tempoDeSonoThread;
    private int valor;
    private int[] pubilcTempoDeSonoThread;
    private int[] publicValorGasto;
    private boolean[] atualizarEstado;

    public Pessoa(Semaphore entrada, Semaphore saida, Semaphore mutex, int[] contador, int[] n, int[] compras, Semaphore mutexCompras, int[] pubilcTempoDeSonoThread, boolean atualizarEstado[], int[] etapas, int[] publicValorGasto, Semaphore mutexTempoDeSonoThread){
        this.semaforo1 = entrada;
        this.semaforo2 = saida;
        this.mutex = mutex;
        this.contador = contador;
        this.n = n;
        this.compras = compras;
        this.mutexCompras = mutexCompras;
        Random valorAleatorio = new Random();
        this.tempoDeSonoThread = valorAleatorio.nextInt(5001 - 1000) + 1000;
        this.valor = 0;
        this.pubilcTempoDeSonoThread = pubilcTempoDeSonoThread;
        this.atualizarEstado = atualizarEstado;
        this.etapas = etapas;
        this.mutexTempoDeSonoThread = mutexTempoDeSonoThread;
        this.valorGasto = 1;
        this.publicValorGasto = publicValorGasto;
        System.out.println("Thread dormiu por -> " + this.tempoDeSonoThread + " Gastos ->  " + this.valorGasto +
                "\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
    public void prontoCritico(){
        try {

            if(this.valor < 5){
                while(!(atualizarEstado[0])){
                    Thread.sleep(1);
                }
                Random valorAleatorio = new Random();
                this.mutexTempoDeSonoThread.acquire();
                this.tempoDeSonoThread = this.pubilcTempoDeSonoThread[0] + valorAleatorio.nextInt(1001) - 500;
                this.valorGasto = this.publicValorGasto[0] + valorAleatorio.nextInt(5) - 1;
                if(this.valorGasto < 1){
                    this.valorGasto = 1;
                }
                if(this.tempoDeSonoThread < 0){
                    this.tempoDeSonoThread = 1;
                }
                this.mutexTempoDeSonoThread.release();
            }
            else{
                this.mutexTempoDeSonoThread.acquire();
                this.pubilcTempoDeSonoThread[0] = this.tempoDeSonoThread;
                this.publicValorGasto[0] = this.valorGasto;
                this.atualizarEstado[0] = true;
                this.mutexTempoDeSonoThread.release();
            }
            this.compras[0] = n[0]*5;
            this.valor = 0;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void rendevouz(){
        try {
            this.atualizarEstado[0] = false;
            boolean finalizarProcesso = false;
            while(!finalizarProcesso){
                Thread.sleep(this.tempoDeSonoThread);
                this.mutexCompras.acquire();
                for(int i = 0; i < valorGasto; i++){
                    if(this.compras[0] != 0){
                        this.compras[0]--;
                        this.valor++;
                    }
                    else{
                        finalizarProcesso = true;
                    }
                }
                this.mutexCompras.release();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }



    public void run(){
        try {
            for(int i = 0; i < etapas[0]; i++){
                this.rendevouz();
                mutex.acquire();
                this.contador[0]++;
                if(this.contador[0] == this.n[0]){
                    System.out.println();
                    this.semaforo2.acquire();  
                    this.semaforo1.release(); 
                }
                this.mutex.release();
                this.semaforo1.acquire();
                this.semaforo1.release();
                this.prontoCritico();
                System.out.println("Thread dormiu por: " + this.tempoDeSonoThread + " valorGasto: " +this.valorGasto +
                        "\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");


                this.mutex.acquire();
                this.contador[0]--;
                if(this.contador[0] == 0){
                    this.semaforo1.acquire(); 
                    this.semaforo2.release(); 
                    System.out.println();
                }
                this.mutex.release();
                this.semaforo2.acquire();
                this.semaforo2.release();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}