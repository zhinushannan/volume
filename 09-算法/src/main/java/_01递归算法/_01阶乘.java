package _01递归算法;

public class _01阶乘 {

    public static void main(String[] args) {
        System.out.println(run(5));
    }

    public static int run(int num) {
        if (num == 1) {
            return 1;
        }
        return run(num - 1) * num;
    }

}
