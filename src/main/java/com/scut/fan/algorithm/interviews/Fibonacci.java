package com.scut.fan.algorithm.interviews;

/**
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项。
 * Created by FAN on 2016/7/6.
 */
public class Fibonacci {
    private static long fibonacci_v1(int i){
        if(i<=0){
            return 0;
        }
        if (i==1){
            return 1;
        }
        return fibonacci_v1(i-1)+fibonacci_v1(i-2);
    }
    private static long fibonacci_v2(int i){
        long[] record=new long[i+1];
        record[0]=0;
        record[1]=1;
        if (i>=2){
            for (int j = 2; j <=i; j++) {
                record[j]=record[j-1]+record[j-2];
            }
        }
        return record[i];
    }
    //比较高效的解法
    public static long fibonacci_v3(int i){
        int[] result={0,1};
        if (i<2){
            return result[i];
        }
        long FibNMinusOne=1,FibMinusTwo=0,FibN=1;
        for (int j = 2; j <= i; j++) {
            FibN=FibNMinusOne+FibMinusTwo;
            FibMinusTwo=FibNMinusOne;
            FibNMinusOne=FibN;
        }
        return FibN;
    }


    public static void main(String[] args) {
        int i=30;
        System.out.println(fibonacci_v1(i)+":"+fibonacci_v2(i)+":"+fibonacci_v3(i));
    }
}
