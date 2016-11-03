package com.scut.fan.algorithm.interviews;

import java.util.*;

/**
 * Created by fan on 2016/11/2.
 */
public class TotalOrdering {
    private char [] seqs;
    private Integer [] book;
    //用于结果去重
    private HashSet<String> result = new HashSet<String>();
    /**
     * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
     * 例如输入字符串abc,则打印出由字符a,b,c
     * 所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。 结果请按字母顺序输出。
     输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。\
     * @param str
     * @return
     */

    public ArrayList<String> Permutation(String str) {
        ArrayList<String> arrange = new ArrayList<String>();
        if(str == null || str.isEmpty()) return arrange;
        char[] strs = str.toCharArray();
        seqs = new char[strs.length];
        book = new Integer[strs.length];
        for (int i = 0; i < book.length; i++) {
            book[i] = 0;
        }
        dfs(strs, 0);
        arrange.addAll(result);
        Collections.sort(arrange);
        return arrange;
    }

    /**
     * 深度遍历法
     */
    private void dfs(char[] arrs, int step){
        //走完所有可能 记录排列
        if(arrs.length == step){
            String str = "";
            for (int i = 0; i < seqs.length; i++) {
                str += seqs[i];
            }
            result.add(str);
            return; //返回上一步
        }
        //遍历整个序列,尝试每一种可能
        for (int i = 0; i < arrs.length; i++) {
            //是否走过
            if(book[i] == 0){
                seqs[step] = arrs[i];
                book[i] = 1;
                //下一步
                dfs(arrs, step + 1);
                //走完最后一步 后退一步
                book[i] = 0;
            }
        }
    }

    public static void main(String[] args) {
        TotalOrdering test=new TotalOrdering();
        ArrayList<String> list= test.Permutation2("abcde");
        for (String str:list) {
            System.out.println(str);
        }
    }

    public ArrayList<String> Permutation2(String str) {
        TreeSet<String> tree = new TreeSet<String>();
        Stack<String[]> stack = new Stack<String[]>();
        ArrayList<String> results = new ArrayList<String>();
        stack.push(new String[]{str,""});
        do{
            String[] popStrs = stack.pop();
            String oldStr = popStrs[1];
            String statckStr = popStrs[0];
            for(int i =statckStr.length()-1;i>=0;i--){
                String[] strs = new String[]{statckStr.substring(0,i)+statckStr.substring(i+1),oldStr+statckStr.substring(i,i+1)};
                if(strs[0].length()==0){
                    tree.add(strs[1]);
                }else{
                    stack.push(strs);
                }
            }
        }while(!stack.isEmpty());
        for(String s : tree)
            results.add(s);
        return results;
    }

}
