package _08树结构基础._03线索化二叉树;

public class ThreadedBinaryTree<E> {

    private Node<E> root;

    /**
     * 为了实现线索化，需要创建一个指针，指向当前结点的前驱结点
     */
    private Node<E> pre = null;

    public ThreadedBinaryTree(Node<E> root) {
        this.root = root;
    }

    /**
     * 中序线索化的方法
     * @param node 当前需要线索化的节点
     */
    public void threadedNodes(Node<E> node) {
        // 如果 node == null，不能线索化
        if (null == node) {
            return;
        }
        // 1、线索化左子树
        threadedNodes(node.getLeft());
        // 2、线索化当前结点

        // 处理当前结点的前驱结点
        if (null == node.getLeft()) {
            // 让当前结点的左指针指向钱去结点
            node.setLeft(pre);
            // 修改当前结点的左指针的类型
            node.setLeftType(Node.TYPE_THREADED);
        }

        // 处理后继结点
        if (null != pre && null == pre.getRight()) {
            pre.setRight(node);
            pre.setRightType(Node.TYPE_THREADED);
        }

        pre = node;

        // 3、线索化右子树
        threadedNodes(node.getRight());
    }

    public void threadedList() {
        Node<E> node = this.root;
        while (null != node) {
            while (node.getLeftType() == Node.TYPE_SON) {
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightType() == Node.TYPE_THREADED) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }
}
