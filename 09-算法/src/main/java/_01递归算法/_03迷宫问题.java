package _01递归算法;

public class _03迷宫问题 {

    public static int[][] maze = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 0, 0, 0, 0, 1},
            {1, 0, 0, 1, 0, 1, 0, 0, 1},
            {1, 0, 0, 1, 0, 1, 0, 0, 1},
            {1, 0, 0, 1, 0, 1, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    public static void main(String[] args) {
        doWalk(1, 1);

        for (int[] ints : maze) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    /**
     * 1、判断当前坐标是否未被走过
     * 2、判断当前坐标的上下左右是否有未被走过且能走得通的
     *      如果有：说明当前坐标可通，标记为2
     *      如果无：说明当前坐标不可通，标记3
     * 3、如果终点被走过了，则说明已经走通了
     *
     * 判断不可通的情况和理由：
     * 1 2 1
     * 1 2 1
     * 1 1 1
     * 理由：因为走过的路再走回去是没有意义的，所以只需要判断三面是否都是1即可
     *
     * 注意：如果走的方向不是绕圈走的话，路径可能会好多，绕圈走最后只会得到一个路径
     */
    public static boolean doWalk(int x, int y) {
        // 如果终点的标记是2，则意味着已经走通了
        if (maze[5][7] == 2) {
            return true;
        }
        // 如果当前标记是0
        if (maze[x][y] == 0){
            // 假设当前路是行得通的
            maze[x][y] = 2;
            // 判断当前坐标的上下左右是否都没被走过且能走得通，如果是，则返回true
            if (doWalkDown(x, y) || doWalkRight(x, y) || doWalkUp(x, y) || doWalkLeft(x, y)) {
                return true;
            }
            // 如果不是，则说明当前坐标是行不通的，将当前坐标标记为3，返回false
            maze[x][y] = 3;
            return false;
        }
        // 如果终点没走通且当前坐标的标记不是0，则返回false
        return false;
    }


    public static boolean doWalkLeft(int x, int y) {
        return doWalk(x - 1, y);
    }

    public static boolean doWalkRight(int x, int y) {
        return doWalk(x + 1, y);
    }

    public static boolean doWalkUp(int x, int y) {
        return doWalk(x, y - 1);
    }

    public static boolean doWalkDown(int x, int y) {
        return doWalk(x, y + 1);
    }

}
