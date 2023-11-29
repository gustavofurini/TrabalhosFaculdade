import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Semaphore;

public class Main {

    public static final int MAX_TRABALHADORAS = 4;
    public static int contador = 0;
    public static int contador2 = 0;

    public static void main(String[] args) {

        Semaphore mutexInsercao = new Semaphore(1);
        Semaphore barreiraEntrada = new Semaphore(0);
        Semaphore barreiraSaida = new Semaphore(1);
        Semaphore semaforoCombinadora = new Semaphore(0);
        Semaphore mutexContador2 = new Semaphore(1);
        Semaphore mutexContador = new Semaphore(1);



        ArrayList<String> listaDeArquivos = new ArrayList<>();

        Trabalhadora ThreadTrab1 =
                new Trabalhadora(listaDeArquivos, barreiraEntrada,
                        barreiraSaida, mutexInsercao, mutexContador, semaforoCombinadora, mutexContador2);
        Trabalhadora ThreadTrab2 =
                new Trabalhadora(listaDeArquivos, barreiraEntrada,
                        barreiraSaida, mutexInsercao, mutexContador, semaforoCombinadora, mutexContador2);
        Trabalhadora ThreadTrab3 =
                new Trabalhadora(listaDeArquivos, barreiraEntrada,
                        barreiraSaida, mutexInsercao, mutexContador, semaforoCombinadora, mutexContador2);
        Trabalhadora ThreadTrab4 =
                new Trabalhadora(listaDeArquivos, barreiraEntrada,
                        barreiraSaida, mutexInsercao, mutexContador, semaforoCombinadora, mutexContador2);

        Combinadora combinadora = new Combinadora(listaDeArquivos, semaforoCombinadora);

        ThreadTrab1.start();
        ThreadTrab2.start();
        ThreadTrab3.start();
        ThreadTrab4.start();
        combinadora.start();
    }

}