package _03动态规划;

import java.util.HashMap;
import java.util.Map;

/**
 * 现在有一个n个数组成的数列，牛牛现在想取一个连续的子序列，
 * 并且这个子序列还必须得满足：最多只改变一个数，就可以使得这个连续的子序列是一个严格上升的子序列，这个连续子序列最长的长度是多少。
 */
public class _02最长递增子数组 {

    private static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        int[] nums = {1, 5, 2, 4, 3};
        System.out.println(getMaxLength(nums, 0));
        System.out.println(dp(nums, 0));

    }

    public static int getMaxLength(int[]nums, int i) {
        // 如果是从最后一位数开始查找，那么直接返回 1
        if (i == nums.length - 1) {
            return 1;
        }

        // 让 maxLength 的初始值为 1，因为即便从这个数开始都是降序，那么这个数也可以作为单独的一个升序的数列
        int maxLength = 1;
        // 因为 i 已经被默认纳入了升序序列（maxLength的初始值为1），所以需要从 i + 1 的位置开始遍历，一直遍历到最后一位
        for (int j = i + 1; j < nums.length; j++) {
            // 如果当前的数大于 i 位置的数
            if (nums[j] > nums[i]) {
                // 让 maxLength 与 从 j 处开始的最大长度进行比较（+1是因为要把i处给记上），把大的那个赋值给 maxLength
                maxLength = Math.max(maxLength, getMaxLength(nums, j) + 1);
            }
        }
        // 返回最大值
        return maxLength;
    }

    // 经过融入动态规划思想改造后的算法（核心代码和 getMaxLength完全相同 ）
    public static int dp(int[]nums, int i) {
        // 如果该坐标被遍历过，则直接去哈希表中查找
        if (map.containsKey(i)) {
            return map.get(i);
        }

        if (i == nums.length - 1) {
            return 1;
        }
        int maxLength = 1;
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[j] > nums[i]) {
                maxLength = Math.max(maxLength, dp(nums, j) + 1);
            }
        }

        map.put(i, maxLength);

        return maxLength;
    }

}
