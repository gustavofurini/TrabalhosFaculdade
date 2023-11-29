import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws Exception {
        // QUANTIDADES DE TAREFAS DEPENDE DA QUANTIDADDE DE PROCESSADORES
        matriz(1, 4, 4, "MatrizA.txt", "MatrizB.txt");
        

    }

    public static void matriz(int matriz, int processadores, int qtdTarefas, String arquivo1, String arquivo2) throws Exception {
        double[][] matrizA = Arquivo.lerArquivo(arquivo1);
        double[][] matrizB = Arquivo.lerArquivo(arquivo2);
        double[][] matrizC = multiplicacaoMatriz(matrizA, matrizB, qtdTarefas);
        System.out.println("Matriz A: " + Arrays.deepToString(matrizA));
        System.out.println("Matriz B: " + Arrays.deepToString(matrizB));
        System.out.println("Matriz C:" + Arrays.deepToString(matrizC));
        Arquivo.escreverArquivo("MatrizC.txt", Arrays.deepToString(matrizC));
    }

    public static double[][] multiplicacaoMatriz(double[][] matrizA, double[][] matrizB, int numTarefas) throws InterruptedException {
        double[][] tarefasThread = listaDeTarefas(numTarefas, matrizA, matrizB);

        double[][] matrizC = new double[matrizA.length][matrizB[0].length];
        Semaphore mutex = new Semaphore(1);

        long tempoInicial = System.currentTimeMillis();
        for(int i = 0; i < tarefasThread.length; i++){
            Multiplicacao multiplicacao = new Multiplicacao(matrizA, matrizB, matrizC, tarefasThread[i], mutex);
            multiplicacao.start();

            if(i == tarefasThread.length - 1){
                multiplicacao.join();
            }
        }
        long tempoFinal = System.currentTimeMillis();

        System.out.println("Tempo de execução: " + (tempoFinal - tempoInicial));
        return matrizC;
    }

    public static double[][] listaDeTarefas(int numeroTarefas, double[][] matrizA, double[][] matrizB){
        int elementosMatrizC = matrizA.length * matrizB[0].length;
        double[][] tarefasThread = new double[numeroTarefas][(int) ((elementosMatrizC % numeroTarefas != 0) ? Math.floor(elementosMatrizC / numeroTarefas) + 1 : Math.floor(elementosMatrizC / numeroTarefas))];

        int contador = 0;
        int adicionarValor = 0;

        for(int i = 0; i < numeroTarefas; i++) {
            double limite = (adicionarValor > 0) ? Math.floor(elementosMatrizC / numeroTarefas) + adicionarValor : Math.floor(elementosMatrizC / numeroTarefas);
            adicionarValor = 0;

            if (elementosMatrizC % numeroTarefas != 0){
                adicionarValor++;
            }

            for(int j = 0; j < limite; j++){
                tarefasThread[i][j] = contador;
                contador++;
            }
        }

        return tarefasThread;
    }
}