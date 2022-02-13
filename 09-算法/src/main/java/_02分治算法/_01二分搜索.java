package _02分治算法;

public class _01二分搜索 {

    public static void main(String[] args) {
        int[] arrAsc = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int search = search(arrAsc, 11, 0, arrAsc.length - 1);
        System.out.println(search);
    }

    public static int search(int[] arrAsc, int target, int left, int right) {
        int mid = (left + right) / 2;
        if (left >= right) {
            return -1;
        }
        if (arrAsc[mid] > target) {
            return search(arrAsc, target, left, mid - 1);
        } else if (arrAsc[mid] < target) {
            return search(arrAsc, target, mid + 1, right);
        } else {
            return mid;
        }
    }

}
