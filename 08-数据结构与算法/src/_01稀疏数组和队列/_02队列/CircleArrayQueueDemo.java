package _01稀疏数组和队列._02队列;

import utils.ArrayUtil;

public class CircleArrayQueueDemo {
}

class CircleArrayQueue {

    private int maxSize;

    private int front;

    private int rear;

    private int[] arr;

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[this.maxSize];
        this.front = 0;
        this.rear = 0;
    }

    /**
     * 判断队列是否已满
     * @return true - 满，false - 未满
     */
    public boolean isFUll() {
        return (rear + 1) % maxSize == front;
    }

    /**
     * 判断队列是否为空
     * @return true - 空， false - 非空
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 当前队列有效数据个数
     * @return 返回有效数据个数
     */
    public int size() {
        return (rear + maxSize - front) % maxSize;
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
        // rear++;
        // 取模的目的：防止指针大于maxSize
        rear = (rear + 1) % maxSize;
        arr[rear] = item;
        return true;
    }

    /**
     * 获取队列数据，出队列
     * @return 返回并弹出队首元素
     */
    public int getQueue() {
        int data = headQueue();
        // front++;
        front = (front + 1) % maxSize;
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
