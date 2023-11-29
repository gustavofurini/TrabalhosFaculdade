public class PrintarThread extends Thread{

    public String getNome() {
        return String.format("[%s]", this.getName());
    }
    void threadPrint(String texto) {
        System.out.println(getNome() + " -> " + texto);

    }

}