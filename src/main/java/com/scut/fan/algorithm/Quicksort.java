package com.scut.fan.algorithm;

/**
 * Created by FAN on 2016/7/3.
 */
public class Quicksort {
    public int[] Sort(int[] array){
        quicksort(array,0,array.length-1);
        return array;
    }

    public static void quicksort(int[] array,int start,int end){
        if (start>=end||array==null||array.length==0){
            return;
        }
        int index=partition(array,start,end);
        quicksort(array,start,index-1);
        quicksort(array,index+1,end);
    }
    private static int partition(int[] array,int start,int end){
        int index=start-1;
        for (int i = start ; i <end ; i++) {
            if (array[i]<=array[end]){
                if (index!=i){
                    int temp=array[i];
                    array[i]=array[++index];
                    array[index]=temp;
                }
            }
        }
        int temp=array[end];
        array[end]=array[++index];
        array[index]=temp;
        return index;

    }

    public static void main(String[] args) {

        
        int[] array=new int[]{6,6,125,1,51,56,489,56,612,56,1256,1,5612,6,1256,16,2,612,63,26,16,12,9,126,12,62,3,2,92,3,126,2,6,5,6312,89};
        //quicksort(array,0,array.length-1);
        for (int i = 0; i < array.length; i++) {
            System.out.print("|"+array[i]);
        }
        System.out.println("");
        array=new Quicksort().Sort(array);
        for (int i = 0; i <array.length ; i++) {
            System.out.print("|"+array[i]);
        }

    }
}
