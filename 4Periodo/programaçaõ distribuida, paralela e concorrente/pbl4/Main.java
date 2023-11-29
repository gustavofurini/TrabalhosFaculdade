import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws Exception {
        Semaphore mutex = new Semaphore(1);
        Semaphore mutex1 = new Semaphore(1);
        Semaphore mutex2 = new Semaphore(1);
        Semaphore mutex3 = new Semaphore(1);
        Semaphore mutex4 = new Semaphore(1);
        Semaphore barreira = new Semaphore(1);

        System.out.println("Hello, World!");
        Thread1 teste = new Thread1(mutex, mutex1, mutex2, mutex3, mutex4, barreira);
        teste.start();
    }
}