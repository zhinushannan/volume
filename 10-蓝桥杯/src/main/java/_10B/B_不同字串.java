package _10B;

import java.util.HashSet;
import java.util.Set;

public class B_不同字串 {

    static Set<String> strings = new HashSet<>();

    public static void main(String[] args) {

        String str = "0100110001010001";
//        String str = "aaab";

        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j <= str.length(); j++) {
                strings.add(str.substring(i, j));
            }
        }

        System.out.println(strings.size());

    }

}
