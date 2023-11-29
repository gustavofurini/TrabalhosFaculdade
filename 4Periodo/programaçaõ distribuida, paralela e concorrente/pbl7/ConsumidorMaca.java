

import java.util.Random;

public class ConsumidorMaca extends Thread {
    private String valor;
    private BufferLimitado buffer;
    private Random aleatorio;

    public ConsumidorMaca(BufferLimitado buffer, int valor)
    {
        this.valor = "Consumidor de Maça" + valor;
        this.buffer = buffer;
        aleatorio = new Random();
    }

    public void run() {
        try {
            int gera = aleatorio.nextInt(200);
            buffer.remover(gera, valor);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}