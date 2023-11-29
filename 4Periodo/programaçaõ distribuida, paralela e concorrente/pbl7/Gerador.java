
import java.util.Random;

public class Gerador extends Thread{

    public BufferLimitado BufferBanana;
    public BufferLimitado BufferMaca;

    public Gerador(BufferLimitado BufferMaça, BufferLimitado BufferBanana) {
        this.BufferBanana = BufferBanana;
        this.BufferMaca = BufferMaça;
    }

    @Override
    public void run() {

        while (true) {

            Random rand = new Random();
            int aleatorio = rand.nextInt(4);
            int threadDormindo = rand.nextInt(5000);
            Thread thread = new Thread();

            switch (aleatorio) {
                case 1:
                    thread = new ProdutorBanana(BufferBanana, aleatorio);
                    System.out.println("Produtor de Banana");
                    break;
                case 2:
                    thread = new ProdutorMaca(BufferMaca, aleatorio);
                    System.out.println("Produtor de Maça");
                    break;
                case 3:
                    thread = new ConsumidorBanana(BufferBanana, aleatorio);
                    System.out.println("Consumidor de Banana");
                    break;
                case 4:
                    thread = new ConsumidorMaca(BufferMaca, aleatorio);
                    System.out.println("Consumidor de Maça");
                    break;
            }
            thread.start();
            try {
                Thread.sleep(threadDormindo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
