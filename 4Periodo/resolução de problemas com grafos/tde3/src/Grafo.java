import java.util.ArrayList;

public class Grafo<T> {
    public ArrayList<Vertice<T>> verticesList;
    public ArrayList<Aresta<T>> arestasList;

    //Montar uma matriz boleana para o tde_3 temporaria. para poder fazer o algoritimo Warshall
    public Grafo() {
        verticesList = new ArrayList<Vertice<T>>();
        arestasList = new ArrayList<Aresta<T>>();
    }

    public void cria_adj(Double peso, T dadoInicio, T dadoFim) {
        Vertice<T> inicio = this.obtemVertice(dadoInicio);
        Vertice<T> fim = this.obtemVertice(dadoFim);

        Aresta<T> aresta = new Aresta<>(peso, inicio, fim);

        inicio.adicionarArestaSaida(aresta);
        fim.adicionarArestaSaida(aresta);

        arestasList.add(aresta);

    }

    
    void remove_adjacencia(T dadoIncial, T dadoFinal){
        Vertice<T> inicio = this.obtemVertice(dadoIncial);
        Vertice<T> fim = this.obtemVertice(dadoFinal);

        for (int i = 0; i < arestasList.size(); i++) {
            if ((arestasList.get(i).inicio.dado == inicio.dado) && (arestasList.get(i).fim.dado == fim.dado)) {
                arestasList.remove(i);
            }
            
        }
    }
    

    public void imprime_adjacencias() {
        for (int i = 0; i < verticesList.size(); i++) {
            System.out.print(verticesList.get(i).dado);

            var arestaAnalisada =  verticesList.get(i).arestaSaida;
            
            for (int j = 0; j < arestaAnalisada.size(); j++) {

                if (arestaAnalisada.get(j).inicio.dado.equals(verticesList.get(i).dado)) {
                    System.out.print(" - " + arestaAnalisada.get(j).fim.dado);    
                } else {
                    System.out.print(" - " + arestaAnalisada.get(j).inicio.dado);
                }            
            }
            System.out.println();
        }
    }

    
    void seta_informação(T dado, T informacao){
        this.obtemVertice(dado).informacao = informacao;
    }

    

    public int adjacentes(T dado, ArrayList<Integer> adj){

        Vertice<T> temp = this.obtemVertice(dado);
        var listaAdj = temp.arestaSaida;
        int contador = 0;
        for (int i = 0; i < listaAdj.size(); i++) {
            contador++;
        }

        adj.add(contador);

        return contador;
    }

    //Os metodos abaixo são para facilitar alguns processos repetitivos
    public void adicionarVertice(T dado) {
        var newVertice = new Vertice<T>(dado);
        this.verticesList.add(newVertice);
    }

    private Vertice<T> obtemVertice(T dado) {
        Vertice<T> vertice = null;
        for (int i = 0; i < this.verticesList.size(); i++) {
            if (this.verticesList.get(i).dado.equals(dado)) {
                vertice = this.verticesList.get(i);
                break;
            }
        }

        return vertice;
    }

    //Mostra todos os vertices
    public void print() {
        ArrayList<Vertice<T>> marcados = new ArrayList<Vertice<T>>();
        ArrayList<Vertice<T>> fila = new ArrayList<Vertice<T>>();

        Vertice<T> atual = this.verticesList.get(0);
        marcados.add(atual);
        System.out.print(atual.dado + " -> ");
        fila.add(atual);

        while (fila.size() > 0) {
            Vertice<T> visitado = fila.get(0);
            for (int i = 0; i < visitado.arestaSaida.size(); i++) {
                //Andar no grafo
                Vertice<T> proximo = visitado.arestaSaida.get(i).fim;
                if (!marcados.contains(proximo)) {
                    marcados.add(proximo);
                    System.out.print(proximo.dado + " -> ");
                    fila.add(proximo);
                }
            }
            fila.remove(0);
        }
    }
}
