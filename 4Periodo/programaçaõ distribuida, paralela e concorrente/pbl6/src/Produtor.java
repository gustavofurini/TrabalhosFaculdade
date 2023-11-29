import java.util.Random;

public class Produtor extends Thread{

        private Ceasa ceasa;
        private int maxEntregas;
        private String nomeProduto;
        private Random rand = new Random();

        public Produtor(Ceasa ceasa, int maxEntregas, String nomeProduto) {
            this.ceasa = ceasa;
            this.maxEntregas = maxEntregas;
            this.nomeProduto = nomeProduto;
        }

        public void run() {
            while (true) {
                try {
                    System.out.println(ceasa.entregarProduto(rand.nextInt(1, maxEntregas)) + " " + nomeProduto + " entregues");
                    Thread.sleep(rand.nextInt(100, 1000));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
}
