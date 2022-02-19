package _12B;

import java.util.Arrays;

public class B_卡片 {

    // 3181
    public static void main(String[] args) {
        // 创建一个长度为10的数组，下标即数字，从0到9
        int[] cards = new int[10];
        // 全部填充为2021，代表每一种卡片都有2021张
        Arrays.fill(cards, 2021);

        // temp - 用来计数
        int temp = 0;
        // flag - 用来标记循环的条件
        boolean flag = true;
        while (flag) {
            // 让 temp 自增 1 ，并将其转化为字符数组
            temp++;
            char[] chars = String.valueOf(temp).toCharArray();
            /*
               遍历字符数组，判断每一种卡片的个数
               如果等于 0 ，则结束，即 flag = false
               如果不等于 0 ，则继续循环，将该卡片的数量减 1
             */
            for (char ch : chars) {
                // ASCII 码和数字的关系 ： '1' - 48 = 1
                cards[ch - 48]--;
                if (cards[ch - 48] == 0) {
                    flag = false;
                }
            }
        }

        System.out.println(temp);

    }

}
