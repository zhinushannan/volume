package _03动态规划;

/**
 * 给定两个字符串str1和str2，输出两个字符串的最长公共子串
 */
public class _04最长公共子串 {

    public static void main(String[] args) {
        int i = longestSub("TTACGCCT", "CGTT");
        System.out.println(i);
    }

    /**
     * 【https://www.bilibili.com/video/BV14A411v7mP】
     */
    public static int longestSub(String str1, String str2) {
        int[][]dp = new int[str1.length()][str2.length()];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = Math.max(dp[i][j] + 1, dp[i - 1][j - 1]);
                }
            }
        }
        return dp[str1.length() - 1][str2.length() - 1];
    }

}
