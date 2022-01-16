package _08树结构基础._01二叉树遍历;

public class Node <E> {

    private E element;

    private Node<E> left;

    private Node<E> right;

    Node(E element, Node<E> left, Node<E> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }


    public Node<E> getLeft() {
        return left;
    }

    public Node<E> setLeft(Node<E> left) {
        this.left = left;
        return this;
    }

    public Node<E> getRight() {
        return right;
    }

    public Node<E> setRight(Node<E> right) {
        this.right = right;
        return this;
    }

    @Override
    public String toString() {
        return element.toString();
    }

    /**
     * <p>前序遍历</p>
     * <p>1、输出当前结点</p>
     * <p>2、递归向左子树前序遍历</p>
     * <p>3、递归向右子树前序遍历</p>
     */
     public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * <p>中序遍历</p>
     * <p>1、递归向左子树前序遍历</p>
     * <p>2、输出当前结点</p>
     * <p>3、递归向右子树前序遍历</p>
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * <p>后序遍历</p>
     * <p>1、递归向左子树前序遍历</p>
     * <p>2、递归向右子树前序遍历</p>
     * <p>3、先输出当前结点</p>
     */
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

}
