public class Prim {
    static class Grafo {
        int vertices;
        int matriz[][];

        public Grafo(int vertices) {
            this.vertices = vertices;
            matriz = new int[vertices][vertices];
        }

        public void adicionarVertice(int origem, int destino, int peso) {
            matriz[origem][destino] = peso;
            matriz[destino][origem] = peso;
        }

        class Resultado {
            int noPai;
            int peso;
        }

        int minimoVertice(boolean[] arvoreGeradoraMinima, int[] pesos) {
            int minimoPeso = Integer.MAX_VALUE;
            int vertice = -1;

            for (int i = 0; i < vertices; i++) {
                if (arvoreGeradoraMinima[i] == false && minimoPeso > pesos[i]) {
                    minimoPeso = pesos[i];
                    vertice = i;
                }
            }
            return vertice;
        }

        public void arvoreGeradoraMinima() {
            boolean[] arvoreGeradoraMinima = new boolean[vertices];
            int[] pesos = new int[vertices];
            Resultado[] resultado = new Resultado[vertices];

            for (int i = 0; i < vertices; i++) {
                pesos[i] = Integer.MAX_VALUE;
                resultado[i] = new Resultado();
            }
            pesos[0] = 0;
            resultado[0].noPai = -1;
            for (int i = 0; i < vertices; i++) {
                int vertice = minimoVertice(arvoreGeradoraMinima, pesos);
                arvoreGeradoraMinima[vertice] = true;
                for (int j = 0; j < vertices; j++) {
                    if (matriz[vertice][j] > 0) {
                        if (!arvoreGeradoraMinima[j] && matriz[vertice][j] < pesos[j]) {
                            pesos[j] = matriz[vertice][j];
                            resultado[j].noPai = vertice;
                            resultado[j].peso = pesos[j];
                        }
                    }
                }
            }
            printarArvoreGeradoraMinima(resultado);
        }

        public void printarArvoreGeradoraMinima(Resultado[] resultado) {
            int custoMinimo = 0;;
            for (int i = 1; i < vertices; i++) {
                System.out.println("Aresta " + i + " -> " + resultado[i].noPai + " peso: " + resultado[i].peso);
                custoMinimo += resultado[i].peso;
            }
            System.out.println("Custo minimo: " + custoMinimo);
        }
    }
}
