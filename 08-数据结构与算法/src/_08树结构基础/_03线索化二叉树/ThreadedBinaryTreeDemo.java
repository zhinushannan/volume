package _08树结构基础._03线索化二叉树;

import org.junit.Test;
import utils.SplitLineUtil;

public class ThreadedBinaryTreeDemo {

    @Test
    public void testThreadedBinaryTree() {
        Node<String> root = new Node<>("tom");
        Node<String> node2 = new Node<>("jack");
        Node<String> node3 = new Node<>("smith");
        Node<String> node4 = new Node<>("mary");
        Node<String> node5 = new Node<>("king");
        Node<String> node6 = new Node<>("dim");

        root
                .setLeft(node2)
                .setRight(node3);

        node2
                .setLeft(node4)
                .setRight(node5);

        node3.setLeft(node6);

        ThreadedBinaryTree<String> threadedBinaryTree = new ThreadedBinaryTree<>(root);
        threadedBinaryTree.threadedNodes(root);

        System.out.println("king的前驱结点是" + node5.getLeft());
        System.out.println("king的后驱结点是" + node5.getRight());

        SplitLineUtil.printLineWithoutFeed("使用线索化方式遍历线索化二叉树");
        threadedBinaryTree.threadedList();

    }

}
