public class TratadorAcervo {
    Acervo acervo;

    public TratadorAcervo(Acervo a) {
        this.acervo = a;
    }

    public void incluirLivro(int id, String titulo){
        acervo.incluirLivro(id, titulo);
    }

    public void excluirLivro(int id){
        acervo.excluirLivro(id);
    }

    public void consultarLivro(int id){
        Livro l = acervo.consultarLivro(id);
        if (l != null)
            System.out.println("Livro: " + l.titulo + "->" +  l.id + "Estado: " + l.estado + "\n");
        else
            System.out.println("O livro não existe.");
    }

    public void atualizarLivro(int id, String tituloNovo){
        acervo.atualizarLivro(id, tituloNovo);
    }
}