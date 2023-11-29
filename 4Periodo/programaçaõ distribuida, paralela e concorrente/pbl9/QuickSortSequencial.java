import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuickSortSequencial {
    static void quickSort(int list[], int lb, int ub) {
        int pivo, desce, sobe;
        int temp;
        if(lb>=ub)
            return;
        pivo=list[lb];
        desce=lb;
        sobe=ub;
        while(desce<sobe) {
            while(list[desce]<=pivo && desce<ub) desce++;
            while(list[sobe]>pivo) sobe--;
            if(desce<sobe) {
                temp=list[desce];
                list[desce]=list[sobe];
                list[sobe]=temp;
            }
        }
        list[lb]=list[sobe];
        list[sobe]=pivo;

        quickSort(list, lb, sobe-1);
        quickSort(list, sobe+1, ub);
    }
}