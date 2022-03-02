package _10B;

import java.util.Scanner;

public class F_特别数的和 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();

        int sum = 0;
        for (int j = 1; j <= i; j++) {
            if (judge(j)) {
                sum += j;
            }
        }
        System.out.println(sum);

    }

    public static boolean judge(int i) {
        return String.valueOf(i).contains("2") ||
                String.valueOf(i).contains("0") ||
                String.valueOf(i).contains("1") ||
                String.valueOf(i).contains("9");
    }

}
