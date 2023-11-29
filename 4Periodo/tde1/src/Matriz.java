
import java.util.ArrayList;

public class Matriz {

    public ArrayList<ArrayList<Double>> matriz;
    public int length;
    public Double valorAdjacencia = Double.POSITIVE_INFINITY;

    
    //Construtor sem parametro
    public Matriz() {
        matriz = new ArrayList<>();
        matriz.add(new ArrayList<>());
        matriz.get(0).add(0.0);
        length = matriz.size();
    }

    
    //Construtor com parametro size
    public Matriz(int size) {
        matriz = new ArrayList<>();
        matriz.add(new ArrayList<>());
        matriz.get(0).add(0.0);
        length = matriz.size();
        upSize(size - 1);
    }

    // Valor da adjacencia
    public Double getValorAdjacencia() {

        return valorAdjacencia;
    }

    // Quantidade de adjacencia
    public int quantidadeAdjacencias(int y) {
        int cont = 0;
        for (int x = 0; x < matriz.size(); x++) {
            Double value = matriz.get(y).get(x);
            if (value != getValorAdjacencia() && value != 0) {
                cont++;
            }
        }
        return cont;
    }

    //Indice no vetor da adjacencia
    public Double getAdjacencyOf(int y, int x) {

        return matriz.get(y).get(x);
    }

    //Valor da adjacencia
    public void setValorAdjacencia(Double value) {
        updateValorAdjacencia(value);
        this.valorAdjacencia = value;
    }

    //Adiciona um valor numa posição x y
    public void setOn(int x, int y, Double value) {
        matriz.get(y).set(x, value);
    }

    //Adjacencia
    public void adjacencia(int x, int y, Double value) {
        if (x != y) {
            matriz.get(y).set(x, value);
            matriz.get(x).set(y, value);
        }
    }


    public void upSize() {

        ArrayList<Double> aux = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            aux.add(valorAdjacencia);
        }
        length++;
        matriz.add(aux);

        for (int i = 0; i < length; i++) {
            matriz.get(i).add(valorAdjacencia);
        }
        matriz.get(length - 1).set(length - 1, 0.0);
    }

    
    public void upSize(int length) {
        for (int i = 0; i < length; i++) {
            upSize();
        }
    }

    //Reseta a adjacencia
    public void resetAdjacency() {
        for (int i = 0; i < matriz.size(); i++) {
            for (int j = 0; j < matriz.size(); j++) {
                if (matriz.get(i).get(j) != 0.0) {
                    matriz.get(i).set(j, valorAdjacencia);
                }
            }
        }
    }

    //Remove adjacencia numa posição x y
    public void removeAdjacencia(int x, int y) {
        matriz.get(y).set(x, valorAdjacencia);
    }

    //Reseta a matriz
    public void reset() {
        matriz = new ArrayList<>();
        matriz.add(new ArrayList<>());
        matriz.get(0).add(valorAdjacencia);
        length = 0;
    }


    public void updateValorAdjacencia(Double newValue) {
        Double oldValue = this.valorAdjacencia;
        for(int i = 0; i < matriz.size(); i++) {
            for(int j = 0; j < matriz.size(); j++) {
                if(matriz.get(j).get(i) == oldValue) {
                    matriz.get(j).set(i, newValue);
                }
            }
        }
    }

    //Imprime a matriz
    public void print() {
        //System.out.printf("length: %d\n", length);
        System.out.println("----MATRIZ ADJACENCIA----");
        System.out.println("       Tamanho: " + length + "\n");
        System.out.println("+++++++++++++++++++++++++");
        String numberTable = "|   | ";
        for (int i = 0; i < matriz.size(); i++) {
            numberTable += i + " | ";
        }
        System.out.println(numberTable);
        for (int i = 0; i < matriz.size(); i++) {
            System.out.printf("| %d | ", i);
            for (int j = 0; j < matriz.get(i).size(); j++) {
                Double value = matriz.get(i).get(j);
                if (value == valorAdjacencia) {
                    System.out.printf("x | ");
                } else {
                    System.out.printf("%.0f | ", value);
                }
            }
            System.out.println();
        }
        System.out.println("+++++++++++++++++++++++++\n");
    }

}
