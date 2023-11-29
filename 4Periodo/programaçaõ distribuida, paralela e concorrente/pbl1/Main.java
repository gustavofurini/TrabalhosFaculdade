import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws Exception {
        Semaphore mutexPares = new Semaphore(1);
        Semaphore mutexImpares = new Semaphore(1);

        Contador contGlobal = new Contador();
        int chave[] = new int[100];

        GeradorDeImpares impar1 = new GeradorDeImpares(chave, mutexImpares, contGlobal);
        GeradorDeImpares impar2 = new GeradorDeImpares(chave, mutexImpares, contGlobal);
        GeradorDePares par1 = new GeradorDePares(chave, mutexPares, contGlobal);
        GeradorDePares par2 = new GeradorDePares(chave, mutexPares, contGlobal);


        impar1.start();
        par1.start();
        impar2.start();
        par2.start();
        System.out.println("Threads Iniciadas!");

        try{
            impar1.join();
            impar2.join();
            par1.join();
            par2.join();
            System.out.println("FIM!");
            System.out.println("Quantidade de numeros impares: " + (impar2.getQntd() + impar1.getQntd()));
            System.out.println("Quantidade de numeros pares: " + (par2.getQntd() + par1.getQntd()));
            System.out.println("Quantidade de numeros inserido: " + contGlobal.getContador()); // so pra ter ctz que foi realmente todos os 100
            System.out.print("Array criado: [");
            for(int i = 0; i < chave.length - 1; i++){
                System.out.print(chave[i] + ", ");
            }
            System.out.println(chave[chave.length - 1] + "]");
        }catch(Exception e){
            System.out.println(e);
        }

    }
}
