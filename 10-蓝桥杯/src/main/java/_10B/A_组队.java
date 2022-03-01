package _10B;

import java.lang.reflect.Array;
import java.util.Arrays;

public class A_组队 {

    public static void main(String[] args) {

        int[][] team =
                {
                        {97, 90, 0, 0, 0},
                        {92, 85, 96, 0, 0},
                        {0, 0, 0, 0, 93},
                        {0, 0, 0, 80, 86},
                        {89, 83, 97, 0, 0},

                        {82, 86, 0, 0, 0},
                        {0, 0, 0, 87, 90},
                        {0, 97, 96, 0, 0},
                        {0, 0, 89, 0, 0},
                        {95, 99, 0, 0, 0},

                        {0, 0, 96, 97, 0},
                        {0, 0, 0, 93, 98},
                        {94, 91, 0, 0, 0},
                        {0, 83, 87, 0, 0},
                        {0, 0, 98, 97, 98},

                        {0, 0, 0, 93, 86},
                        {98, 83, 99, 98, 81},
                        {93, 87, 92, 96, 98},
                        {0, 0, 0, 89, 92},
                        {0, 99, 96, 95, 81},
                };

        int maxScore = getMaxScore(team, 0, 0);
        System.out.println(maxScore);

    }

    public static int getMaxScore(int[][] team, int number, int score) {
        // 递归出口
        if (number == 5) {
            return score;
        }

        int maxScore = score;

        for (int i = 0; i < team.length; i++) {
            int temp = team[i][number];
            if (temp != 0) {
                int[] ints = Arrays.copyOf(team[i], team[i].length);
                Arrays.fill(team[i], 0);
                // 取最大值
                maxScore = Math.max(getMaxScore(team, number + 1, score + temp), maxScore);
                // 回溯
                team[i] = ints;
            }
        }

        return maxScore;
    }

}
