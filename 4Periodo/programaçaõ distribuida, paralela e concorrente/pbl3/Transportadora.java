import java.util.Random;
import java.util.concurrent.Semaphore;

public class Transportadora extends Thread{
    char nomeTransportadora;
    private Semaphore tempo, produtos2, controleEntregas;
    FilaDeEntrega entregas;

    public Semaphore valorEntregas;

    public Transportadora(char nomeTransportadora, FilaDeEntrega entregas, Semaphore tempo, Semaphore produtos2, Semaphore controleEntregas){
        this.nomeTransportadora = nomeTransportadora;
        this.entregas = entregas;
        this.tempo = tempo;
        this.produtos2 = produtos2;
        this.controleEntregas = controleEntregas;

        if (this.nomeTransportadora == 'A') {
            valorEntregas = new Semaphore(1);
        } else {
            valorEntregas = new Semaphore(4);
        }
    }


    public void run(){

        Random random = new Random();
        while(true){
            try {
                Thread.sleep(random.nextInt(5000));

                produtos2.acquire();
                controleEntregas.acquire();
                Entrega entregaEmExecucao = entregas.entregas.get(0);
                entregas.entregas.remove(entregas.entregas.get(0));
                controleEntregas.release();

                new Transporte(this, entregaEmExecucao).start();

                System.out.println("Saiu para a entrega da transportadora: " + this.nomeTransportadora + "\n" + "=================");

                System.out.println("Produto: " + entregaEmExecucao.venda.produtoNome + ", Pedido Número: " + entregaEmExecucao.venda.loja.contadorVendas +  entregaEmExecucao.venda.loja.nomeDaLoja + "\n" + "=================");

                tempo.release();

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
