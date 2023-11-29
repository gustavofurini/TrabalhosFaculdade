import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Thread1 extends Thread {

    static ArrayList<Integer> lParte1 = new ArrayList<Integer>();
    static ArrayList<Integer> lParte2 = new ArrayList<Integer>();
    static ArrayList<Integer> lParte3 = new ArrayList<Integer>();
    static ArrayList<Integer> lParte4 = new ArrayList<Integer>();
    static int contadorThreads = 0;
    static int nThreads = 4;

    Semaphore mutex, mutex1, mutex2, mutex3, mutex4, barreira;

    public Thread1(Semaphore mutex, Semaphore mutex1, Semaphore mutex2, Semaphore mutex3, Semaphore mutex4, Semaphore barreira) {
        this.mutex = mutex;
        this.mutex1 = mutex1;
        this.mutex2 = mutex2;
        this.mutex3 = mutex3;
        this.mutex4 = mutex4;
        this.barreira = barreira;
    }

    @Override
    public void run() {
        // Cria todos os funcionários
        int n = 100; // Esse n pode ser passado como parametro
        for (int i = 0; i < n; i++) {
            // Adicionando código a todos os funcionários
            Funcionarios.lFuncionarios.add(new Funcionarios(i));
        }

        // Separando a lista de funcionários em 4 partes
        divideLista4partes();

        Thread2 thread2 = new Thread2(mutex, mutex1, mutex2, mutex3, mutex4, barreira);
        Thread3 thread3 = new Thread3(mutex, mutex1, mutex2, mutex3, mutex4, barreira);
        Thread4 thread4 = new Thread4(mutex, mutex1, mutex2, mutex3, mutex4, barreira);
        Thread5 thread5 = new Thread5(mutex, mutex1, mutex2, mutex3, mutex4, barreira);

        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

    }

    public void divideLista4partes() {
        int particoes = (Funcionarios.lFuncionarios.size() / 4);
        int contador = 0;
        int i = 0;
        int partes = particoes;

        while( contador < 4){
            for ( ;i <= partes; i++) {

                // Adiciona o indice das partes
                if ( contador == 0) {
                    lParte1.add(i);
                } else if( contador == 1) {
                    lParte2.add(i);
                } else if( contador == 2) {
                    lParte3.add(i);
                } else if( contador == 3) {
                    lParte4.add(i);
                }
            }

            contador++;
            i = partes;
            partes = particoes + partes;
        }
    }

    public void ImprimeContraCheque() {
        try {
            Path path = FileSystems.getDefault().getPath("");
            String directoryName = path.toAbsolutePath().toString();
            System.out.println(directoryName + "\\src\\aaaaa.txt");

            File file = new File(directoryName + "\\src\\parte1.txt");
            file.createNewFile();

        } catch (Exception e) {
            System.out.println("To no catch");
        }
    }
}