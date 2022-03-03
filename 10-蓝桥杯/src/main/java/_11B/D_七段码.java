package _11B;

import java.util.*;

/**
 * 思路：
 * 1、查找所有组合的可能性，存入all集合中
 * 2、对所有可能性进行判断，将符合要求的存入result集合中
 *
 * 目前问题：
 * 如何对字符串进行判断，猜测使用bfs算法
 *
 */
public class D_七段码 {

    static String[] strs = {"a", "b", "c", "d", "e", "f", "g"};

    static Map<String, String> kv = new HashMap<>();

    static Set<String> all = new HashSet<>();

    static Set<String> result = new HashSet<>();

    public static void main(String[] args) {
        init();
        getResult();
        System.out.println(result.size());
    }

    public static void getResult() {
        for (int i = 1; i <= strs.length; i++) {
            dfsGetAll("", 0, i);
        }
        for (String s : all) {
            if (judge(s)) {
                result.add(s);
            }
        }
    }

    public static void dfsGetAll(String str, int index, int length) {
        if (str.length() == length) {
            all.add(str);
            return;
        }

        for (int i = index; i < strs.length; i++) {
            dfsGetAll(str + strs[i], i + 1, length);
        }
    }

    public static boolean judge(String str) {
        return false;
    }

    public static void init() {
        kv.put("a", "bf");
        kv.put("b", "acg");
        kv.put("c", "bdg");
        kv.put("d", "ce");
        kv.put("e", "dfg");
        kv.put("f", "age");
        kv.put("g", "bcef");
    }

}
