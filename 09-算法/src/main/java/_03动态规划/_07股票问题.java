package _03动态规划;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 */
public class _07股票问题 {

    public static void main(String[] args) {
        int[] shares = {7, 6, 4, 3, 1};
        int result = shares(shares);
        System.out.println(result);
    }

    /**
     * 前i天的最大收益 = max { 前i-1天的最大收益，第i天的价格 - 前i-1天中的最小价格 }
     */
    public static int shares(int[] shares) {
        int max = 0;
        int min = Integer.MAX_VALUE;

        for (int i = 1; i < shares.length; i++) {
            min = Math.min(min, shares[i - 1]);
            max = Math.max(max, shares[i] - min);
        }

        return max;
    }

}
