

import java.util.Random;


public class ConsumidorBanana extends Thread {
    private String valor;
    private BufferLimitado buffer;
    private Random random;


    public ConsumidorBanana(BufferLimitado buffer, int valor) {
        this.valor = "Consumidor de Banana" + valor;
        this.buffer = buffer;
        random = new Random();

    }
    public void run() {
        try {
            int gera = random.nextInt(200);
            buffer.remover(gera, valor);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}