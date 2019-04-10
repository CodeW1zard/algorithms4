package chapter3;

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
        if (root == null) {
            this.root = new TreeNode(key, value);
            return;
        }
        put(root, key, value);
    }

    private void put(TreeNode<Key, Value> node, Key key, Value value) {
        if (less(node.key, key)) {
            if (node.right != null) {
                put(node.right, key, value);
            } else {
                node.right = new TreeNode(key, value);
            }
        } else if (less(key, node.key)) {
            if (node.left != null) {
                put(node.left, key, value);
            } else {
                node.left = new TreeNode(key, value);
            }
        } else {
            node.value = value;
        }
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


