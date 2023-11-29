public class Matriz {
    private double matriz[][];

    public Matriz(double matriz[][]){
        this.matriz = matriz;
    }

    public double[] coluna(int indexColuna){
        double linha[] = new double[this.matriz.length];
        for (int i = 0; i<linha.length; i++) linha[i] = this.matriz[i][indexColuna];
        return linha;
    }

    public double[] linha(int indexLinha){
        double[] coluna = new double[this.matriz[indexLinha].length];
        for (int i = 0; i<coluna.length; i++) coluna[i] = this.matriz[indexLinha][i];
        return coluna;
    }

}