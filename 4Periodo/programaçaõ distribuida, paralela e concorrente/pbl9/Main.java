import java.io.FileWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    static int [] criarArray(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) list.add(i);

        Collections.shuffle(list);

        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) array[i] = list.get(i);

        return array;
    }

    //static int highestPowerof2(int n) {
    //    int res = 0;
    //    for (int i = n; i >= 1; i--)
    //    {
    //       
    //        if ((i & (i - 1)) == 0)
    //        {
    //            res = i;
    //            break;
    //        }
    //    }
    //    return res;
    //}

    public static void main(String[] args) throws IOException {
        int[] array1 = criarArray((int)Math.pow(2,15));
        int[] array2 = criarArray((int)Math.pow(2,16));
        int[] array3 = criarArray((int)Math.pow(2,17));
        int[] array4 = criarArray((int)Math.pow(2,18));
        int[] array5 = criarArray((int)Math.pow(2,19));

        int[] array6 = criarArray((int)Math.pow(2,20));
        int[] array7 = criarArray((int)Math.pow(2,21));
        int[] array8 = criarArray((int)Math.pow(2,22));

        int[] array9 = criarArray((int)Math.pow(2,23));
        int[] array10 = criarArray((int)Math.pow(2,24));
        int[] array11 = criarArray((int)Math.pow(2,25));
        int[] array12 = criarArray((int)Math.pow(2,26));

        long mediaParalelo1 = 0;
        long mediaSequencial1 = 0;
        for (int z = 0; z < 50; z++) {
            int[] arr = Arrays.copyOf(array1, array1.length);
            int[] arr2 = Arrays.copyOf(array1, array1.length);
            long start_a = System.currentTimeMillis();
            QuickSort.quickSort(arr, 0, arr.length - 1);
            long end_a = System.currentTimeMillis();
            long aParalelo = (end_a - start_a);
            mediaParalelo1 += aParalelo;

            long start_aS = System.currentTimeMillis();
            QuickSortSequencial.quickSort(arr2, 0, arr.length - 1);
            long end_aS = System.currentTimeMillis();
            long aSequencial = (end_aS - start_aS);
            mediaSequencial1 += aSequencial;
        }
        System.out.println("=======================================");
        System.out.println("Tempo (2^15) paralelo: " + mediaParalelo1/50);
        System.out.println("Tempo (2^15) sequencial: " + mediaSequencial1/50);
        System.out.println("=======================================");
        long mediaParalelo2 = 0;
        long mediaSequencial2 = 0;
        for (int z = 0; z < 50; z++) {
            int[] arr = Arrays.copyOf(array2, array2.length);
            int[] arr2 = Arrays.copyOf(array2, array2.length);
            long start_b = System.currentTimeMillis();
            QuickSort.quickSort(arr, 0, arr.length - 1);
            long end_b = System.currentTimeMillis();
            long bParalelo = (end_b - start_b);
            mediaParalelo2 += bParalelo;

            long start_bS = System.currentTimeMillis();
            QuickSortSequencial.quickSort(arr2, 0, arr.length - 1);
            long end_bS = System.currentTimeMillis();
            long bSequencial = (end_bS - start_bS);
            mediaSequencial2 += bSequencial;
        }
        System.out.println("=======================================");
        System.out.println("Tempo (2^16) paralelo: " + mediaParalelo2/50);
        System.out.println("Tempo (2^16) sequencial: " + mediaSequencial2/50);
        System.out.println("=======================================");

        long mediaParalelo3 = 0;
        long mediaSequencial3 = 0;
        for (int z = 0; z < 50; z++) {
            int[] arr = Arrays.copyOf(array3, array3.length);
            int[] arr2 = Arrays.copyOf(array3, array3.length);
            long start_c = System.currentTimeMillis();
            QuickSort.quickSort(arr, 0, arr.length - 1);
            long end_c = System.currentTimeMillis();
            long cParalelo = (end_c - start_c);
            mediaParalelo3 += cParalelo;

            long start_cS = System.currentTimeMillis();
            QuickSortSequencial.quickSort(arr2, 0, arr.length - 1);
            long end_cS = System.currentTimeMillis();
            long cSequencial = (end_cS - start_cS);
            mediaSequencial3 += cSequencial;
        }
        System.out.println("=======================================");
        System.out.println("Tempo (2^17) paralelo: " + mediaParalelo3/50);
        System.out.println("Tempo (2^17) sequencial: " + mediaSequencial3/50);
        System.out.println("=======================================");

        long mediaParalelo4 = 0;
        long mediaSequencial4 = 0;
        for (int z = 0; z < 50; z++) {
            int[] arr = Arrays.copyOf(array4, array4.length);
            int[] arr2 = Arrays.copyOf(array4, array4.length);
            long start_d = System.currentTimeMillis();
            QuickSort.quickSort(arr, 0, arr.length - 1);
            long end_d = System.currentTimeMillis();
            long dParalelo = (end_d - start_d);
            mediaParalelo4 += dParalelo;

            long start_dS = System.currentTimeMillis();
            QuickSortSequencial.quickSort(arr2, 0, arr.length - 1);
            long end_dS = System.currentTimeMillis();
            long dSequencial = (end_dS - start_dS);
            mediaSequencial4 += dSequencial;
        }
        System.out.println("=======================================");
        System.out.println("Tempo (2^18) paralelo: " + mediaParalelo4/50);
        System.out.println("Tempo (2^18) sequencial: " + mediaSequencial4/50);
        System.out.println("=======================================");

        long mediaParalelo5 = 0;
        long mediaSequencial5 = 0;
        for (int z = 0; z < 50; z++) {
            int[] arr = Arrays.copyOf(array5, array5.length);
            int[] arr2 = Arrays.copyOf(array5, array5.length);
            long start_e = System.currentTimeMillis();
            QuickSort.quickSort(arr, 0, arr.length - 1);
            long end_e = System.currentTimeMillis();
            long eParalelo = (end_e - start_e);
            mediaParalelo5 += eParalelo;

            long start_eS = System.currentTimeMillis();
            QuickSortSequencial.quickSort(arr2, 0, arr.length - 1);
            long end_eS = System.currentTimeMillis();
            long eSequencial = (end_eS - start_eS);
            mediaSequencial5 += eSequencial;
        }
        System.out.println("=======================================");
        System.out.println("Tempo (2^19) paralelo: " + mediaParalelo5/50);
        System.out.println("Tempo (2^19) sequencial: " + mediaSequencial5/50);
        System.out.println("=======================================");
        long mediaParalelo6 = 0;
        long mediaSequencial6 = 0;
        for (int z = 0; z < 10; z++) {
            int[] arr = Arrays.copyOf(array6, array6.length);
            int[] arr2 = Arrays.copyOf(array6, array6.length);
            long start_a = System.currentTimeMillis();
            QuickSort.quickSort(arr, 0, arr.length - 1);
            long end_a = System.currentTimeMillis();
            long fParalelo = (end_a - start_a);
            mediaParalelo6 += fParalelo;

            long start_aS = System.currentTimeMillis();
            QuickSortSequencial.quickSort(arr2, 0, arr.length - 1);
            long end_aS = System.currentTimeMillis();
            long fSequencial = (end_aS - start_aS);
            mediaSequencial6 += fSequencial;
        }
        System.out.println("=======================================");
        System.out.println("Tempo (2^20) paralelo: " + mediaParalelo6/10);
        System.out.println("Tempo (2^20) sequencial: " + mediaSequencial6/10);
        System.out.println("=======================================");


        long mediaParalelo7 = 0;
        long mediaSequencial7 = 0;
        for (int z = 0; z < 10; z++) {
            int[] arr = Arrays.copyOf(array7, array7.length);
            int[] arr2 = Arrays.copyOf(array7, array7.length);
            long start_a = System.currentTimeMillis();
            QuickSort.quickSort(arr, 0, arr.length - 1);
            long end_a = System.currentTimeMillis();
            long aParalelo = (end_a - start_a);
            mediaParalelo7 += aParalelo;

            long start_aS = System.currentTimeMillis();
            QuickSortSequencial.quickSort(arr2, 0, arr.length - 1);
            long end_aS = System.currentTimeMillis();
            long aSequencial = (end_aS - start_aS);
            mediaSequencial7 += aSequencial;
        }
        System.out.println("=======================================");
        System.out.println("Tempo (2^21) paralelo: " + mediaParalelo7/10);
        System.out.println("Tempo (2^21) sequencial: " + mediaSequencial7/10);
        System.out.println("=======================================");

        long mediaParalelo8 = 0;
        long mediaSequencial8 = 0;
        for (int z = 0; z < 50; z++) {
            int[] arr = Arrays.copyOf(array8, array8.length);
            int[] arr2 = Arrays.copyOf(array8, array8.length);
            long start_h = System.currentTimeMillis();
            QuickSort.quickSort(arr, 0, arr.length - 1);
            long end_h = System.currentTimeMillis();
            long hParalelo = (end_h - start_h);
            mediaParalelo8 += hParalelo;

            long start_hS = System.currentTimeMillis();
            QuickSortSequencial.quickSort(arr2, 0, arr.length - 1);
            long end_hS = System.currentTimeMillis();
            long hSequencial = (end_hS - start_hS);
            mediaSequencial8 += hSequencial;
        }
        System.out.println("=======================================");
        System.out.println("Tempo (2^22) paralelo: " + mediaParalelo8/50);
        System.out.println("Tempo (2^22) sequencial: " + mediaSequencial8/50);
        System.out.println("=======================================");
        long mediaParalelo9 = 0;
        long mediaSequencial9 = 0;
        for (int z = 0; z < 10; z++) {
            int[] arr = Arrays.copyOf(array9, array9.length);
            int[] arr2 = Arrays.copyOf(array9, array9.length);
            long start_a = System.currentTimeMillis();
            QuickSort.quickSort(arr, 0, arr.length - 1);
            long end_a = System.currentTimeMillis();
            long aParalelo = (end_a - start_a);
            mediaParalelo9 += aParalelo;

            long start_aS = System.currentTimeMillis();
            QuickSortSequencial.quickSort(arr2, 0, arr.length - 1);
            long end_aS = System.currentTimeMillis();
            long aSequencial = (end_aS - start_aS);
            mediaSequencial9 += aSequencial;
        }
        System.out.println("=======================================");
        System.out.println("Tempo (2^23) paralelo: " + mediaParalelo9/10);
        System.out.println("Tempo (2^23) sequencial: " + mediaSequencial9/10);
        System.out.println("=======================================");
        long mediaParalelo10 = 0;
        long mediaSequencial10 = 0;
        for (int z = 0; z < 10; z++) {
            int[] arr = Arrays.copyOf(array10, array10.length);
            int[] arr2 = Arrays.copyOf(array10, array10.length);
            long start_a = System.currentTimeMillis();
            QuickSort.quickSort(arr, 0, arr.length - 1);
            long end_a = System.currentTimeMillis();
            long aParalelo = (end_a - start_a);
            mediaParalelo10 += aParalelo;

            long start_aS = System.currentTimeMillis();
            QuickSortSequencial.quickSort(arr2, 0, arr.length - 1);
            long end_aS = System.currentTimeMillis();
            long aSequencial = (end_aS - start_aS);
            mediaSequencial10 += aSequencial;
        }
        System.out.println("=======================================");
        System.out.println("Tempo (2^24) paralelo: " + mediaParalelo10/10);
        System.out.println("Tempo (2^24) sequencial: " + mediaSequencial10/10);
        System.out.println("=======================================");

        long mediaParalelo11 = 0;
        long mediaSequencial11 = 0;
        for (int z = 0; z < 10; z++) {
            int[] arr = Arrays.copyOf(array11, array11.length);
            int[] arr2 = Arrays.copyOf(array11, array11.length);
            long start_a = System.currentTimeMillis();
            QuickSort.quickSort(arr, 0, arr.length - 1);
            long end_a = System.currentTimeMillis();
            long aParalelo = (end_a - start_a);
            mediaParalelo11 += aParalelo;

            long start_aS = System.currentTimeMillis();
            QuickSortSequencial.quickSort(arr2, 0, arr.length - 1);
            long end_aS = System.currentTimeMillis();
            long aSequencial = (end_aS - start_aS);
            mediaSequencial11 += aSequencial;
        }
        System.out.println("=======================================");
        System.out.println("Tempo (2^25) paralelo: " + mediaParalelo11/10);
        System.out.println("Tempo (2^25) sequencial: " + mediaSequencial11/10);
        System.out.println("=======================================");

        long mediaParalelo12 = 0;
        long mediaSequencial12 = 0;
        for (int z = 0; z < 10; z++) {
            int[] arr = Arrays.copyOf(array12, array12.length);
            int[] arr2 = Arrays.copyOf(array12, array12.length);
            long start_a = System.currentTimeMillis();
            QuickSort.quickSort(arr, 0, arr.length - 1);
            long end_a = System.currentTimeMillis();
            long lParalelo = (end_a - start_a);
            mediaParalelo12 += lParalelo;

            long start_aS = System.currentTimeMillis();
            QuickSortSequencial.quickSort(arr2, 0, arr.length - 1);
            long end_aS = System.currentTimeMillis();
            long aSequencial = (end_aS - start_aS);
            mediaSequencial1 += aSequencial;
        }
        System.out.println("=======================================");
        System.out.println("Tempo (2^26) paralelo: " + mediaParalelo12/10);
        System.out.println("Tempo (2^26) sequencial: " + mediaSequencial12/10);
        System.out.println("=======================================");
        FileWriter escreverResultado = new FileWriter("valores.csv");

        escreverResultado.append("Tamanho");
        escreverResultado.append(",");
        escreverResultado.append("Tempo paralelo");
        escreverResultado.append(",");
        escreverResultado.append("Tempo sequencial");
        escreverResultado.append('\n');

        escreverResultado.append("2^15");
        escreverResultado.append(",");
        escreverResultado.append(String.valueOf(mediaParalelo1/50));
        escreverResultado.append(",");
        escreverResultado.append(String.valueOf(mediaSequencial1/50));
        escreverResultado.append('\n');

        escreverResultado.append("2^16");
        escreverResultado.append(",");
        escreverResultado.append(String.valueOf(mediaParalelo2/50));
        escreverResultado.append(",");
        escreverResultado.append(String.valueOf(mediaSequencial2/5));
        escreverResultado.append('\n');

        escreverResultado.append("2^17");
        escreverResultado.append(",");
        escreverResultado.append(String.valueOf(mediaParalelo3/50));
        escreverResultado.append(",");
        escreverResultado.append(String.valueOf(mediaSequencial3/50));
        escreverResultado.append('\n');

        escreverResultado.append("2^18");
        escreverResultado.append(",");
        escreverResultado.append(String.valueOf(mediaParalelo4/50));
        escreverResultado.append(",");
        escreverResultado.append(String.valueOf(mediaSequencial4/50));
        escreverResultado.append('\n');

        escreverResultado.append("2^19");
        escreverResultado.append(",");
        escreverResultado.append(String.valueOf(mediaParalelo5/50));
        escreverResultado.append(",");
        escreverResultado.append(String.valueOf(mediaSequencial5/50));
        escreverResultado.append('\n');

        escreverResultado.append("2^20");
        escreverResultado.append(",");
        escreverResultado.append(String.valueOf(mediaParalelo6));
        escreverResultado.append(",");
        escreverResultado.append(String.valueOf(mediaSequencial6));
        escreverResultado.append('\n');

        escreverResultado.append("2^21");
        escreverResultado.append(",");
        escreverResultado.append(String.valueOf(mediaParalelo7));
        escreverResultado.append(",");
        escreverResultado.append(String.valueOf(mediaSequencial7));
        escreverResultado.append('\n');

        escreverResultado.append("2^22");
        escreverResultado.append(",");
        escreverResultado.append(String.valueOf(mediaParalelo8/50));
        escreverResultado.append(",");
        escreverResultado.append(String.valueOf(mediaSequencial8/50));
        escreverResultado.append('\n');

        escreverResultado.append("2^23");
        escreverResultado.append(",");
        escreverResultado.append(String.valueOf(mediaParalelo9));
        escreverResultado.append(",");
        escreverResultado.append(String.valueOf(mediaSequencial9));
        escreverResultado.append('\n');

        escreverResultado.append("2^24");
        escreverResultado.append(",");
        escreverResultado.append(String.valueOf(mediaParalelo10));
        escreverResultado.append(",");
        escreverResultado.append(String.valueOf(mediaSequencial10));
        escreverResultado.append('\n');

        escreverResultado.append("2^25");
        escreverResultado.append(",");
        escreverResultado.append(String.valueOf(mediaParalelo11));
        escreverResultado.append(",");
        escreverResultado.append(String.valueOf(mediaSequencial11));
        escreverResultado.append('\n');

        escreverResultado.append("2^26");
        escreverResultado.append(",");
        escreverResultado.append(String.valueOf(mediaParalelo12));
        escreverResultado.append(",");
        escreverResultado.append(String.valueOf(mediaSequencial12));
        escreverResultado.append('\n');

        escreverResultado.flush();
        escreverResultado.close();

        System.out.println("PRINTADO NO ARQUIVO CSV");
    }
}