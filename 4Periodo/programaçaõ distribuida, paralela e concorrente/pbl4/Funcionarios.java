import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
public class Funcionarios {

    public static ArrayList<Funcionarios> lFuncionarios = new ArrayList<Funcionarios>();

    Random random = new Random();
    int codigo;
    double salario_bruto = (new Random().nextDouble()) + (new Random().nextInt(4000)+1000);
    double desconto_imp_ren;
    double desconto_inss;
    double desconto_prev_privada;
    double desconto_plano_sus;
    double total_descontos;

    double salario_liquido;

    public Funcionarios(int codigo) {
        this.codigo = codigo;
    }

    public String relatorioDeDados() {
        DecimalFormat df = new DecimalFormat("#,###.00");
        codigo++;
        return ("Funcionario: " + codigo + " possui salario bruto de R$" + df.format(salario_bruto) + ". Teve o desconto total de R$" + df.format(total_descontos) + " passando a ficar com o salario liquido de R$" + df.format(salario_liquido) + ".\n");
    }

}