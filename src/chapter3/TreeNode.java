package chapter3;

public class TreeNode<Key extends Comparable, Value> {
    TreeNode left = null;
    TreeNode right = null;
    int height;
    int count;
    Key key;
    Value value;

    public TreeNode(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    public TreeNode(Key key, Value value, int height) {
        this.key = key;
        this.value = value;
        this.height = height;
    }

    public TreeNode(Key key, Value value, int height, int count) {
        this.key = key;
        this.value = value;
        this.height = height;
        this.count = count;
    }
}
