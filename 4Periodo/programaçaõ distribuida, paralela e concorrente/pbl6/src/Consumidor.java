import java.util.Random;

public class Consumidor extends Thread{
    private Ceasa ceasa;
    private int maxPedidos;
    private String nomeProduto;
    private Random rand = new Random();

    public Consumidor(Ceasa ceasa, int maxPedidos, String nomeProduto) {
        this.ceasa = ceasa;
        this.maxPedidos = maxPedidos;
        this.nomeProduto = nomeProduto;
    }

    public void run() {
        while(true){
            try{
                System.out.println(ceasa.pedirProduto(rand.nextInt(1, maxPedidos)) +" " + nomeProduto + " pedidos");
                Thread.sleep(rand.nextInt(100, 1000));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
