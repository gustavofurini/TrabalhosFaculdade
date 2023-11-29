import java.util.Random;

public class ProdutorMaca extends Thread {
    private String N;
    private BufferLimitado buffer;
    private Random aleatorio;


    public ProdutorMaca(BufferLimitado buffer, int N)
    {
        this.N = "ProdutorMaça" + N;
        this.buffer = buffer;
        aleatorio = new Random();
    }

    public void run() {

        try {
            int gera = aleatorio.nextInt(99) + 1;
            buffer.inserir(gera, N);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
