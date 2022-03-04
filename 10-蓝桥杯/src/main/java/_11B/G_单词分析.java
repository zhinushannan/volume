package _11B;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class G_单词分析 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] split = scanner.nextLine().split("");

        Map<String, Integer> wordCount = new HashMap<>();

        for (String s : split) {
            Integer integer = wordCount.get(s);
            if (null == integer || 0 == integer) {
                wordCount.put(s, 1);
            } else {
                wordCount.put(s, wordCount.get(s) + 1);
            }
        }

        int maxCount = 0;
        String maxWord = "";

        for (String s : wordCount.keySet()) {
            if (wordCount.get(s) > maxCount) {
                maxCount = wordCount.get(s);
                maxWord = s;
            }
        }

        System.out.println(maxWord);
        System.out.println(maxCount);

    }

}
