package _03动态规划;

/**
 * 你是一个专业的盗贼，计划偷打劫街的房屋。每间屋内都藏有一定的现金，
 * 影响你的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间
 * 相邻的房屋在同一晚上被盗贼闯入，系统会自动报警。
 */
public class _08打家劫舍问题 {

    public static void main(String[] args) {
        int[] rooms1 =  {1, 2, 3, 1};
        int[] rooms2 =  {2, 7, 9, 3, 1};

        int rob1 = rob(rooms1, 0);
        System.out.println(rob1);

        int rob2 = rob(rooms2, 0);
        System.out.println(rob2);

    }

    public static int rob(int[]rooms, int start) {
        if (start >= rooms.length) {
            return 0;
        }

        return Math.max(
                // 不抢，去下一家
                rob(rooms, start + 1),
                // 抢，去下下家
                rob(rooms, start + 2) + rooms[start]
        );
    }

}
