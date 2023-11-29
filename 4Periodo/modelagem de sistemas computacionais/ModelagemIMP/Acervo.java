import java.util.ArrayList;

public class Acervo {
    ArrayList<Livro> Livros = new ArrayList<Livro>();

    public void incluirLivro(int id, String titulo){
        Livro l = consultarLivro(id);
        if (l == null) {
            Livro livro = new Livro(id, titulo);
            Livros.add(livro);
            System.out.println("Livro adicionado com sucesso.");
        }
        else {
            System.out.println("ID EXISTENTE");
        }
    }

    public void excluirLivro(int id){
        Livro l = consultarLivro(id);
        if (l != null) {
            Livros.remove(l);
            System.out.println("Livro removido com sucesso.");
        }
        else {
            System.out.println("ID INEXISTENTE");
        }
    }

    public Livro consultarLivro(int id){
        for (Livro l : Livros) {
            if (l.id == id) {
                return l;
            }
        }

        return null;
    }

    public void atualizarLivro(int id, String tituloNovo){
        Livro l = consultarLivro(id);
        if (l != null) {
            l.titulo = tituloNovo;
            System.out.println("Livro atualizado com sucesso.");
        }
        else {
            System.out.println("ID INEXISTENTE.");
        }
    }
}
