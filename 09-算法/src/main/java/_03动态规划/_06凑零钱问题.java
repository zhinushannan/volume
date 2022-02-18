package _03动态规划;

import java.util.Arrays;

/**
 * 给你k种面值的硬币，面值分别为c1, c2 ... ck，每种硬币的数量无限，
 * 再给⼀个总金额amount，请问最少需要几枚硬币凑出这个⾦额，如果不可能凑出，算法返回 -1 。
 */
public class _06凑零钱问题 {

    public static void main(String[] args) {

        int[]coins = {1, 2, 5, 7, 10};
        int amount = 14;
        int i = coinChange(coins, amount);
        System.out.println(i);

    }

    /**
     * 【https://www.bilibili.com/video/BV1cT4y1w7Ct】
     */
    public static int coinChange(int[] coins, int amount) {
        // 初始化数组dp，大小为amount + 1，全部元素初始化为-1，dp的下标代表对应金额，值是对应的最优解
        int[]dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        // 金额0的最优解dp[0] = 0
        dp[0] = 0;

        // 变量 i 从 1 循环至 amount，依次计算金额 1 至 amount 的最优解
        for (int i = 1; i <= amount; i++) {
            // 对于每个金额 i，使用变量 j 遍历面值 coins 数组
            for (int coin : coins) {
                // 所有小于等于 i 的面值 coins[j]，如果金额 i - coins[j] 有最优解
                if (coin <= i && dp[i - coin] != -1) {
                    // 如果当前金额还未计算或者 dp[i] 比正在计算的最优解大，则更新 dp[i]
                    if (dp[i] == -1 || dp[i] > dp[i - coin] + 1) {
                        dp[i] = dp[i - coin] + 1;
                    }
                }
            }
        }
        return dp[amount];
    }

}
