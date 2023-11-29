

import java.util.Random;


public class ProdutorBanana extends Thread {
    public String valor;
    public BufferLimitado buffer;
    public Random aleatorio;
    int valorMaximo;

    public ProdutorBanana(BufferLimitado buffer, int valor)
    {
        this.valor = "Produtor de Banana" + valor;
        this.buffer = buffer;
        aleatorio = new Random();
    }

    public void run() {
        try {
            int gera = aleatorio.nextInt(99) + 1;
            buffer.remover(gera, valor);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

