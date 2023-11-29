import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Main {

    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        Acervo acervo = new Acervo();
        TratadorAcervo tratadorAcervo = new TratadorAcervo(acervo);

        while(true){
            System.out.println("[1]INCLUIR LIVRO");
            System.out.println("[2]CONSULTAR LIVRO");
            System.out.println("[3]EXCLUIR LIVRO");
            System.out.println("[4]ATUALIZAR LIVRO");
            System.out.println("[5]SAIR");
            System.out.println("ESCOLHA UMA OPÇÃO:");
            int opcao = scanner.nextInt();
            if(opcao == 1){
                System.out.println("Informe o id do livro!");
                int idLivro;
                idLivro = scanner.nextInt();
                System.out.println("Informe o título do livro!");
                String tituloLivro;
                tituloLivro = scanner.next();
                tratadorAcervo.incluirLivro(idLivro, tituloLivro);
            }
            else if(opcao == 2){
                System.out.println("Informe o id do livro a ser consultado!");
                int idLivro;
                idLivro = scanner.nextInt();
                tratadorAcervo.consultarLivro(idLivro);
            }
            else  if(opcao == 3){
                System.out.println("Informe o id do livro a ser excluído!");
                int idLivro;
                idLivro = scanner.nextInt();
                tratadorAcervo.excluirLivro(idLivro);
            }
            else if(opcao == 4){
                System.out.println("Informe o id do livro a ser atualizado!");
                int id;
                id = scanner.nextInt();
                System.out.println("Informe o novo título do livro!");
                String novoTitulo = scanner.next();
                tratadorAcervo.atualizarLivro(id, novoTitulo);
            }
            else if(opcao == 5){
                System.exit(0);

            }
        }

    }
}
