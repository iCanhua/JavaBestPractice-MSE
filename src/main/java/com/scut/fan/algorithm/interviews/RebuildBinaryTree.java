package com.scut.fan.algorithm.interviews;

import com.scut.fan.algorithm.TreeNode;

/**
 * Created by FAN on 2016/7/17.
 */
public class RebuildBinaryTree {
    //树结点
    public static void main(String[] args) {
        int[] pre={1,2,4,7,3,5,6,8};
        int[] in={4,7,2,1,5,3,8,6};
        rebuildBinaryTree(pre,in);
    }


    public static TreeNode rebuildBinaryTree(int [] preorder, int [] inorder){
        if (preorder.length!=inorder.length){
            return null;
        }
        TreeNode root=construct(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
        return root;

    }
    public static TreeNode construct(int [] pre,int startPre,int endPre,int [] in,int startIn,int endIn){
        if(startPre>endPre||startIn>endIn)
            return null;
        TreeNode root=new TreeNode(pre[startPre]);

        for(int i=startIn;i<=endIn;i++)
            if(in[i]==pre[startPre]){
                root.left=construct(pre,startPre+1,startPre+i-startIn,in,startIn,i-1);
                root.right=construct(pre,i-startIn+startPre+1,endPre,in,i+1,endIn);
            }

        return root;
    }
}
