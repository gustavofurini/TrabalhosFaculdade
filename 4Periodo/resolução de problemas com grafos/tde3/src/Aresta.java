public class Aresta<T> {
    public Double peso;
    public Vertice<T> inicio;
    public Vertice<T> fim;
    
    public Aresta(Double peso, Vertice<T> inicio, Vertice<T> fim) {
        this.peso = peso;
        this.inicio = inicio;
        this.fim = fim;
    }
}
