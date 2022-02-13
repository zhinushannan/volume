package _01递归算法;

import java.util.Arrays;

public class _04八皇后问题 {

    private static int count = 0;

    public static void main(String[] args) {
        int[][] queue = new int[8][8];
        queue(0, queue);
        System.out.println(count);
    }

    public static void queue(int i, int[][] queue) {
        // 递归出口，当 i == queue.length 时，表明已经到达最大层数的下一层了，即已经遍历完了
        if (i == queue.length) {
            count++;
            return;
        }

        for (int k = 0; k < queue.length; k++) {
            // 寻找当前层数中的可放置的坐标
            if (queue[i][k] == 0) {
                int[][] copy = copyOf(queue);
                mark(i, k, queue);
                queue(i + 1, queue);
                // 将queue数组还原为放置前，回溯
                queue = copyOf(copy);
            }
        }
    }


    public static void mark(int i, int j, int[][] queue) {
        // 横向竖向标记为 -1
        for (int k = 0; k < queue.length; k++) {
            queue[i][k] = -1;
            queue[k][j] = -1;
        }

        // 逆时针 45° 标记为 -1
        int x = i > j ? i - j : 0;
        int y = i > j ? 0 : j - i;
        while (x < queue.length && y < queue.length) {
            queue[x++][y++] = -1;
        }

        // 顺时针 45° 标记为 -1
        x = i + j < queue.length ? 0 : i + j - (queue.length - 1);
        y = i + j < queue.length ? i + j : queue.length - 1;
        while (x < queue.length && y >= 0) {
            queue[x++][y--] = -1;
        }
        queue[i][j] = 1;
    }

    /**
     * 复制二维数组不能直接用 Arrays.copyOf
     * 原因：该方式的深拷贝只支持一维数组
     *
     * 数组也是对象，如果使用该方法进行深拷贝，使用的是 public static <T> T[] copyOf(T[] original, int newLength) 函数，
     * 对于对象来说，深拷贝是通过反射机制获取其setter方法对对象进行赋值，然而数组没有setter接口，所以只能拷贝其地址，和浅拷贝效果相同
     */
    public static int[][] copyOf(int[][] original) {
        int[][] newArr = new int[original.length][original.length];
        for (int i = 0; i < original.length; i++) {
            newArr[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return newArr;
    }

}
