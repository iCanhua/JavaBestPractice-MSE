package com.scut.fan.algorithm;

/**
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 *
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 *
 *  
 *
 * 示例：
 *
 * 输入：nums: [1, 1, 1, 1, 1], S: 3
 * 输出：5
 * 解释：
 *
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 *
 * 一共有5种方法让最终目标和为3。
 *  
 *
 * 提示：
 *
 * 数组非空，且长度不会超过 20 。
 * 初始的数组的和不会超过 1000 。
 * 保证返回的最终结果能被 32 位整数存下。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/target-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TargetSum {

  public static void main(String[] args) {
    System.out.println(findTargetSumWays(new int[]{1,999},998));
  }

  public static int findTargetSumWays(int[] nums, int S) {
    int numsLength =nums.length;
    int[][] dp= new int[numsLength][2001];
    dp[0][findIndex(nums[0])]=1;
    dp[0][findIndex(-nums[0])]=dp[0][findIndex(-nums[0])]+1;
    for(int i =1;i<numsLength;i++){
      for(int j=-1000;j<=1000;j++){
        if(findIndex(j-nums[i])>=0){
          dp[i][findIndex(j)] +=dp[i-1][findIndex(j-nums[i])];
        }
        if(findIndex(j+nums[i])<=2000){
          dp[i][findIndex(j)] +=dp[i-1][findIndex(j+nums[i])];
        }
      }
    }
    if(S>1000||S<-1000){
      return 0;
    }
    return dp[numsLength-1][findIndex(S)];
  }

  public static int findIndex(int sum){
    return sum+1000;
  }
}
