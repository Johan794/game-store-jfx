package model.sortAlgotithms;

import java.util.Arrays;
import java.util.List;

public class Algorithms<T extends Comparable<T>> {

    T String;

    public void insertionSort(List<E> array){
        for(int i = 1; i < array.size(); i++){
            for(int j = i; j > 0 && array.get(j-1).compareTo(array.get(j)) > array[j]; j--){
                int temp = array[j];
                array[j] = array[j-1];
                array[j-1] = temp;
            }
        }
    }

    public void bubbleSort(){
        boolean changed = true;
        for(int i = 1; i < array.length-1 && changed; i++){
            changed = false;
            for(int j = 0; j < array.length-i; j++){
                if(array[j] > array[j+1]){
                    changed = true;
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
                System.out.println(j);
                System.out.println(Arrays.toString(array));
            }
        }
    }
}
