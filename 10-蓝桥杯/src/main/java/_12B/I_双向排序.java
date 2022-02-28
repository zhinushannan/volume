package _12B;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class I_双向排序 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        int length = Integer.parseInt(s.split(" ")[0]);
        int times = Integer.parseInt(s.split(" ")[1]);

        Integer[]arr = new Integer[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }

        for (int i = 0; i < times; i++) {
            s = scanner.nextLine();

            String[] s1 = s.split(" ");

            int index = Integer.parseInt(s1[1]);

            if (s1[0].equals("0")) {
                descSort(arr, index);
            } else {
                ascSort(arr, index);
            }
        }

        System.out.println(Arrays.toString(arr));

    }

    /**
     * 升序排序
     * 从 index 到 末尾 进行升序排序
     */
    public static void ascSort(Integer[] arr, int index) {
        Arrays.sort(arr, index - 1, arr.length);
    }

    /**
     * 降序排序
     * 对 0 -- index 范围内进行降序排序
     */
    public static void descSort(Integer[] arr, int index) {
        Arrays.sort(arr, 0, index, Collections.reverseOrder());
    }

}
