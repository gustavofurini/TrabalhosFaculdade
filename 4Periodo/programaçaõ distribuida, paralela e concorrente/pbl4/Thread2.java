import java.io.File;
import java.io.FileWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.concurrent.Semaphore;

public class Thread2 extends Thread{

    Semaphore mutex, mutex1, mutex2, mutex3, mutex4, barreira;

    public Thread2(Semaphore mutex, Semaphore mutex1, Semaphore mutex2, Semaphore mutex3, Semaphore mutex4, Semaphore barreira) {
        this.mutex = mutex;
        this.mutex1 = mutex1;
        this.mutex2 = mutex2;
        this.mutex3 = mutex3;
        this.mutex4 = mutex4;
        this.barreira = barreira;
    }

    @Override
    public void run() {
        CalculaValorImpostoDeRenda();
    }


    public void CalculaValorImpostoDeRenda() {

        try {
            var funcionario = Funcionarios.lFuncionarios;


            mutex1.acquire();
            var ultimaPosicaoP1 = Thread1.lParte1.get(Thread1.lParte1.size() - 1);
            for (int i = Thread1.lParte1.get(0); i < ultimaPosicaoP1; i++) {
                funcionario.get(i).desconto_imp_ren = funcionario.get(i).salario_bruto * 0.20;
                funcionario.get(i).total_descontos += funcionario.get(i).desconto_imp_ren;
                funcionario.get(i).salario_liquido = funcionario.get(i).salario_bruto - funcionario.get(i).total_descontos;
            }
            System.out.println("Imposto de renda calculado da parte 1");
            mutex1.release();


            mutex2.acquire();
            var ultimaPosicaoP2 = Thread1.lParte2.get(Thread1.lParte2.size() - 1);
            for (int i = Thread1.lParte2.get(0); i < ultimaPosicaoP2; i++) {
                funcionario.get(i).desconto_imp_ren = funcionario.get(i).salario_bruto * 0.20;
                funcionario.get(i).total_descontos += funcionario.get(i).desconto_imp_ren;
                funcionario.get(i).salario_liquido = funcionario.get(i).salario_bruto - funcionario.get(i).total_descontos;
            }
            System.out.println("Imposto de renda calculado da parte 2");
            mutex2.release();


            mutex3.acquire();
            var ultimaPosicaoP3 = Thread1.lParte3.get(Thread1.lParte3.size() - 1);
            for (int i = Thread1.lParte3.get(0); i < ultimaPosicaoP3; i++) {
                funcionario.get(i).desconto_imp_ren = funcionario.get(i).salario_bruto * 0.20;
                funcionario.get(i).total_descontos += funcionario.get(i).desconto_imp_ren;
                funcionario.get(i).salario_liquido = funcionario.get(i).salario_bruto - funcionario.get(i).total_descontos;
            }
            System.out.println("Imposto de renda calculado da parte 3");
            mutex3.release();

            // Parte 4
            mutex4.acquire();
            var ultimaPosicaoP4 = Thread1.lParte4.get(Thread1.lParte4.size() - 1);
            for (int i = Thread1.lParte4.get(0); i < ultimaPosicaoP4; i++) {
                funcionario.get(i).desconto_imp_ren = funcionario.get(i).salario_bruto * 0.20;
                funcionario.get(i).total_descontos += funcionario.get(i).desconto_imp_ren;
                funcionario.get(i).salario_liquido = funcionario.get(i).salario_bruto - funcionario.get(i).total_descontos;
            }
            System.out.println("Imposto de renda calculado da parte 4");
            mutex4.release();

            mutex.acquire();
            Thread1.contadorThreads++;
            if (Thread1.contadorThreads == Thread1.nThreads) {
                barreira.release();
            }
            mutex.release();

            System.out.println("[THREAD 1] chegou na barreira");
            barreira.acquire();
            barreira.release();
            System.out.println("[THREAD 1] passou da barreira");


            mutex1.acquire();

            ImprimeContraCheque();
            mutex1.release();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void ImprimeContraCheque() {
        try {
            Path path = FileSystems.getDefault().getPath("");
            String directoryName = path.toAbsolutePath().toString();
            System.out.println(directoryName + "\\srcparte1.txt");


            File file = new File(directoryName + "\\src\\parte1.txt");
            file.createNewFile();


            FileWriter myWriter = new FileWriter(directoryName + "\\src\\parte1.txt");
            var ultimaPosicaoP1 = Thread1.lParte1.get(Thread1.lParte1.size() - 1);
            for (int i = Thread1.lParte1.get(0); i < ultimaPosicaoP1; i++) {
                myWriter.write(Funcionarios.lFuncionarios.get(i).relatorioDeDados());
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");

        } catch (Exception e) {
            System.out.println("Erro ae escrever o arquivo");
        }
    }

}