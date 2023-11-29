import java.io.*;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

public class Arquivo {
    public static void escreverArquivo(String caminho, String texto) {
        try (
                FileWriter arquivo = new FileWriter(caminho, false);
                BufferedWriter buffer = new BufferedWriter(arquivo);
                PrintWriter print = new PrintWriter(buffer))
        {
            print.append(texto);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static double[][] lerArquivo(String nomeArquivo) throws Exception {
        BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo));
        Scanner scanner = new Scanner(new FileReader(nomeArquivo));

        int j = 0;
        int i = 0;

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            j = 0;
            for (String numero : linha.split(" ")) {
                j += 1;
            }
            i += 1;
        }


        double matriz[][] = new double[i][j];

        Scanner scanner1 = new Scanner(new FileReader(nomeArquivo));
        j = 0; i = 0;
        while (scanner1.hasNextLine()) {
            String line = scanner1.nextLine();
            j = 0;
            for (String numero : line.split(" ")) {
                matriz[i][j] = Double.parseDouble(numero);
                j += 1;
            }
            i += 1;
        }

        return matriz;
    }


}