package _02链表._02双向链表;

import java.util.Objects;

public class DoubleLinkedList<E> {

    private class Node<E> {
        E element;
        Node<E> pre;
        Node<E> next;

        Node(Node<E> pre, E element, Node<E> next) {
            this.pre = pre;
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            return "pre=" + pre.element + ", element=" + element + ", next=" + next.element;
        }
    }

    private Node<E> head;

    private Node<E> tail;

    private int size;

    public DoubleLinkedList() {
        this.head = new Node<>(null, null, null);
        this.tail = new Node<>(null, null, null);
        this.head.next = tail;
        this.tail.pre = this.head;
    }

    /**
     * 将元素追加到末尾
     * @param element 需要追加的元素
     * @return 是否追加成功
     */
    public boolean add(E element) {
        Node<E> current = new Node<>(null, element, null);

        Node<E> tail1 = this.tail;
        Node<E> tail2 = tail1.pre;

        tail2.next = current;
        current.pre = tail2;

        tail1.pre = current;
        current.next = tail1;

        this.size++;
        return true;
    }

    /**
     * 将元素追加到指定位置
     * @param element 需要追加的元素
     * @return 是否追加成功
     */
    public boolean add(int index, E element) {
        if (null == element) {
            throw new NullPointerException();
        }
        // 先获取到指定结点
        Node<E> current = this.getNode(index);
        Node<E> pre = current.pre;
        pre.next = new Node<>(pre, element, current);
        this.size++;
        return true;
    }

    /**
     * 清空链表
     */
    public void clear() {
        this.head.next = this.tail;
        this.tail.pre = this.head;
        this.size = 0;
    }

    /**
     * 是否包好指定元素
     * @param o 指定元素
     * @return 是true，否false
     */
    public boolean contains(Object o) {
        return this.indexOf(o) != -1;
    }

    /**
     * 获得指定索引位置的元素
     * @param index 指定索引
     * @return 返回指定远元素
     */
    public E get(int index) {
        Node<E> current = getNode(index);
        return current.element;
    }

    /**
     * 根据指定索引获取指定的结点
     * @param index 指定索引
     * @return 指定结点
     */
    private Node<E> getNode(int index) {
        /*
        若链表的长度是5，则索引范围是 0-4，所以size的范围是 [0, size)
         */
        if (index < 0 || index > this.size - 1) {
            throw new IndexOutOfBoundsException();
        }

        // 当目标索引在前半部分使用从前找，在后半部分使用从后找
        return index < this.size / 2 ? this.getNodeFromHead(index) : this.getNodeFromTail(index);
    }

    private Node<E> getNodeFromHead(int index) {
        int tempIndex = 0;
        Node<E> tempNode = this.head.next;
        while (tempIndex != index) {
            tempNode = tempNode.next;
            tempIndex++;
        }
        return tempNode;
    }

    private Node<E> getNodeFromTail(int index) {
        int tempIndex = this.size - 1;
        Node<E> tempNode = this.tail.pre;
        while (tempIndex != index) {
            tempNode = tempNode.pre;
            tempIndex--;
        }
        return tempNode;
    }

    /**
     * 返回指定元素的索引
     * @param o 指定元素
     * @return 返回索引
     */
    public int indexOf(Object o) {
        if (o == null) {
            return -1;
        }
        int headIndex = 0;
        int tailIndex = this.size - 1;

        Node<E> head = this.head.next;
        Node<E> tail = this.tail.pre;

        // 双向遍历，使用双向指针，牺牲内存换取时间
        while (headIndex < tailIndex) {
            if (head.element.equals(o)) {
                return headIndex;
            }
            if (tail.element.equals(o)) {
                return tailIndex;
            }
            headIndex++;
            tailIndex--;

            head = head.next;
            tail = tail.pre;
        }

        return -1;
    }

    /**
     * 判断链表是否为空
     * @return 空true，非空false
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * 移除指定位置的元素
     * @param index 指定索引
     * @return 返回被删除的元素
     */
    public E remove(int index) {
        Node<E> current = this.getNode(index);

        E element = current.element;

        Node<E> pre = current.pre;
        Node<E> next = current.next;

        if (this.head.equals(pre)) {
            /*
            当删除的是第一个结点时，因为第一个结点的前一个就是首结点，
            则直接令首结点 为 当前结点即可，但是同时要将pre和element置空
             */
            this.head = current;
            this.head.pre = null;
            this.head.element = null;
        } else if (null == next) {
            /*
            如果当前结点是尾结点时，则只需要将尾结点的前一个结点的next指针置空即可
             */
            pre.next = null;
        } else {
            /*
            当前结点既不是首结点也不是尾结点
            需要将当前结点的pre和next指针置空，
            然后让当前结点的前一个结点的next指针指向下一个结点，
            让下一个结点的pre指针指向上一个结点
             */
            current.pre.next = current.next;
            current.next.pre = current.pre;
        }

        this.size--;
        return element;
    }

    /**
     * 删除给定元素
     * @param o 给定元素
     * @return 是否删除成功
     */
    public boolean remove(Object o) {
        int i = this.indexOf(o);
        if (i == -1) {
            return false;
        }
        this.remove(i);
        return true;
    }

    /**
     * 返回链表长度
     * @return 链表长度
     */
    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("[");

        Node<E> temp = this.head;
        while (!temp.next.equals(this.tail)) {
            temp = temp.next;
            string.append(temp.element).append(", ");
        }

        string.replace(string.length() - 2, string.length(), "]");

        return string.toString();
    }


}
