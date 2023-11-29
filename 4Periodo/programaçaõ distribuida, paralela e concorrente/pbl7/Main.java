public class Main {

    public static void main(String[] args) {
        BufferLimitado BufferBanana = new BufferLimitado(100);
        BufferLimitado BufferMaca = new BufferLimitado(100);
        Gerador G = new Gerador(BufferMaca, BufferBanana);
        ConsumidorBanana consumidorBanana = new ConsumidorBanana(BufferBanana, 10);
        ConsumidorMaca consumidorMaca = new ConsumidorMaca(BufferMaca, 10);
        ProdutorBanana produtorBanana = new ProdutorBanana(BufferBanana, 10);
        ProdutorMaca produtorMaca = new ProdutorMaca(BufferMaca, 10);
        produtorMaca.run();
        produtorBanana.run();
        consumidorBanana.run();
        consumidorMaca.run();
        G.run();
    }
}
