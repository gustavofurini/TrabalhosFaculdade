
import java.util.concurrent.Semaphore;
import java.util.Random;
public class Loja extends Thread{
    public char nomeDaLoja;
    public int contadorVendas;
    private Semaphore mutex, itens;
    public FilaDeVenda vendas;
    public Random produtoAleatorio = new Random();


    public Loja(FilaDeVenda vendas, Semaphore mutex, Semaphore itens){
        this.vendas = vendas;
        this.mutex = mutex;
        this.itens = itens;
    }

    public char produtoAleatorio() {
        String valores = "ABCDEFGH";
        char nomeProduto = valores.charAt(produtoAleatorio.nextInt(8));
        return nomeProduto;
    }

    public void run(){

        while (true){
            try {

                Venda venda = new Venda(produtoAleatorio(), this);
                contadorVendas++;

                mutex.acquire();
                vendas.vendas.add(venda);
                mutex.release();
                itens.release();

                Thread.sleep(1000);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
