import static java.lang.Integer.valueOf;

public class Ceasa {
    private int qtdProdutos;
    private int maxProdutos;
    private boolean filaEntrega;
    private boolean filaPedido;

    public Ceasa(int maxProdutos) {
        this.maxProdutos = maxProdutos;
    }

    public synchronized int pedirProduto(int produtos) throws InterruptedException{
        int totalprodutos = valueOf(produtos);

        while(filaPedido) wait();
        filaPedido = true;
        while(produtos > 0){
            while(qtdProdutos == 0) wait();
            if(qtdProdutos - produtos < 0){
                produtos -= qtdProdutos;
                qtdProdutos = 0;
            }else {
                qtdProdutos -= produtos;
                produtos = 0;
            }
        }
        filaPedido = false;
        notifyAll();
        return totalprodutos;
    }

    public synchronized int entregarProduto(int produtos) throws InterruptedException{
        int totalprodutos = valueOf(produtos);

        while(filaEntrega) wait();
        filaEntrega = true;
        while(produtos > 0){
            while(qtdProdutos == maxProdutos) wait();
            if(qtdProdutos + produtos > maxProdutos){
                produtos -= maxProdutos - qtdProdutos;
                qtdProdutos = maxProdutos;
            }else {
                qtdProdutos += produtos;
                produtos = 0;
            }
        }
        filaEntrega = false;
        notifyAll();
        return totalprodutos;
    }
}
