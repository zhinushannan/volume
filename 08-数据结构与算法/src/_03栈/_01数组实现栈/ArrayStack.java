package _03栈._01数组实现栈;

public class ArrayStack<E> {

    /** 栈的最大容量 */
    private int maxSize;

    /** 栈的顶指针 */
    private int top;

    /** 栈的元素 */
    private Object[] elements;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        elements = new Object[this.maxSize];
        this.top = 0;
    }

    /**
     * 向栈顶压入一个数据
     * @param item 数据
     * @return 返回压入的数据
     */
    public E push(E item) {
        if (full()) {
            throw new IndexOutOfBoundsException("栈满");
        }
        /*
        this.elements[this.top++] = item;
        等同于下
        this.elements[this.top] = item;
        this.top++;

        int a = 0;
        b = a++;
        这个过程是先执行赋值，再执行运算；
        整个过程结束后，b = 0, a = 1

        int a = 0;
        b = ++a;
        这个过程是先执行运算，在执行赋值
        整个过程结束后，b = 1, a = 1

         */
        this.elements[this.top++] = item;
        return item;
    }

    /**
     * 弹出栈顶数据
     * @return 栈顶数据
     */
    public E pop() {
        if (empty()) {
            throw new IndexOutOfBoundsException("栈空");
        }
        return (E) this.elements[--this.top];
    }

    /**
     * 弹出栈顶数据但不不移除
     * @return 栈顶数据
     */
    public E peek() {
        if (empty()) {
            throw new IndexOutOfBoundsException("栈空");
        }
        return (E) this.elements[this.top - 1];
    }

    public boolean empty() {
        return this.top == 0;
    }

    public boolean full() {
        return this.top == this.maxSize;
    }

    public int size() {
        return this.top;
    }

    @Override
    public String toString() {
        StringBuffer string = new StringBuffer("[");

        for (int i = 0; i < this.top; i++) {
            string.append(this.elements[i]).append(", ");
        }

        string.replace(string.length() - 2, string.length(), "]");

        return string.toString();
    }
}
