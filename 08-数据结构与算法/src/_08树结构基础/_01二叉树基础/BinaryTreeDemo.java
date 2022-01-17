package _08树结构基础._01二叉树基础;

import org.junit.Before;
import org.junit.Test;
import utils.SplitLineUtil;

public class BinaryTreeDemo {

    private BinaryTree<String> binaryTree;

    /**
     * 构造二叉树
     *
     *      宋江
     *   吴用    卢俊义
     *        关胜   林冲
     *
     *
     */
    @Before
    public void init() {
        Node<String> root = new Node<>("宋江", null, null);
        Node<String> node2 = new Node<>("吴用", null, null);
        Node<String> node3 = new Node<>("卢俊义", null, null);
        Node<String> node4 = new Node<>("林冲", null, null);
        Node<String> node5 = new Node<>("关胜", null, null);

        root
                .setLeft(node2)
                .setRight(node3);

        node3
                .setLeft(node5)
                .setRight(node4);

        binaryTree = new BinaryTree<>();
        binaryTree.setRoot(root);
    }

    @Test
    public void testOrder() {
        SplitLineUtil.printLineWithoutFeed("前序遍历");
        binaryTree.getRoot().preOrder();
        SplitLineUtil.printLineWithoutFeed("中序遍历");
        binaryTree.getRoot().infixOrder();
        SplitLineUtil.printLineWithoutFeed("后序遍历");
        binaryTree.getRoot().postOrder();
    }

    @Test
    public void testSearch() {
        SplitLineUtil.printLineWithoutFeed("前序查找");
        System.out.println(binaryTree.getRoot().preSearch("关胜"));
        SplitLineUtil.printLineWithoutFeed("中序查找");
        System.out.println(binaryTree.getRoot().infixSearch("关胜"));
        SplitLineUtil.printLineWithoutFeed("后序查找");
        System.out.println(binaryTree.getRoot().postSearch("关胜"));
    }

    @Test
    public void testRemove() {
        SplitLineUtil.printLineWithoutFeed("删除卢俊义");
        binaryTree.getRoot().remove("卢俊义");
        binaryTree.getRoot().preOrder();
    }

    @Test
    public void testRemove2() {
        SplitLineUtil.printLineWithoutFeed("删除卢俊义");
        binaryTree.getRoot().remove2("关胜");
        binaryTree.getRoot().preOrder();
    }

}
