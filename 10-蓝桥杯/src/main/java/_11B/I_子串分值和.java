package _11B;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class I_子串分值和 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                count += f(s.substring(i, j));
            }
        }

        System.out.println(count);

    }

    public static int f(String S) {
        Set<Character> set = new HashSet<>();
        char[] chars = S.toCharArray();
        for (char aChar : chars) {
            set.add(aChar);
        }
        return set.size();
    }

}
