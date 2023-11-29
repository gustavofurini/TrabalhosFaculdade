public class QuickSort extends Thread {
    private int[] array;
    private int lb;
    private int ub;

    private static final int NO_OF_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private static final int NO_OF_THREADS = 2; 
    private static int count;

    public QuickSort(int[] array, int lb, int ub) {
        this.array = array;
        this.lb=lb;
        this.ub=ub;
    }

    static void quickSort(int[] list, int lb, int ub) {
        int pivo, desce, sobe;
        int temp;
        if(lb >= ub) return;
        pivo = list[lb];
        desce = lb;
        sobe = ub;

        while(desce < sobe) {
            while(list[desce] <= pivo  &&  desce < ub) desce++; 
            while(list[sobe] > pivo) sobe--;
            if(desce < sobe) {               
                temp = list[desce];
                list[desce] = list[sobe];
                list[sobe] = temp;
            }
        }
        list[lb] = list[sobe];
        list[sobe] = pivo;

        if (count < NO_OF_THREADS-1) {
            Thread t1 = new Thread(new QuickSort(list, lb, sobe - 1));
            t1.start();
            Thread t2 = new Thread(new QuickSort(list, sobe + 1, ub));
            t2.start();
            count--;
            try {
                t1.join();
                t2.join();
            } catch (Exception e){
                e.printStackTrace();
            }
        } else {
            quickSort(list, lb, sobe-1);
            quickSort(list, sobe+1, ub);
        }
    }

    public void run() {
        count += 1;
        quickSort(array,lb,ub);
    }
}