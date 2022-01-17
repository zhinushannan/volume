package _08树结构基础._02顺序存储二叉树;

import org.junit.Test;
import utils.SplitLineUtil;

public class ArrayBinaryTreeDemo {

    @Test
    public void test() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};

        ArrayBinaryTree binaryTree = new ArrayBinaryTree(arr);

        SplitLineUtil.printLineWithoutFeed("前序遍历");
        binaryTree.preOrder(0);
        System.out.println();
        SplitLineUtil.printLineWithoutFeed("中序遍历");
        binaryTree.infixOrder(0);
        System.out.println();
        SplitLineUtil.printLineWithoutFeed("后序遍历");
        binaryTree.postOrder(0);

    }

}
