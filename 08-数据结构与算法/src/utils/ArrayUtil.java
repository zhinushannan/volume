package utils;

import java.util.Arrays;

public class ArrayUtil {

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
