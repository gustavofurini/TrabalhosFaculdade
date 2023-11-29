import java.util.ArrayList;

public class Vertice<T> {
    public T dado;
    public T informacao;
    public ArrayList<Aresta<T>> arestaEntrada;
    public ArrayList<Aresta<T>> arestaSaida;

    public Vertice(T valor) {
        this.dado = valor;
        arestaEntrada = new ArrayList<Aresta<T>>();
        arestaSaida = new ArrayList<Aresta<T>>();
    }

    public void adicionarArestaEntrada(Aresta<T> aresta){
        arestaEntrada.add(aresta);
    }

    public void adicionarArestaSaida(Aresta<T> aresta){
        arestaSaida.add(aresta);
    }
}
