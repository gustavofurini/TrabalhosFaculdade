import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args){
        Semaphore barreiraEntrada = new Semaphore(0);
        Semaphore barreiraSaida = new Semaphore(1);
        Semaphore mutex = new Semaphore(1);
        int[] contador = new int[1];
        int[] n = new int[1];
        int[] compras = new int[1];
        Semaphore mutexCompras = new Semaphore(1);
        int[] tempoDeSonoThread = new int[1];
        boolean autalizarEstado[] = new boolean[1];
        int[] etapas = new int[1];
        int[] valorGasto = new int[1];
        Semaphore mutexTempoDeSono = new Semaphore(1);

        n[0] = 4;
        compras[0] = n[0]*5;
        etapas[0] = 5;
        valorGasto[0] = 1;

        Pessoa pessoa1 = new Pessoa(barreiraEntrada, barreiraSaida, mutex, contador,  n, compras, mutexCompras, tempoDeSonoThread, autalizarEstado, etapas, valorGasto, mutexTempoDeSono);
        Pessoa pessoa2 = new Pessoa(barreiraEntrada, barreiraSaida, mutex, contador,  n, compras, mutexCompras, tempoDeSonoThread, autalizarEstado, etapas, valorGasto, mutexTempoDeSono);
        Pessoa pessoa3 = new Pessoa(barreiraEntrada, barreiraSaida, mutex, contador,  n, compras, mutexCompras, tempoDeSonoThread, autalizarEstado, etapas, valorGasto, mutexTempoDeSono);
        Pessoa pessoa4 = new Pessoa(barreiraEntrada, barreiraSaida, mutex, contador,  n, compras, mutexCompras, tempoDeSonoThread, autalizarEstado, etapas, valorGasto, mutexTempoDeSono);

        pessoa1.start();
        pessoa2.start();
        pessoa3.start();
        pessoa4.start();


        try
        {
            pessoa1.join();
            pessoa2.join();
            pessoa3.join();
            pessoa4.join();
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}