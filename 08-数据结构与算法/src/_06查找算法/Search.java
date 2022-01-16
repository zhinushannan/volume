package _06查找算法;

import org.junit.Test;

public class Search {

    @Test
    public void test() {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int target = 10;

        System.out.println(sequentialSearch(arr, target));
        System.out.println(binarySearch(arr, target));
        System.out.println(insertValueSearch(arr, target));

    }

    /**
     *
     * <p>线性(顺序)查找算法</p>
     * <p>遍历数组（可无序），查找对应值</p>
     *
     * @param arr 需要查找的数组
     * @param target 目标值
     * @return 索引
     */
    public static int sequentialSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (target == arr[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     *
     * <p>升序数组二分查找</p>
     * <p>1、确定数组的中间下标</p>
     * <p>2、让查找的数与中间的数做比较，如果目标值大于中间值，则将mid右移，反之则左移（使用left、right、mid指针）</p>
     * <p>3、逆序相反</p>
     *
     * @param arr 升序数组
     * @param target 目标值
     * @return 索引
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = (left + right / 2);

            int value = arr[mid];
            if (target > value) {
                left = mid + 1;
            }
            if (target < value) {
                right = mid - 1;
            }
            if (target == value) {
                return mid;
            }
        }
        return -1;
    }

    /**
     *
     * <p>升序插值查找</p>
     * <p>原理和二分查找一样，把mid的值换成 left + (right - left) * (target - arr[left]) / (arr[right] - arr[left]) </p>
     *
     * @param arr 升序数组
     * @param target 目标值
     * @return 索引
     */
    public static int insertValueSearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) * (target - arr[left]) / (arr[right] - arr[left]);

            int value = arr[mid];
            if (target > value) {
                left = mid + 1;
            }
            if (target < value) {
                right = mid - 1;
            }
            if (target == value) {
                return mid;
            }
        }
        return -1;
    }


}
