package _10B;

import java.util.HashSet;
import java.util.Set;

public class D_数的分解 {

    public static void main(String[] args) {
        Set<String> strings = new HashSet<>();

        for (int i = 1; i <= 2019; i++) {
            for (int j = 1; j <= 2019; j++) {
                System.out.println(i + " " + j);
                for (int k = 1; k <= 2019; k++) {
                    if (i + j + k == 2019) {
                        String string = getString(i, j, k);
                        if (null != string) {
                            strings.add(string);
                        }
                    }
                }
            }
        }

        System.out.println(strings.size());

    }

    public static String getString(int a, int b, int c) {
        if (
                // 三个各不相同的
                a == b || a == c || b == c ||
                // 不包含2和4
                ("" + a + b + c).contains("2") || ("" + a + b + c).contains("4")
        ) {
            return null;
        }

        int temp;
        if (a > b) {
            temp = a;
            a = b;
            b = temp;
        }

        if (a > c) {
            temp = a;
            a = c;
            c = temp;
        }

        if (b > c) {
            temp = b;
            b = c;
            c = temp;
        }

        return a + "," + b + "," + c;
    }

}
