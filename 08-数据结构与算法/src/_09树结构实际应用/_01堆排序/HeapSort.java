package _09树结构实际应用._01堆排序;

import utils.ArrayUtil;

public class HeapSort {

    public static void main(String[] args) {
        int[] arr = {10, 1, 2, 3, 4, 5, 6, 7, 8};
        sort(arr);
        ArrayUtil.printArray1(arr);
    }

    public static void sort(int[] arr) {
        buildHeap(arr);
        for (int i = arr.length - 1; i >= 0; i--) {
            swap(arr, 0, i);
            heapify(arr, 0, i);
        }
    }

    /**
     * 建堆，应当从最后一个结点的父节点开始大堆化
     * @param arr
     */
    public static void buildHeap(int[] arr) {
        int last = arr.length - 1;
        int parent = (last - 1) / 2;
        for (int i = parent; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
    }

    /**
     *
     * <p>将一颗二叉树大堆化</p>
     *
     * @param arr 二叉树数组
     * @param i 需要进行大堆化的节点
     * @param length 树的末尾节点坐标
     */
    public static void heapify(int[] arr, int i, int length) {
        /*
        获取左子节点、右子节点的坐标
        获取当前结点的坐标，并记为最大坐标
         */
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int max = i;

        // 获取三个结点当中最大的节点的坐标值
        if (left < length && arr[left] > arr[max]) {
            max = left;
        }
        if (right < length && arr[right] > arr[max]) {
            max = right;
        }

        /*
         判断当前结点 i 是不是最大结点，如果不是则进行交换
         但是其子树不一定符合大堆顶，因此要对其子树进行大堆化

         若当前结点 i 是最大结点，则说明以i为根节点、left、right
         为子结点的二叉树是大堆顶树，递归结束
         */
        if (max != i) {
            swap(arr, max, i);
            heapify(arr, max, length);
        }

    }

    public static void swap(int[] arr, int index1, int index2) {
        int temp1 = arr[index1], temp2 = arr[index2];
        arr[index1] = temp2;
        arr[index2] = temp1;
    }

}
