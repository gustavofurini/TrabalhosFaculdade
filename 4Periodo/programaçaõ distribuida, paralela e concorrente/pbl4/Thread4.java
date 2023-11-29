import java.io.File;
import java.io.FileWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.concurrent.Semaphore;

public class Thread4 extends Thread {

    Semaphore mutex, mutex1, mutex2, mutex3, mutex4, barreira;

    public Thread4(Semaphore mutex, Semaphore mutex1, Semaphore mutex2, Semaphore mutex3, Semaphore mutex4, Semaphore barreira) {
        this.mutex = mutex;
        this.mutex1 = mutex1;
        this.mutex2 = mutex2;
        this.mutex3 = mutex3;
        this.mutex4 = mutex4;
        this.barreira = barreira;
    }

    @Override
    public void run() {
        CalculaPrevidenciaPrivada();
    }

    public void CalculaPrevidenciaPrivada() {
        try {
            var funcionario = Funcionarios.lFuncionarios;

            // Parte 3
            mutex3.acquire();
            var ultimaPosicaoP3 = Thread1.lParte3.get(Thread1.lParte3.size() - 1);
            for (int i = Thread1.lParte3.get(0); i < ultimaPosicaoP3; i++) {
                funcionario.get(i).desconto_prev_privada = funcionario.get(i).salario_bruto * 0.04;
                funcionario.get(i).total_descontos += funcionario.get(i).desconto_prev_privada;
                funcionario.get(i).salario_liquido = funcionario.get(i).salario_bruto - funcionario.get(i).total_descontos;
            }
            System.out.println("Previdencia parte 3");
            mutex3.release();


            mutex4.acquire();
            var ultimaPosicaoP4 = Thread1.lParte4.get(Thread1.lParte4.size() - 1);
            for (int i = Thread1.lParte4.get(0); i < ultimaPosicaoP4; i++) {
                funcionario.get(i).desconto_prev_privada = funcionario.get(i).salario_bruto * 0.04;
                funcionario.get(i).total_descontos += funcionario.get(i).desconto_prev_privada;
                funcionario.get(i).salario_liquido = funcionario.get(i).salario_bruto - funcionario.get(i).total_descontos;
            }
            System.out.println("Previdencia parte 4");
            mutex4.release();

            // Parte 1
            mutex1.acquire();
            var ultimaPosicaoP1 = Thread1.lParte1.get(Thread1.lParte1.size() - 1);
            for (int i = Thread1.lParte1.get(0); i < ultimaPosicaoP1; i++) {
                funcionario.get(i).desconto_prev_privada = funcionario.get(i).salario_bruto * 0.04;
                funcionario.get(i).total_descontos += funcionario.get(i).desconto_prev_privada;
                funcionario.get(i).salario_liquido = funcionario.get(i).salario_bruto - funcionario.get(i).total_descontos;
            }
            System.out.println("Previdencia parte 1");
            mutex1.release();

            // Parte 2
            mutex2.acquire();
            var ultimaPosicaoP2 = Thread1.lParte2.get(Thread1.lParte2.size() - 1);
            for (int i = Thread1.lParte2.get(0); i < ultimaPosicaoP2; i++) {
                funcionario.get(i).desconto_prev_privada = funcionario.get(i).salario_bruto * 0.04;
                funcionario.get(i).total_descontos += funcionario.get(i).desconto_prev_privada;
                funcionario.get(i).salario_liquido = funcionario.get(i).salario_bruto - funcionario.get(i).total_descontos;
            }
            System.out.println("Previdencia parte 2");
            mutex2.release();

            mutex.acquire();
            Thread1.contadorThreads++;
            if (Thread1.contadorThreads == Thread1.nThreads) {
                barreira.release();
            }
            mutex.release();

            System.out.println("[THREAD 3] chegou na barreira");
            barreira.acquire();
            barreira.release();
            System.out.println("[THREAD 3] passou da barreira");


            mutex3.acquire();

            ImprimeContraCheque();
            mutex3.release();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void ImprimeContraCheque() {
        try {
            Path path = FileSystems.getDefault().getPath("");
            String directoryName = path.toAbsolutePath().toString();
            System.out.println(directoryName + "\\src\\parte3.txt");

            File file = new File(directoryName + "\\src\\parte3.txt");

            file.createNewFile();


            FileWriter myWriter = new FileWriter(directoryName + "\\src\\parte3.txt");
            var ultimaPosicaoP3 = Thread1.lParte3.get(Thread1.lParte3.size() - 1);
            for (int i = Thread1.lParte3.get(0); i < ultimaPosicaoP3; i++) {
                myWriter.write(Funcionarios.lFuncionarios.get(i).relatorioDeDados());
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");

        } catch (Exception e) {
            System.out.println("Erro ae escrever o arquivo");;
        }
    }

}