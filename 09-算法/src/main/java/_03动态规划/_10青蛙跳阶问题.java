package _03动态规划;

/**
 * 一只青蛙一次可以跳上 1 级台阶，也可以跳上2 级。求该青蛙跳上一个n 级的台阶总共有多少种跳法。
 */
public class _10青蛙跳阶问题 {

    public static void main(String[] args) {
        System.out.println(frog(3));
    }

    public static int frog(int n) {
        if (n == 2) {
            return 2;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 0) {
            return 0;
        }
        return frog(n - 1) + frog(n - 2);
    }


}
