import java.util.ArrayList;

//Trabalho TDE para Grafos
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("ESTUDOS DE GRAFO");

        ArrayList<Integer> adj = new ArrayList<Integer>();

        Grafo<String> grafo = new Grafo<String>();
        grafo.adicionarVertice("A");
        grafo.adicionarVertice("B");
        grafo.adicionarVertice("C");
        grafo.adicionarVertice("D");

        grafo.seta_informação("A", "11");
        grafo.seta_informação("B", "12");
        grafo.seta_informação("C", "13");
        grafo.seta_informação("D", "14");
        
        grafo.cria_adj(2.0, "A", "B");
        grafo.cria_adj(2.0, "A", "C");
        grafo.cria_adj(1.0, "D", "A");

        //grafo.imprime_adjacencias();

        grafo.adjacentes("A", adj);
        grafo.adjacentes("B", adj);


        for (int i = 0; i < grafo.arestasList.size(); i++) {
            System.out.println("Arestas: " + grafo.arestasList.get(i).inicio.dado + "-" + grafo.arestasList.get(i).fim.dado);
        }

        System.out.println("---------------------------");
        grafo.remove_adjacencia("A", "C");

        for (int i = 0; i < grafo.arestasList.size(); i++) {
            System.out.println("Arestas: " + grafo.arestasList.get(i).inicio.dado + "-" + grafo.arestasList.get(i).fim.dado);
        }

        /*
        //Mostrando todos os vertices do grafo.
        for (int i = 0; i < grafo.verticesList.size(); i++) {
            System.out.println("Vertice/Nó: " + grafo.verticesList.get(i).dado);
        }
        */

        /*
        //Mostrando todas as informações do grafo
        for (int i = 0; i < grafo.verticesList.size(); i++) {
            System.out.println("Informação: " + grafo.verticesList.get(i).informacao);
        }
        */
        
        
        /*
        //Mostra adjacentes
        for (int i = 0; i < adj.size(); i++) {
            System.out.print(adj.get(i) + " ");
        }
        */

        //grafo.print();
        
        
    }
}
