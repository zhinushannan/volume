package _11B;

import java.text.NumberFormat;
import java.util.Scanner;

public class F_成绩分析 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int length = scanner.nextInt();

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int count = 0;

        int sc;
        for (int i = 0; i < length; i++) {
            sc = scanner.nextInt();

            min = Math.min(min, sc);
            max = Math.max(max, sc);
            count += sc;
        }

        System.out.println(max);
        System.out.println(min);
        System.out.printf("%.2f%n", count * 1.0 / length);

    }

}
