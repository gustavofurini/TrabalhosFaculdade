import java.util.concurrent.Semaphore;
import java.util.Random;

public class Fabricante extends Thread{
    char nomeFabricante;
    public Semaphore valorFabricacao;
    private Semaphore produtos, controleVendas, produtos2, controleEntregas;
    private FilaDeVenda vendas;
    private FilaDeEntrega entregas;


    public Fabricante(FilaDeVenda vendas, FilaDeEntrega entregas, Semaphore controleVendas, Semaphore produtos, Semaphore produtos2, Semaphore controleEntregas){
        this.vendas = vendas;
        this.entregas = entregas;
        this.controleVendas = controleVendas;
        this.produtos = produtos;
        this.produtos2 = produtos2;
        this.controleEntregas = controleEntregas;

        if (this.nomeFabricante == 'B') {
            valorFabricacao = new Semaphore(1);
        } else {
            valorFabricacao = new Semaphore(4);
        }

    }

    public void run(){
        Random random = new Random();
        while(true){
            try {
                Thread.sleep(random.nextInt(3000));

                produtos.acquire();
                controleVendas.acquire();
                Venda novaVenda = vendas.vendas.get(0);
                vendas.vendas.remove(vendas.vendas.get(0));
                controleVendas.release();

                Thread.sleep(random.nextInt(3000));

                this.valorFabricacao.acquire();
                new Fabricacao(this, novaVenda).start();


                Entrega entrega = new Entrega();
                entrega.numeroEntrega++;
                entrega.venda = novaVenda;

                controleEntregas.acquire();
                entregas.entregas.add(entrega);
                System.out.println("Fila de Entrega: " + entregas.entregas.size() + "\n" + "=================");
                controleEntregas.release();

                Thread.sleep(random.nextInt(3000));

                System.out.println("Retirada produto" + "\n" + "=================");

                produtos2.release();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
