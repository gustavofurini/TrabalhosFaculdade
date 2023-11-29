
public class Ceasa {
    public int quantidadeProdutos;
    public int maximoProdutos;
    public boolean filaEntrega;
    public boolean filaPedido;

    public Ceasa(int maximoProdutos) {
        this.maximoProdutos = maximoProdutos;
    }

    public synchronized int pedirProduto(int produtos) throws InterruptedException{
        int totalprodutos = produtos;

        while(filaPedido) wait();
        filaPedido = true;
        while(produtos > 0){
            while(quantidadeProdutos == 0) wait();
            if(quantidadeProdutos - produtos < 0){
                produtos = produtos - quantidadeProdutos;
                quantidadeProdutos = 0;
            }else {
                quantidadeProdutos = quantidadeProdutos - produtos;
                produtos = 0;
            }
        }
        filaPedido = false;
        notifyAll();
        return totalprodutos;
    }

    public synchronized int entregarProduto(int produtos) throws InterruptedException{
        int quantidadeTotalProdutos = produtos;

        while(filaEntrega) wait();
        filaEntrega = true;
        while(produtos > 0){
            while(quantidadeProdutos == maximoProdutos) wait();
            if(quantidadeProdutos + produtos > maximoProdutos){
                produtos -= maximoProdutos - quantidadeProdutos;
                quantidadeProdutos = maximoProdutos;
            }else {
                quantidadeProdutos = quantidadeProdutos + produtos;
                produtos = 0;
            }
        }
        filaEntrega = false;
        notifyAll();
        return quantidadeTotalProdutos;
    }
}
