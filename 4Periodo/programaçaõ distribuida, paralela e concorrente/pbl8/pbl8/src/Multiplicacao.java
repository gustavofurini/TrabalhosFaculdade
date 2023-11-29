import java.util.concurrent.Semaphore;

public class Multiplicacao extends Thread {
    private double[][] matrizA;
    private double[][] matrizB;
    private double[] taskList;
    private double[][] matrizC;
    private Semaphore mutex;

    public Multiplicacao(double[][] matrizA, double[][] matrizB, double[][] matrizC, double[] taskList, Semaphore mutex){
        this.matrizA = matrizA;
        this.matrizC = matrizC;
        this.matrizB = matrizB;
        this.taskList = taskList;
        this.mutex = mutex;
    }

    public void run() {
        Valor[] calculatedList = new Valor[taskList.length];

        Matriz a = new Matriz(matrizA);
        Matriz b = new Matriz(matrizB);

        for(int i = 0; i < taskList.length; i++){
            double elementPosition = taskList[i];
            int[] positionInC = posicaoAtual((int) elementPosition, matrizC);

            double resultado = multiplicacaoLinhaColuna(a.linha(positionInC[0]), b.coluna(positionInC[1]));

            Valor valorAtual = new Valor(resultado, positionInC[0], positionInC[1]);
            calculatedList[i] = valorAtual;
        }

        try {
            mutex.acquire();
            for(Valor valor : calculatedList){
                matrizC[valor.getLinha()][valor.getColuna()] = valor.getValor();
            }
            mutex.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public int[] posicaoAtual(int fim, double[][] matrizC){
        int valor = 0;
        int[] posicao = new int[2];

        for(int i = 0; i < matrizC.length; i++){
            for(int j = 0; j < matrizC[i].length; j++){
                if(valor == fim){
                    posicao[0] = i;
                    posicao[1] = j;

                    return posicao;
                }

                valor++;
            }
        }

        return posicao;
    }

    public double multiplicacaoLinhaColuna(double[] linha, double[] coluna){
        if(linha.length != coluna.length) return -1;

        double resultadoMultiplicacao = 0;

        for (int i = 0; i < linha.length; i++){
            resultadoMultiplicacao += linha[i] * coluna[i];
        }

        return resultadoMultiplicacao;
    }

}