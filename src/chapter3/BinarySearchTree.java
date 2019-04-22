package chapter3;

import edu.princeton.cs.algs4.Queue;

public class BinarySearchTree<Key extends Comparable, Value> {
    private TreeNode<Key, Value> root;

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        TreeNode<Key, Value> node = root;
        while (node != null) {
            if (less(node.key, key)) node = node.right;
            else if (less(key, node.key)) node = node.left;
            else return node.value;
        }
        return null;
    }

    public void put(Key key, Value value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        root = put(root, key, value);
    }

    private TreeNode<Key, Value> put(TreeNode<Key, Value> node, Key key, Value value) {
        if (node == null) return new TreeNode<Key, Value>(key, value);
        int cmp = key.compareTo(node.key);
        if (cmp > 0)
            node.right = put(node.right, key, value);
        else if (cmp < 0)
            node.left = put(node.left, key, value);
        else
            node.value = value;
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    private int size(TreeNode<Key, Value> node) {
        if (node == null)
            return 0;
        else
            return node.count;
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    public void deleteMin() {
        if (root == null) return;
        deleteMin(root);
    }

    private TreeNode deleteMin(TreeNode node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    public void deleteMax() {
        if (root == null) return;
        deleteMax(root);
    }

    private TreeNode deleteMax(TreeNode node) {
        if (node.right == null) return node.left;
        node.right = deleteMin(node.right);
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    public void delete(Key key) {
        delete(root, key);
    }

    private TreeNode delete(TreeNode node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        // search the key
        if (cmp < 0)
            node.left = delete(node.left, key);
        else if (cmp > 0)
            node.right = delete(node.right, key);
        else {
            // 如果node只有一个child或者没有，那么返回其child或者null
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            // 如果node有两个child，那么寻找右侧最小值，将其与node替换
            // 同时在右侧分支内删除最小值结点
            TreeNode x = node;
            node = min(x.right);
            node.left = x.left;
            node.right = deleteMin(x.right);
        }
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    private TreeNode min(TreeNode node) {
        if (node == null) return null;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public Key min() {
        if (root == null) return null;
        TreeNode<Key, Value> node = root.left;
        while (node.left != null) {
            node = node.left;
        }
        return node.key;
    }

    public Key max() {
        if (root == null) return null;
        TreeNode<Key, Value> node = root.right;
        while (node.right != null) {
            node = node.right;
        }
        return node.key;
    }

    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<>();
        inorder(root, q);
        return q;
    }

    private void inorder(TreeNode<Key, Value> node, Queue q) {
        if (node == null) return;
        inorder(node.left, q);
        q.enqueue(node.key);
        inorder(node.right, q);
    }

    private void preorder(TreeNode node, Queue q) {
        if (node == null) return;
        q.enqueue(node.key);
        preorder(node.left, q);
        preorder(node.right, q);
    }

    private void postorder(TreeNode node, Queue q) {
        if (node == null) return;
        postorder(node.left, q);
        postorder(node.right, q);
        q.enqueue(node);
    }

    private int rank(TreeNode<Key, Value> node, Key key) {
        if (node == null) return 0;
        int cmp = node.key.compareTo(key);
        if (cmp > 0)
            return rank(node.left, key);
        else if (cmp < 0)
            return 1 + rank(node.left, key) + rank(node.right, key);
        else
            return size(node.left);
    }

    public Key floor(Key key) {
        TreeNode<Key, Value> node = floor(key, root);
        if (node == null) return null;
        return node.key;
    }

    private TreeNode<Key, Value> floor(Key key, TreeNode<Key, Value> node) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node;
        if (cmp < 0) return floor(key, node.left);
        TreeNode<Key, Value> node_ = floor(key, node.right);
        if (key == null) {
            return node;
        } else {
            return node_;
        }
    }

    public Key ceil(Key key) {
        TreeNode<Key, Value> node = ceil(key, root);
        if (node == null) return null;
        return node.key;
    }

    private TreeNode<Key, Value> ceil(Key key, TreeNode<Key, Value> node) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node;
        if (cmp > 0) return floor(key, node.right);
        TreeNode<Key, Value> node_ = floor(key, node.left);
        if (key == null) {
            return node;
        } else {
            return node_;
        }
    }


    private boolean less(Key key1, Key key2) {
        return key1.compareTo(key2) < 0;
    }

}


