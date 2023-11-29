import java.util.Random;

public class GerarNumero {

    private static Random numero = new Random();

    public static int gerar(int numeroAleatorio) {
        return numero.nextInt(numeroAleatorio + 1);
    }
}