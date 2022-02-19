package _04回溯法;

import java.util.ArrayList;
import java.util.List;

/**
 * n 个不重复的数，全排列共有 n! 个
 */
public class _01全排列问题 {

    private static List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        int[]nums = {1, 2, 3, 4, 5};
        backtrack(nums, new StringBuilder());
        System.out.println(list.size());
    }


    public static void backtrack(int[]nums, StringBuilder str) {
        if (str.length() == nums.length) {
            list.add(str.toString());
            System.out.println(str.toString());
            return;
        }
        for (int num : nums) {
            // 如果当前结果中不包含指定的元素，则符合要求
            if (!str.toString().contains(String.valueOf(num))) {
                // 追加
                str.append(num);
                // 执行
                backtrack(nums, str);
                // 回溯
                str.deleteCharAt(str.length() - 1);
            }
        }
    }

}
