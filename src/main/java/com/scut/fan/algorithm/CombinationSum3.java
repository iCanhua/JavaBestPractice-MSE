package com.scut.fan.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 说明：
 *
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 输入：3 15
 * 输出：[[1, 5, 9], [1, 6, 8], [2, 4, 9], [2, 5, 8], [2, 6, 7], [3, 4, 8], [3, 5, 7], [4, 5, 6]]
 */
public class CombinationSum3 {

  List<Integer> branch = new ArrayList<>();

  List<List<Integer>> result= new ArrayList<>();

  public static void main(String[] args) {
    CombinationSum3 combinationSum3 = new CombinationSum3();
    List<List<Integer>> lists = combinationSum3.combinationSum3(3, 15);
    System.out.println(lists);
  }



  public List<List<Integer>> combinationSum3(int k, int n) {
    dps(1,k,n);
    return result;
  }

  public void dps(int cur ,int k,int n){
    //等于10证明9已经入树，所以10是合法的
    if(cur>9+1){
      return;
    }
    if(branch.size()>k){
      return;
    }
    if(check(k,n)){
      return;
    }
    //如果选中该元素符合可能就继续
    if(branch.size()+9-cur+1>=k){
      branch.add(cur);
      dps(cur+1,k,n);
      branch.remove(branch.size()-1);
    }
    //如果不选中该元素还有可能也继续
    if(branch.size()+9-cur>=k){
      dps(cur+1,k,n);
    }
  }

  public boolean  check(int k, int n){
    if(branch.size()==k){
      int sum = 0;
      for(int index :branch){
        sum+=index;
      }
      if(sum==n){
        result.add(new ArrayList<>(branch));
        return true;
      }
    }
    return false;
  }
}
