package _10B;

public class C_数列求值 {

    public static void main(String[] args) {

        int a = 1, b = 1, c = 1, d = 0;

        int N = 20190324;

        for (int i = 3; i < N; i++) {
            // 防止精度丢失
            d = (a + b + c) % 1000000;
            a = b;
            b = c;
            c = d;
        }

        System.out.println(d);

    }

}
