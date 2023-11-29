import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Arquivo {

    public static void salvar(String nome, Lista objeto) throws IOException {
        final Path arquivo = Paths.get(nome);
        try (final OutputStream outputStream = Files.newOutputStream(arquivo)) {
            for (Integer integer : objeto.getList()) {
                outputStream.write(String.format("%d\n", integer).getBytes());
            }
        }
    }

    public static Lista abrirArquivo(String nome) throws IOException {
        final Path arquivo = Paths.get(nome);
        Lista lista = new Lista();

        for (String numero : Files.readAllLines(arquivo)) {
            if (!numero.trim().isEmpty()) {
                lista.getList().add(Integer.parseInt(numero));
            }
        }

        return lista;
    }
}