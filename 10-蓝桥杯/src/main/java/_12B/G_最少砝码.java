package _12B;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * N -- [砝码] -- 个数
 *
 * 1 -- [1] -- 1
 * 2 -- [1, 3] -- 2
 * 3 -- [1, 3] -- 2
 * 4 -- [1, 3] -- 2
 * 5 -- [1, 3, 9] -- 3
 * 6 -- [1, 3, 9] -- 3
 * 7 -- [1, 3, 9] -- 3
 * 8 -- [1, 3, 9] -- 3
 * 9 -- [1, 3, 9] -- 3
 * 10 -- [1, 3, 9] -- 3
 * 11 -- [1, 3, 9] -- 3
 * 12 -- [1, 3, 9] -- 3
 * 13 -- [1, 3, 9] -- 3
 * 14 -- [1, 3, 9, 27] -- 4
 * ......
 *
 * 规律：N -- [1, 3, 9, 27, ...] ， 数字的关系是 3 的 n 次方，且 N 介于 前N项之和 与 最后一位之间，属于等比数列问题
 *
 */
public class G_最少砝码 {

    public static void main(String[] args) {
        int N = 14;
        int sum = 1;
        int next = sum * 3;

        for (int i = 1; i <= 1000000000; i++) {
            if (sum >= N && N < next) {
                System.out.println(i);
                return;
            }

            sum += next;
            next *= 3;
        }

    }

}
