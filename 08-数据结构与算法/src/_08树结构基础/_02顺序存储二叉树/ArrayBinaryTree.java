package _08树结构基础._02顺序存储二叉树;

public class ArrayBinaryTree {

    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder(int index) {
        System.out.print(arr[index] + "\t");

        if ((index * 2 + 1) < arr.length) {
            preOrder(2 * index + 1);
        }

        if ((index * 2 + 2) < arr.length) {
            preOrder(2 * index + 2);
        }

    }

    public void infixOrder(int index) {
        if ((index * 2 + 1) < arr.length) {
            infixOrder(2 * index + 1);
        }

        System.out.print(arr[index] + "\t");

        if ((index * 2 + 2) < arr.length) {
            infixOrder(2 * index + 2);
        }

    }

    public void postOrder(int index) {
        if ((index * 2 + 1) < arr.length) {
            preOrder(2 * index + 1);
        }

        if ((index * 2 + 2) < arr.length) {
            preOrder(2 * index + 2);
        }

        System.out.print(arr[index] + "\t");
    }

}
