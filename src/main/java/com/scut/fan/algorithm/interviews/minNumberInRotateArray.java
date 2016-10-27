package com.scut.fan.algorithm.interviews;

/**
 * Created by FAN on 2016/10/26.
 */
public class minNumberInRotateArray {

    public static void main(String[] args) {
        int [] array=new int[]{5,6,7,89,1,2,3,4};
        int i=minNumberInRotateArray(array);
        System.out.println(i);
    }


     /*
 * 传进去旋转数组，注意旋转数组的特性：
 * 1.包含两个有序序列
 * 2.最小数一定位于第二个序列的开头
 * 3.前序列的值都>=后序列的值
 * 定义把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * ^_^这个旋转思想是很经典的
 * 旋转数组实例：
 * {123456}旋转后{456123}
 */

    //用到了快速排序的快速定位范围的思想，
    public static int minNumberInRotateArray(int [] array) {

        if(array==null||array.length==0){  return 0;
        }
        int low=0;
        int up=array.length-1;
        int mid=low;

        // 当low和up两个指针相邻时候，就找到了最小值，也就是
        //右边序列的第一个值

        while(array[low]>=array[up]){
            if(up-low==1){
                mid=up;
                break;
            }
            //如果low、up、mid下标所指的值恰巧相等
            //如：{0,1,1,1,1}的旋转数组{1,1,1,0,1}
            if(array[low]==array[up]&&array[mid]==array[low])
                return MinInOrder(array);
            mid=(low+up)/2;
            //这种情况，array[mid]仍然在左边序列中
            if(array[mid]>=array[low])
                low=mid;//注意，不能写成low=mid+1；
                //要是这种情况，array[mid]仍然在右边序列中
            else if(array[mid]<=array[up])
                up=mid;
        }

        return array[mid];

    }
    private static int MinInOrder(int[] array) {
        // TODO Auto-generated method stub
        int min =array[0];
        for(int i=1;i<array.length;i++){
            if(array[i]<min){
                min=array[i];

            }
        }
        return min;
    }


}
