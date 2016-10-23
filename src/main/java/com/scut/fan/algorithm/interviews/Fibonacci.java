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

    /**
     * 有一段楼梯台阶有15级台阶，以小明的脚力一步最多只能跨3级，请问小明登上这段楼梯有多少种不同的走法?
     */
    static class FibonacciPractice{
        //递归实现
        public static int  solution_v1(int n){
            if (n==1){
                return 1;
            }else if (n==2){
                return 2;
            }else if (n==3){
                return 4;
            }
            return solution_v1(n-1)+solution_v1(n-2)+solution_v1(n-3);
        }
        //循环实现
        public static int solution_v2(int n){
            int[] Fib={1,2,4};
            if (n<4){
                return Fib[n];
            }
            int FibN=7,FibNMinusOne=4,FibNMinusTwo=2,FibNMinesThree=1;
            for (int i = 4; i <n+1; i++) {
                FibN=FibNMinusOne+FibNMinusTwo+FibNMinesThree;
                FibNMinesThree=FibNMinusTwo;
                FibNMinusTwo=FibNMinusOne;
                FibNMinusOne=FibN;
            }
            return FibN;
        }

    }


    public static void main(String[] args) {
        int i=30;
        System.out.println(fibonacci_v1(i)+":"+fibonacci_v2(i)+":"+fibonacci_v3(i));
        int result=FibonacciPractice.solution_v2(15);
        System.out.println(result);
    }
}
