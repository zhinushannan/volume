package _01稀疏数组和队列._02队列;

import utils.ArrayUtil;

public class ArrayQueueDemo {

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(10);
        queue.addQueue(0);
        queue.addQueue(1);
        queue.addQueue(2);
        queue.addQueue(3);
        queue.showQueue();
        queue.getQueue();
        queue.headQueue();
        queue.showQueue();
    }

}

class ArrayQueue {

    // 表示数组的最大容量
    private int maxSize;

    /**
     * 队列头部指针
     */
    private int front;

    /**
     * 队列尾部指针
     */
    private int rear;

    // 用于存放数据
    private int[] arr;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[this.maxSize];
        this.front = 0;
        this.rear = -1;
    }

    /**
     * 判断队列是否已满
     * @return true - 满，false - 未满
     */
    public boolean isFUll() {
        return rear == maxSize - 1;
    }

    /**
     * 判断队列是否为空
     * @return true - 空， false - 非空
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 添加数据到队列
     * @param item 需要添加的元素
     * @return 返回是否添加成功
     */
    public boolean addQueue(int item) {
        // 判断队列是否已满
        if (isFUll()) {
            System.out.println("队列已满，不能加入数据");
            return false;
        }
        rear++;
        arr[rear] = item;
        return true;
    }

    /**
     * 获取队列数据，出队列
     * @return 返回并弹出队首元素
     */
    public int getQueue() {
        int data = headQueue();
        front++;
        return data;
    }

    /**
     * 显示队列头部数列，但不弹出
     * @return 返回头部数列
     */
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空");
        }
        return arr[front];
    }

    /**
     * 显示队列所有元素
     */
    public void showQueue() {
        ArrayUtil.printArray1(arr, front, rear);
    }

}
