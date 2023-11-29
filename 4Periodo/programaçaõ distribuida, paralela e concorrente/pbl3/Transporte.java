import java.util.Random;

public class Transporte extends Thread{
    Transportadora transportadora;
    Entrega entrega;

    public Transporte(Transportadora transportadora, Entrega entrega) {
        this.transportadora = transportadora;
        this.entrega = entrega;
    }

    public void entregaConcluida( Transportadora transportadora) {
        Random random = new Random();

        try {
            switch (this.transportadora.nomeTransportadora) {
                case 'A': sleep(random.nextInt(100) + 100); break;
                case 'B': sleep(random.nextInt(200) + 400); break;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        this.entregaConcluida(this.transportadora);
        this.transportadora.valorEntregas.release();

    }
}
