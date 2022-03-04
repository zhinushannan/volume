package _11B;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class H_数字三角形 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());

        int[][]number = new int[size][size];

        for (int i = 0; i < size; i++) {
            String[] split = scanner.nextLine().split(" ");
            for (int j = 0; j < split.length; j++) {
                number[i][j] = Integer.parseInt(split[j]);
            }
        }

        int dfs = dfs(number, 0, 0, number[0][0], 0, 0);
        System.out.println(dfs);
    }

    public static int dfs(int[][]number, int i, int j, int count, int left, int right) {
        if (i == number.length - 1) {
            if (Math.abs(left - right) <= 1) {
                return count;
            }
            return -1;
        }

        int max = Integer.MIN_VALUE;

        // 向左下走
        max = Math.max(max, dfs(number, i + 1, j, count + number[i + 1][j], left + 1, right));

        // 向右下走
        max = Math.max(max, dfs(number, i + 1, j + 1, count + number[i + 1][j + 1], left, right + 1));

        return max;
    }


}
