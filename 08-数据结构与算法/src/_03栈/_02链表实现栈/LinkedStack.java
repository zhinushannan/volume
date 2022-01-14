package _03栈._02链表实现栈;

public class LinkedStack<E> {

    private class Node<E> {

        private E element;

        private Node<E> next;

        Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    /** 栈底元素，设为空 */
    private Node<E> bottom;

    /** 栈的实际长度 */
    private int size;

    public LinkedStack() {
        this.bottom = new Node<>(null, null);
        this.size = 0;
    }

    public E push(E item) {
        Node<E> temp = this.bottom;

        while (null != temp.next) {
            temp = temp.next;
        }

        temp.next = new Node<>(item, null);
        this.size++;
        return item;
    }

    public E pop() {
        Node<E> temp = getTop2();

        E item = temp.next.element;
        temp.next = null;

        this.size--;
        return item;
    }

    public E peek() {
        Node<E> temp = getTop2();

        return temp.next.element;
    }

    /**
     * 获取栈顶的前一个元素
     * @return
     */
    private Node<E> getTop2() {
        Node<E> temp = this.bottom;

        while (null != temp.next.next) {
            temp = temp.next;
        }

        return temp;
    }

    public boolean empty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("[");

        Node<E> temp = this.bottom;
        while (temp.next != null) {
            temp = temp.next;
            string.append(temp.element).append(", ");
        }

        string.replace(string.length() - 2, string.length(), "]");

        return string.toString();
    }
}
