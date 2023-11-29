
import java.util.ArrayList;

public class Grafo {

    public ArrayList<No> Nos;
    public Matriz matriz;

    public Grafo() {
        Nos = new ArrayList<>();
        matriz = new Matriz();
    }

    public Grafo(int quantidadeNos) {
        quantidadeNos--;
        Nos = new ArrayList<>();
        matriz = new Matriz();
        for(int i = 0; i < quantidadeNos; i++) {
            adicionaNo();
        }
    }

    public void adicionaNo() {
        var novoNo = new No("0");
        Nos.add(novoNo);
        matriz.upSize();
    }

    public void cria_adjacencia(int i, int j, int P) {
        matriz.adjacencia(i, j, (double)P);
    }

    public void remove_adjacencia(int i, int j) {
        matriz.removeAdjacencia(i, j);
    }

    public void imprime_adjacencias() {
        matriz.print();
    }

    public void seta_informacao(int i, String v) {
        Nos.get(i).valor= v;
    }

    public int adjacentes(int i) {
        return matriz.quantidadeAdjacencias(i);
    }
}
