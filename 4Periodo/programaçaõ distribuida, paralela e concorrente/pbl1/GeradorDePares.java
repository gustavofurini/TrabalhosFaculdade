import java.util.Random;
import java.util.concurrent.Semaphore;

public class GeradorDePares extends Thread{
    private int[] chave;
    private Semaphore mutex;
    private int qntd_pares;
    private Contador contGlobal;

    public GeradorDePares(int[] chave, Semaphore mutex, Contador contGlobal) {
        this.chave = chave;
        this.mutex = mutex;
        this.qntd_pares = 0;
        this.contGlobal = contGlobal;
    }
    public void run() {
        try {
            boolean ha_espaco = true;
            while(ha_espaco){
                this.mutex.acquire();
                if(this.contGlobal.getContador()<100){
                    Random r = new Random();

                    int par = r.nextInt(8);
                    if (par % 2 == 1) par++;

                    this.qntd_pares++;
                    this.chave[this.contGlobal.getContador()] = par;
                    this.contGlobal.contar();

                    Thread.sleep(r.nextInt(2000) + 1000);

                }else{
                    ha_espaco = false;
                }
                mutex.release();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public int getQntd(){ return qntd_pares; }
}

