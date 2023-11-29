import java.io.*;
import java.util.*;
import java.util.concurrent.Semaphore;

public class Trabalhadora extends PrintarThread {

    private final ArrayList<String> listaArquivos;
    private final Semaphore barreiraEntrada;
    private final Semaphore barreiraSaida;
    private final  Semaphore mutexInsercaoLista;
    private final  Semaphore mutexContador;
    private final Semaphore mutexCombinadora;
    private final Semaphore mutexContador2;

    public Trabalhadora(ArrayList<String> lista,
                        Semaphore barreiraEntrada,
                        Semaphore barreiraSaida,
                        Semaphore mutexInsercaoLista,
                        Semaphore mutexContador,
                        Semaphore mutexCombinadora,
                        Semaphore mutexContador2) {

        this.listaArquivos = lista;

        this.barreiraEntrada = barreiraEntrada;
        this.barreiraSaida = barreiraSaida;
        this.mutexInsercaoLista = mutexInsercaoLista;
        this.mutexContador = mutexContador;
        this.mutexCombinadora = mutexCombinadora;
        this.mutexContador2 = mutexContador2;
    }

    public void run() {
        try {
            while (true) {
                barreiraEntrada();

                Lista listaDesordenada = new Lista();

                listaDesordenada.popular();

                mutexContador2.acquire();
                Main.contador2++;
                criarArquivo(listaDesordenada, "desordenado ->" + Main.contador2);
                listaDesordenada.ordenar();
                String nomeOrdenado = criarArquivo(listaDesordenada, "ordenado -> " + Main.contador2);
                mutexContador2.release();

                threadPrint("Arquivo criado");
                inserirNaFila(nomeOrdenado);

                barreiraSaida();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void inserirNaFila(String nomeOrdenado) {
        try {
            threadPrint("Aguardando o nome ser inserido.");

            mutexInsercaoLista.acquire();

            listaArquivos.add(nomeOrdenado);

            mutexInsercaoLista.release();

            threadPrint("Arquivo inserido na lista");

        } catch (Exception e) {
            threadPrint("Erro!");
        }
    }

    public void barreiraEntrada() {
        try {
            mutexContador.acquire();

            Main.contador++;
            threadPrint("Thread na entrada");

            if (Main.contador == Main.MAX_TRABALHADORAS) {

                threadPrint("fechou... abriu");
                barreiraSaida.acquire(); //fecha
                barreiraEntrada.release(); //abre
            }
            mutexContador.release();
            barreiraEntrada.acquire();
            barreiraEntrada.release();

        } catch (Exception e) {
            threadPrint("Erro!");
        }

    }

    public void barreiraSaida() {
        try {
            mutexContador.acquire();
            Main.contador -= 1;

            threadPrint("Thread na saida");
            mutexCombinadora.release();
            threadPrint(mutexCombinadora.toString());

            if (Main.contador == 0) {
                barreiraEntrada.acquire();
                barreiraSaida.release();
            }
            mutexContador.release();

            barreiraSaida.acquire();
            barreiraSaida.release();

        } catch (Exception e) {
            threadPrint("Erro!");
        }

    }

    private String criarArquivo(Lista arquivo, String Nome) throws IOException {
        String saida = String.format("%s_%s arquivo .txt", this.getNome(), Nome);
        Arquivo.salvar(saida, arquivo);
        return saida;
    }


}