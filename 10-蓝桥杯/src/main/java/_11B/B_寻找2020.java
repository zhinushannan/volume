package _11B;

import java.util.Scanner;

public class B_寻找2020 {

    public static void main(String[] args) {

        int[][] arr = new int[300][300];

        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 300; i++) {
            String[] split = scanner.nextLine().split("");
            for (int j = 0; j < 300; j++) {
                arr[i][j] = Integer.parseInt(split[j]);
            }
        }

        int count = 0;

        // 横向
        for (int[] ints : arr) {
            for (int j = 0; j < ints.length - 3; j++) {
                if (
                        ints[j] == 2 &&
                                ints[j + 1] == 0 &&
                                ints[j + 2] == 2 &&
                                ints[j + 3] == 0
                ) {
                    count++;
                }
            }
        }

        // 纵向
        for (int i = 0; i < arr.length - 3; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (
                        arr[i][j] == 2 &&
                                arr[i + 1][j] == 0 &&
                                arr[i + 2][j] == 2 &&
                                arr[i + 3][j] == 0
                ) {
                    count++;
                }
            }
        }

        // 左斜向
        for (int i = 0; i < arr.length - 3; i++) {
            for (int j = 0; j < arr[i].length - 3; j++) {
                if (
                        arr[i][j] == 2 &&
                                arr[i + 1][j + 1] == 0 &&
                                arr[i + 2][j + 2] == 2 &&
                                arr[i + 3][j + 3] == 0
                ) {
                    count++;
                }
            }
        }

        System.out.println(count);

    }

}
