package _08树结构基础._03线索化二叉树;

public class Node<E> {

    private E element;

    private Node<E> left;

    private Node<E> right;

    /**
     * <p>如果 leftType</p>
     * <p>等于 0，即 TYPE_FRONT 表示指向左子树</p>
     * <p>等于 1 即 TYPE_BACK 表示指向前驱结点</p>
     */
    private int leftType;

    /**
     * <p>如果 rightType</p>
     * <p>等于 0，即 TYPE_FRONT 表示指向右子树</p>
     * <p>等于 1 即 TYPE_BACK 表示指向后继结点</p>
     */
    private int rightType;

    public static final Integer TYPE_SON = 0;

    public static final Integer TYPE_THREADED = 1;

    public Node(E element) {
        this.element = element;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
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

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "[element=" + element + "]";
    }
}
