public class TreeNode<E> {

    private E data;
    private TreeNode<E> left;
    private TreeNode<E> right;

    public TreeNode(E data, TreeNode<E> left, TreeNode<E> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public TreeNode(E data) {
        this(data, null, null);
    }

    public TreeNode() {
        this(null, null, null);
    }

    public void printTree() {
        printTree(this, 0);
    }

    private void printTree(TreeNode<E> node, int level) {
        if (node == null) {
            return;
        }
        printTree(node.right, level + 1);
        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
        System.out.println(node.data);
        printTree(node.left, level + 1);
    }

    public int size() {
        return size(this);
    }

    private int size(TreeNode<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.left) + size(node.right);
    }

    public E getData() {
        return data;
    }

    public TreeNode<E> getLeft() {
        return left;
    }

    public TreeNode<E> getRight() {
        return right;
    }

    public void setData(E data) {
        this.data = data;
    }

    public void setLeft(TreeNode<E> left) {
        this.left = left;
    }

    public void setRight(TreeNode<E> right) {
        this.right = right;
    }
}
