
import java.util.Random;

public class Fabricacao extends Thread {
    Fabricante fabricante;
    Venda venda;

    public Fabricacao(Fabricante fabricante, Venda venda) {
        this.fabricante = fabricante;
        this.venda = venda;
    }

    public void producao(Venda venda){

        Random tempoDeEntrega = new Random();
        try {
            switch (this.fabricante.nomeFabricante) {
                case 'A':
                    switch (this.venda.produtoNome) {
                        case 'A' : sleep(tempoDeEntrega.nextInt(400) + 600);break;
                        case 'B' : sleep(tempoDeEntrega.nextInt(200) + 200);break;
                        case 'C' : sleep(tempoDeEntrega.nextInt(200) + 1000);break;
                        case 'D' : sleep(tempoDeEntrega.nextInt(200) + 400);break;
                        case 'E' : sleep(tempoDeEntrega.nextInt(200) + 800);break;
                        case 'F' : sleep(tempoDeEntrega.nextInt(200) + 1400);break;
                        case 'G' : sleep(tempoDeEntrega.nextInt(200) + 400);break;
                        case 'H' : sleep(tempoDeEntrega.nextInt(200) + 800);break;
                    }
                    break;
                case 'B':
                    switch (this.venda.produtoNome) {
                        case 'A' : sleep(tempoDeEntrega.nextInt(200) + 400);break;
                        case 'B' : sleep(tempoDeEntrega.nextInt(200) + 1200);break;
                        case 'C' : sleep(tempoDeEntrega.nextInt(200) + 1000);break;
                        case 'D' : sleep(tempoDeEntrega.nextInt(200) + 800);break;
                        case 'E' : sleep(tempoDeEntrega.nextInt(200) + 200);break;
                        case 'F' : sleep(tempoDeEntrega.nextInt(200) + 1000);break;
                        case 'G' : sleep(tempoDeEntrega.nextInt(200) + 1000);break;
                        case 'H' : sleep(tempoDeEntrega.nextInt(200) + 600);break;
                    }
                    break;
                case 'C':
                    switch (this.venda.produtoNome) {
                        case 'A': sleep(tempoDeEntrega.nextInt(200) + 1000);break;
                        case 'B': sleep(tempoDeEntrega.nextInt(200) + 1000);break;
                        case 'C': sleep(tempoDeEntrega.nextInt(200) + 400);break;
                        case 'D': sleep(tempoDeEntrega.nextInt(200) + 600);break;
                        case 'E': sleep(tempoDeEntrega.nextInt(200) + 400);break;
                        case 'F': sleep(tempoDeEntrega.nextInt(200) + 400);break;
                        case 'G': sleep(tempoDeEntrega.nextInt(200) + 1000);break;
                        case 'H': sleep(tempoDeEntrega.nextInt(200) + 400);break;
                    }
                    break;
                case 'D':
                    switch (this.venda.produtoNome) {
                        case 'A': sleep(tempoDeEntrega.nextInt(200) + 800); break;
                        case 'B': sleep(tempoDeEntrega.nextInt(200) + 600); break;
                        case 'C': sleep(tempoDeEntrega.nextInt(200) + 400); break;
                        case 'D': sleep(tempoDeEntrega.nextInt(200) + 1000); break;
                        case 'E': sleep(tempoDeEntrega.nextInt(200) + 1200) ;break;
                        case 'F': sleep(tempoDeEntrega.nextInt(200) + 800); break;
                        case 'G': sleep(tempoDeEntrega.nextInt(200) + 600); break;
                        case 'H': sleep(tempoDeEntrega.nextInt(200) + 1200); break;
                    }
                    break;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.producao(this.venda);
        this.fabricante.valorFabricacao.release();
    }
}
