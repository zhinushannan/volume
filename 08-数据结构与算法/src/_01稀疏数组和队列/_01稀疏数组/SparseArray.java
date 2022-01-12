package _01稀疏数组和队列._01稀疏数组;

import utils.ArrayUtil;
import utils.SplitLineUtil;

import java.util.Arrays;

public class SparseArray {

    public static void main(String[] args) {

        // 得到原数组
        int[][] chessArrRaw = getRawArr();

        // 得到稀疏数组
        int[][] sparseArr = rawToSparse(chessArrRaw);

        // 通过稀疏数组恢复
        int[][] rawArr = sparseToRaw(sparseArr);

        System.out.println("========== 检查恢复的数组与原数组是否相同 ==========");
        System.out.println(Arrays.deepEquals(chessArrRaw, rawArr));
    }

    /**
     * <p>创建一个原始的二维数组 11*11</p>
     * <p>0表示没有棋子，1表示黑子，2表示白子</p>
     *
     * <p>其中【第2行第3列】为黑子；【第3行第4列】、【第5行第6列】为白子</p>
     *
     * @return 返回原数组
     */
    public static int[][] getRawArr() {
        int[][] chessArr = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        chessArr[4][5] = 2;
        SplitLineUtil.printLineWithoutFeed("输出原始数组");
        ArrayUtil.printArray2(chessArr);

        return chessArr;
    }

    /**
     *
     * <p>将二维数组转换成稀疏数组</p>
     * <p>==========</p>
     * <p>稀疏数组是一个列数为3，行数为非无效数字的个数+1</p>
     * <p>第一行记录二维数组的 行数、列数、有效数字个数</p>
     * <p>往后几行依次记录第n个有效数字的 行索引、列索引、数值</p>
     *
     * @param chessArrRaw 原来的二维数组
     * @return 返回稀疏数组
     */
    public static int[][] rawToSparse(int[][] chessArrRaw) {
        // 1. 遍历二维数组，得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArrRaw[i][j] != 0) {
                    sum++;
                }
            }
        }

        // 2. 创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        // 给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        // 遍历二维数组，将非0的值存放到sparseArr中
        int count = 0; // 记录是第几个非0数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArrRaw[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArrRaw[i][j];
                }
            }
        }

        SplitLineUtil.printLineWithoutFeed("输出稀疏数组");
        ArrayUtil.printArray2(sparseArr);

        return sparseArr;
    }

    /**
     * 将稀疏数组还原成二维数组
     * @param sparseArr 稀疏数组
     * @return 还原后的二维数组
     */
    public static int[][] sparseToRaw(int[][] sparseArr) {
        int[][] rawArr = new int[sparseArr[0][0]][sparseArr[0][1]];

        for (int i = 1; i < sparseArr.length; i++) {
            rawArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        System.out.println("========== 恢复后的二维数组 ==========");
        ArrayUtil.printArray2(rawArr);

        return rawArr;
    }

}
