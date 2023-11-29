import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Lista implements Serializable {

    private final int MAX_TAMANHO = 1000000;
    private ArrayList<Integer> list;

    public Lista() {
        this.list = new ArrayList<>();

    }

    public void popular() {
        for (int i = 0; i < MAX_TAMANHO; i++) {
            list.add(GerarNumero.gerar(10000000));
        }
    }

    public void ordenar() {
        Collections.sort(list);
    }

    public ArrayList<Integer> getList() {
        return list;
    }

    public void setList(ArrayList<Integer> list) {
        this.list = list;
    }

}