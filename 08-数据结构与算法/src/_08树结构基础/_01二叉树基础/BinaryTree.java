package _08树结构基础._01二叉树基础;

public class BinaryTree<E> {

    private Node<E> root;

    BinaryTree() {}

    public void setRoot(Node<E> root) {
        this.root = root;
    }

    public Node<E> getRoot() {
        return this.root;
    }

}
