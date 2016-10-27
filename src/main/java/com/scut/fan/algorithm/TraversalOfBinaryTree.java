package com.scut.fan.algorithm;

import com.scut.fan.algorithm.interviews.RebuildBinaryTree;

import java.util.Stack;

/**
 * Created by fan on 2016/10/23.
 */
public class TraversalOfBinaryTree {
    public static void main(String[] args) {

        int[] pre={1,2,4,7,3,5,6,8};
        int[] in={4,7,2,1,5,3,8,6};
        TreeNode root=RebuildBinaryTree.rebuildBinaryTree(pre,in);
        preOrderTraverse(root);
        preOrderTraverse_Stack(root);
       // inOrderTraverse(root);
       // postOrderTraverse(root);

    }


    /**
     * 先序遍历
     * 这三种不同的遍历结构都是一样的，只是先后顺序不一样而已
     * @param node
     * 遍历的节点
     */
    public static void preOrderTraverse(TreeNode node) {
        if (null==node){
            return;
        }
        System.out.println(node.val);
        preOrderTraverse(node.left);
        preOrderTraverse(node.right);

    }
    /**
     * 非递归先序遍历
     */
    public static void preOrderTraverse_Stack(TreeNode node){
        if (null==node){
            return;
        }
        Stack<TreeNode> stack=new Stack<TreeNode>();
        stack.push(node);
        while (!stack.isEmpty()&&null!=stack.peek()){
            TreeNode node1=stack.pop();
            System.out.println(node1.val);
            if(null!=node1.right){
                stack.push(node1.right);
            }
            if (null!=node1.left){
                stack.push(node1.left);
            }
        }


    }

    /**
     * 中序遍历
     * 这三种不同的遍历结构都是一样的，只是先后顺序不一样而已
     * @param node
     * 遍历的节点
     */
    public static void inOrderTraverse(TreeNode node) {
        if (null==node){
            return;
        }
        inOrderTraverse(node.left);
        System.out.println(node.val);
        inOrderTraverse(node.right);
    }

    /**
     * 后序遍历
     * 这三种不同的遍历结构都是一样的，只是先后顺序不一样而已
     * @param node
     * 遍历的节点
     */
    public static void postOrderTraverse(TreeNode node) {
        if (null==node){
            return;
        }

        postOrderTraverse(node.left);
        postOrderTraverse(node.right);
        System.out.println(node.val);
    }

}
