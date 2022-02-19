package _12B;

import java.util.HashSet;
import java.util.Set;

/**
 * 解题思路：
 * 1、直线的表示方法：y=kx+b，即每一个(k, b)都对应着唯一的一条直线，那么字符串 "k b" 的个数就是直线的个数
 * 2、在设计方案时要注意此时不能再使用浮点型了，因为容易出现精度丢失的情况，应该用字符串分数的形式表示，例如：0.2应该表示为 "1/5"
 * 3、在设计方案时要注意斜率不存在、斜率为0的两种特殊情况，同时还要注意正负号的问题(因为 -1/5 和 1/-5 本应该是同一种情况，但是不是同一个字符串)
 */
public class C_直线 {

    // 40257
    public static void main(String[] args) {

        Set<String> lines = new HashSet<>();

        int x = 20;
        int y = 21;

        for (int x1 = 0; x1 < x; x1++) {
            for (int y1 = 0; y1 < y; y1++) {

                for (int x2 = 0; x2 < x; x2++) {
                    for (int y2 = 0; y2 < y; y2++) {
                        if (x1 != x2 || y1 != y2) {
                            lines.add(getKB(x1, y1, x2, y2));
                        }
                    }
                }

            }
        }

        System.out.println(lines.size());

    }

    // 计算 "k b"
    public static String getKB(int x1, int y1, int x2, int y2) {
        // k
        String k;

        int deltaY = y2 - y1;
        int deltaX = x2 - x1;

        // 为了解决正负号问题
        if (deltaY < 0 && deltaX < 0) {
            deltaY = -deltaY;
            deltaX = -deltaX;
        }
        if ((deltaY > 0 && deltaX < 0) || (deltaY < 0 && deltaX > 0)) {
            deltaY = - Math.abs(deltaY);
            deltaX = Math.abs(deltaX);
        }

        // 分别为 斜率不存在、斜率为0、正常情况
        if (deltaX == 0) {
            k = "NAN";
        } else if (deltaY == 0) {
            k = "0";
        } else {

            // 月份
            int divisor = getDivisor(deltaY, deltaX);

            while (divisor != 1) {
                deltaY /= divisor;
                deltaX /= divisor;
                divisor = getDivisor(deltaY, deltaX);
            }

            // 得到 k
            k = deltaY + "/" + deltaX;
        }

        // b
        String b;
        // 分别为 斜率不存在、斜率为0、正常情况
        if (k.equals("NAN")) {
            b = x1 + "";
        } else if (k.equals("0")) {
            b = y1 + "";
        } else {
            int m = y1 * deltaX - x1 * deltaY;
            int n = deltaX;

            // 为了解决正负号问题
            if (m < 0 && n < 0) {
                m = -m;
                n = -n;
            }
            if ((m > 0 && n < 0) || (m < 0 && n > 0)) {
                m = - Math.abs(m);
                n = Math.abs(n);
            }

            if (m == 0) {
                b = null;
            } else {

                // 约分
                int divisor = getDivisor(m, n);

                while (divisor != 1) {
                    m /= divisor;
                    n /= divisor;
                    divisor = getDivisor(m, n);
                }

                // 得到 b
                b = m + "/" + n;
            }

        }


        return k + " " + b;
    }

    // 寻找最大公约数
    public static int getDivisor(int i, int j) {
        i = Math.abs(i);
        j = Math.abs(j);
        if (i == j) {
            return i;
        }
        // 保证 i 比 j 大
        if (j > i) {
            int temp = i;
            i = j;
            j = temp;
        }
        if (i % j == 0) {
            return j;
        }

        for (int k = j; k > 1; k--) {
            if (i % k == 0 && j % k == 0) {
                return k;
            }
        }
        return 1;
    }

}
