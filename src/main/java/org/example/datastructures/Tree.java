package org.example.datastructures;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    private class Node {
        private final int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Node{");
            sb.append("value=").append(value);
            sb.append('}');
            return sb.toString();
        }
    }

    private Node root;

    public void insert(int value) {
        var node = new Node(value);
        if (root == null) {
            root = node;
            return;
        }
        var current = root;
        while (true) {
            if (value < current.value) {
                if (current.left == null) {
                    current.left = node;
                    break;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = node;
                    break;
                }
                current = current.right;
            }
        }
    }

    public boolean find(int value) {
        var current = root;
        while (current != null) {
            if (value < current.value) {
                current = current.left;
            } else if (value > current.value) {
                current = current.right;
            } else {
                return true;
            }
        }
        return false;
    }

    public void preOrder() {
        preOrderTraversal(root);
    }

    private void preOrderTraversal(Node root) {
        if (root == null) return;

        System.out.print(root.value + " ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    public void inOrder() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node root) {
        if (root == null) return;

        inOrderTraversal(root.left);
        System.out.print(root.value + " ");
        inOrderTraversal(root.right);
    }

    public void postOrder() {
        postOrderTraversal(root);
    }

    private void postOrderTraversal(Node root) {
        if (root == null) return;

        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.value + " ");
    }

    public int height() {
        return height(root);
    }

    private int height(Node root) {
        if (root == null) {
            return -1;
        }
        if (isLeaf(root)) {
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }

    private static boolean isLeaf(Node root) {
        return root.left == null && root.right == null;
    }

    public int min() {
        return min(root);
    }

    private int min(Node root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }

        if (isLeaf(root)) {
            return root.value;
        }

        var left = min(root.left);
        var right = min(root.right);

        return Math.min(Math.min(left, right), root.value);
    }

    public boolean equals(Tree other) {
        if (other == null) {
            return false;
        }
        return equals(this.root, other.root);
    }

    private boolean equals(Node first, Node second) {
        if (first == null && second == null) {
            return true;
        }
        if (first != null && second != null) {
            return first.value == second.value
                   && equals(first.left, second.left)
                   && equals(first.right, second.right);
        }
        return false;
    }

    public boolean isBinarySearchTree() {
        if (root == null) {
            return true;
        }
        return isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBinarySearchTree(Node root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.value < min || root.value > max) {
            return false;
        }
        return isBinarySearchTree(root.left, min, root.value - 1)
               && isBinarySearchTree(root.right, root.value + 1, max);
    }

    public List<Integer> getNodesAtDistance(int distance) {
        var list = new ArrayList<Integer>();
        getNodesAtDistance(root, distance, list);
        return list;
    }

    private void getNodesAtDistance(Node root, int distance, List<Integer> list) {
        if (root == null || distance < 0) {
            return;
        }
        if (distance == 0) {
            list.add(root.value);
            return;
        }
        getNodesAtDistance(root.left, distance - 1, list);
        getNodesAtDistance(root.right, distance - 1, list);
    }
}
