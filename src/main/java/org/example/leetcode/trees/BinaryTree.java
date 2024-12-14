package org.example.leetcode.trees;

public class BinaryTree {
    private TreeNode root;

    public BinaryTree() {
        this.root = null;
    }

    public void insert(int data) {
        this.root = insertRec(root, data);
    }

    // Private recursive function for inserting a node
    private TreeNode insertRec(TreeNode root, int data) {
        // If the tree is empty, create a new node
        if (root == null) {
            root = new TreeNode(data);
            return root;
        }

        // Otherwise, recur down the tree
        if (data < root.getData()) {
            root.setLeft(insertRec(root.getLeft(), data));
        } else if (data > root.getData()) {
            root.setRight(insertRec(root.getRight(), data));
        }

        return root;
    }

    // Public method for inorder traversal
    public void inorder() {
        inorderRec(root);
    }

    // Private recursive function for inorder traversal
    private void inorderRec(TreeNode root) {
        if (root != null) {
            inorderRec(root.getLeft());
            System.out.print(root.getData() + " ");
            inorderRec(root.getRight());
        }
    }

    // Public method for preorder traversal
    public void preorder() {
        preorderRec(root);
    }

    // Private recursive function for preorder traversal
    private void preorderRec(TreeNode root) {
        if (root != null) {
            System.out.print(root.getData() + " ");
            preorderRec(root.getLeft());
            preorderRec(root.getRight());
        }
    }

    // Public method for postorder traversal
    public void postorder() {
        postorderRec(root);
    }

    // Private recursive function for postorder traversal
    private void postorderRec(TreeNode root) {
        if (root != null) {
            postorderRec(root.getLeft());
            postorderRec(root.getRight());
            System.out.print(root.getData() + " ");
        }
    }
}
