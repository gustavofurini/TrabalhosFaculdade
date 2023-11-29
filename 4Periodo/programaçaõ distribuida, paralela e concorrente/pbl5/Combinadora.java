import java.io.*;
import java.util.*;
import java.util.concurrent.Semaphore;
public class Combinadora extends PrintarThread {

    private final ArrayList<String> listaDeArquivos;
    private final Semaphore semaforoCombinadora;

    public Combinadora(ArrayList<String> listaDeArquivos, Semaphore semaforoCombinadora) {
        this.listaDeArquivos = listaDeArquivos;
        this.semaforoCombinadora = semaforoCombinadora;
    }

    public void run() {
        try {
            while (true) {

                semaforoCombinadora.acquire(4);

                threadPrint("\nThread combinatoria em execução");
                Lista todosDadosArquivos = carregarArquivos();
                listaDeArquivos.clear();
                criarArquivo(todosDadosArquivos, "Merge:" + Main.contador2);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void criarArquivo(Lista arquivo, String nome) throws IOException {
        String output = String.format("%s.txt",  nome);
        Arquivo.salvar(output, arquivo);
    }

    public Lista carregarArquivos() throws IOException {
        Lista total = new Lista();
        for (String nome : listaDeArquivos) {
            threadPrint(nome + " carregando...");
            Lista arquivo = Arquivo.abrirArquivo(nome);
            total.getList().addAll(arquivo.getList());
        }
        total.ordenar();
        return total;
    }



}