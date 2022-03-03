package _11B;

public class A_门牌制作 {

    public static void main(String[] args) {

        int count = 0;

        for (int i = 1; i <= 2020; i++) {
            int temp = i;
            while (temp != 0) {
                count += temp % 10 == 2 ? 1 : 0;
                temp /= 10;
            }
        }

        System.out.println(count);

    }

}
