public class Main {

    public static void main(String[] args) {
        int vertices = 6;
        Prim.Grafo grafo = new Prim.Grafo(vertices);
        grafo.adicionarVertice(0, 1, 10);
        grafo.adicionarVertice(0, 2, 25);
        grafo.adicionarVertice(1, 4, 30);
        grafo.adicionarVertice(1, 2, 10);
        grafo.adicionarVertice(2, 5, 5);
        grafo.adicionarVertice(2, 3, 20);
        grafo.adicionarVertice(3, 5, 40);
        grafo.adicionarVertice(5, 4, 12);
        grafo.arvoreGeradoraMinima();
    }
}
