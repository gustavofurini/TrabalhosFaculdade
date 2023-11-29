
public class Main {

    public static void main(String[] args) {
        // Instanciando o grafo
        Grafo grafo = new Grafo(5);

        //Criando pontos de adjacencia
        grafo.cria_adjacencia(2, 1, 2);
        grafo.cria_adjacencia(3, 3, 5);
        grafo.cria_adjacencia(3, 0, 9);
        grafo.cria_adjacencia(1, 1, 5);
        grafo.cria_adjacencia(4, 1, 5);
        grafo.imprime_adjacencias();

        System.out.println("Adjacencias na linha "+ 0 + ": " + grafo.adjacentes(0));
        System.out.println("Adjacencias na linha "+ 1 + ": " + grafo.adjacentes(1));
        System.out.println("Adjacencias na linha "+ 2 + ": " + grafo.adjacentes(2));
        System.out.println("Adjacencias na linha "+ 3 + ": " + grafo.adjacentes(3));
        System.out.println("Adjacencias na linha "+ 4 + ": " + grafo.adjacentes(4) + "\n\n");
        
    }

}
