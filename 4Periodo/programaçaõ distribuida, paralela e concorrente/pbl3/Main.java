

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {

        FilaDeVenda vendas = new FilaDeVenda();
        FilaDeEntrega entregas = new FilaDeEntrega();


        Semaphore produtos = new Semaphore( 0 );
        Semaphore produtos2 = new Semaphore( 0 );
        Semaphore tempo = new Semaphore( 100 );
        Semaphore controleVendas = new Semaphore( 1 );
        Semaphore controleEntregas = new Semaphore( 1 );



        //LOJAS
        Loja LojaA = new Loja(vendas, controleVendas, produtos);
        Loja LojaB = new Loja(vendas, controleVendas, produtos);
        Loja LojaC = new Loja(vendas, controleVendas, produtos);
        Loja LojaD = new Loja(vendas, controleVendas, produtos);
        Loja LojaE = new Loja(vendas, controleVendas, produtos);
        Loja LojaF = new Loja(vendas, controleVendas, produtos);
        Loja LojaG = new Loja(vendas, controleVendas, produtos);
        Loja LojaH = new Loja(vendas, controleVendas, produtos);

        LojaA.nomeDaLoja = 'A';
        LojaB.nomeDaLoja = 'B';
        LojaC.nomeDaLoja = 'C';
        LojaD.nomeDaLoja = 'D';
        LojaE.nomeDaLoja = 'E';
        LojaF.nomeDaLoja = 'F';
        LojaG.nomeDaLoja = 'G';
        LojaH.nomeDaLoja = 'H';

        //TRANSPORTADORES
        Transportadora TransportadoraA = new Transportadora('A', entregas, tempo, produtos2, controleEntregas);
        Transportadora TransportadoraB = new Transportadora('B', entregas, tempo, produtos2, controleEntregas);

        //FABRICANTES
        Fabricante FabricanteA = new Fabricante(vendas, entregas, controleVendas, produtos, produtos2, controleEntregas);
        Fabricante FabricanteB = new Fabricante(vendas, entregas, controleVendas, produtos, produtos2, controleEntregas);
        Fabricante FabricanteC = new Fabricante(vendas, entregas, controleVendas, produtos, produtos2, controleEntregas);
        Fabricante FabricanteD = new Fabricante(vendas, entregas, controleVendas, produtos, produtos2, controleEntregas);


        //INICIALIZANDO THREADS
        LojaA.start();
        LojaB.start();
        LojaC.start();
        LojaD.start();
        LojaE.start();
        LojaF.start();
        LojaG.start();
        LojaH.start();

        FabricanteA.start();
        FabricanteB.start();
        FabricanteC.start();
        FabricanteD.start();

        TransportadoraA.start();
        TransportadoraB.start();


    }

}

