package utils;

import java.util.Arrays;

public class ArrayUtil {

    /**
     * 打印一维数组
     * @param arr 一维数组
     */
    public static void printArray1(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 打印一维数组
     * @param arr 一维数组
     */
    public static void printArray1(int[] arr, int start, int end) {
        if (end > arr.length) {
            throw new IndexOutOfBoundsException();
        }
        int[] temp = new int[end - start + 1];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = arr[start++];
        }

        System.out.println(Arrays.toString(temp));
    }

    /**
     * 打印二维数组
     * @param arr 二维数组
     */
    public static void printArray2(int[][] arr) {
        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
    }

}
