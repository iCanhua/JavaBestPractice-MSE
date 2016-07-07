package com.scut.fan.algorithm;

/**
 * 01背包问题，具体算法请参考有关书籍
 * Created by FAN on 2016/7/6.
 */
public class KnapsackProblem {

    public static void main(String[] args) {
        int val[] = {10, 40, 30, 50};
        int wt[] = {5, 4, 6, 3};
        int W = 13;

        System.out.println(solution(val, wt, W));
    }
    /**
     * @param val 各个物品的价值
     * @param wt 各个物品对应的重量，与val对应
     * @param W 背包重量
     * @return 该背包最大价值
     */
    public static int solution(int[] val,int[] wt,int W){
        //要求val[]和wt[]长度一致，非空，W非负数等异常检查
        if (val.length!=wt.length||val==null||wt==null||W<0){
            return -1;
        }
        //构造解问题数组，MaxVal,数组row从1~length分别表示第一个物品到第length个物品
        int MaxVal[][]=new int[val.length+1][W+1];
        //当什么都不拿和W为0的时候，初始化对应数组的最大价值为0
        for (int i = 0; i < val.length+1; i++) {
            MaxVal[i][0]=0;
        }
        for (int i = 0; i < W+1; i++) {
            MaxVal[0][i]=0;
        }
        //填充数组
        for (int item = 1; item < val.length+1; item++) {
            for (int weight = 1; weight < W + 1; weight++) {
                if (weight<wt[item-1]){     //在wt数组中，item-1的下标才是第item个
                    MaxVal[item][weight]=MaxVal[item-1][weight];
                }else {
                    MaxVal[item][weight]=Math.max(val[item-1]+MaxVal[item-1][weight-wt[item-1]],MaxVal[item-1][weight]);
                }
            }
        }
        for (int[] rows : MaxVal) {
            for (int col : rows) {
                System.out.format("%5d", col);
            }
            System.out.println();
        }
        return MaxVal[val.length][W];

    }
}
