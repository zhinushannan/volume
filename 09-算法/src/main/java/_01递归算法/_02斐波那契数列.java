package _01递归算法;

public class _02斐波那契数列 {

    public static void main(String[] args) {
        System.out.println(f(1));
        System.out.println(f(2));
        System.out.println(f(3));
        System.out.println(f(4));
        System.out.println(f(5));
        System.out.println(f(6));
        System.out.println(f(7));
        System.out.println(f(8));
    }

    /**
     * 1 1 2 3 5 8 13
     *
     * f(n) = f(n - 1) + f(n - 2)
     *
     */
    public static int f(int n) {
        if (n <= 2) {
            return 1;
        }
        return f(n - 1) + f(n - 2);
    }

}
