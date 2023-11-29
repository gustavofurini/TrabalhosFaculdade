import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random rand = new Random();

        int maxMacas =  rand.nextInt(100, 10000);
        int maxBananas =  rand.nextInt(100, 10000);

        Ceasa ceasaMacas = new Ceasa(maxMacas);
        Ceasa ceasaBananas = new Ceasa(maxBananas);

        Produtor pm1 = new Produtor(ceasaMacas, maxMacas, "macas");
        Produtor pb1 = new Produtor(ceasaBananas, maxBananas, "bananas");
        Produtor pm2 = new Produtor(ceasaMacas, maxMacas, "macas");
        Produtor pb2 = new Produtor(ceasaBananas, maxBananas, "bananas");
        Produtor pm3 = new Produtor(ceasaMacas, maxMacas, "macas");
        Produtor pb3 = new Produtor(ceasaBananas, maxBananas, "bananas");

        Consumidor cm1 = new Consumidor(ceasaMacas, maxMacas, "macas");
        Consumidor cb1 = new Consumidor(ceasaBananas, maxBananas, "bananas");
        Consumidor cm2 = new Consumidor(ceasaMacas, maxMacas, "macas");
        Consumidor cb2 = new Consumidor(ceasaBananas, maxBananas, "bananas");
        Consumidor cm3 = new Consumidor(ceasaMacas, maxMacas, "macas");
        Consumidor cb3 = new Consumidor(ceasaBananas, maxBananas, "bananas");

        pm1.start();
        pb1.start();
        pm2.start();
        pb2.start();
        pm3.start();
        pb3.start();

        cm1.start();
        cb1.start();
        cm2.start();
        cb2.start();
        cm3.start();
        cb3.start();


    }
}
