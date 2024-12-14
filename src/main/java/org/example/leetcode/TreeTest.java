package org.example.leetcode;

import org.example.leetcode.trees.TreeNode;

import java.util.Arrays;

public class TreeTest {
    public static void main(String[] args) {

        int[] secondArray = {12, 15, 17, 21, 18, 14, 11, 21, 35, 14, 18, 18};

//        BinaryTree tree = new BinaryTree();
//
//        tree.insert(50);
//        tree.insert(30);
//        tree.insert(20);
//        tree.insert(40);
//        tree.insert(70);
//        tree.insert(60);
//        tree.insert(80);
//
//        // Traversing the binary tree in different ways
//        System.out.println("Inorder traversal:");
//        tree.inorder();
//
//        System.out.println("\nPreorder traversal:");
//        tree.preorder();
//
//        System.out.println("\nPostorder traversal:");
//        tree.postorder();

        Arrays.sort(secondArray);
        System.out.println(sortedArrayToBST(secondArray));
    }
    public static TreeNode sortedArrayToBST(int[] nums) {
        return transform(nums, 0, nums.length - 1);
    }
    public static TreeNode transform(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.setLeft(transform(nums, start, mid - 1));
        root.setRight(transform(nums, mid + 1, end));

        return root;

    }
}
