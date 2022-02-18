package _03动态规划;

import java.util.Arrays;

/**
 * 在给定的一串包含正数，负数的数组中，找出最大的子数组的和
 */
public class _03最大子数组和 {

    public static void main(String[] args) {
        int[]arr = {-4, -4, -9, 7, -5, 8, -2, 11, -6};
        int i = maxSub(arr, 0, Integer.MIN_VALUE);
        System.out.println(i);
    }

    /**
     * 整体思想：
     * 【https://www.bilibili.com/video/BV1Dq4y1k7ea】
     */
    public static int maxSub(int[] arr, int start, int max) {
        // 递归结束条件
        if (start == arr.length) {
            return max;
        }
        // 当前循环的最大子数组和存放数组
        int[] dp = new int[arr.length - start];
        // 首元素
        dp[0] = arr[start];
        // 填充dp
        for (int i = start + 1, j = 1; i < arr.length; i++, j++) {
            dp[j] = Math.max(dp[j - 1] + arr[i], arr[i]);
        }
        // 寻找最大值并进行下一步递归
        int asInt = Math.max(Arrays.stream(dp).max().getAsInt(), max);
        return maxSub(arr, start + 1, asInt);
    }

}
