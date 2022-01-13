package _02链表._01单链表;

import java.util.Objects;

public class SingleLinkedList<E> {

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

    private int size;

    public SingleLinkedList() {
        head = new Node<>(null, null, null);
    }

    /**
     * 将元素追加到末尾
     * @param element 需要追加的元素
     * @return 是否追加成功
     */
    public boolean add(E element) {
        Node<E> temp = this.head;

        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node<>(temp, element, null);
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
        this.head.next = null;
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

        int tempIndex = 0;
        /*
         因为head节点的element是null，第一次添加的节点是head的next，
         所以第一个节点应该是head.next
         */
        Node<E> tempNode = this.head.next;
        while (tempIndex != index) {
            tempNode = tempNode.next;
            tempIndex++;
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
        Node<E> temp = this.head;
        int index = 0;
        while (temp.next != null) {
            temp = temp.next;
            if (Objects.equals(o, temp.element)) {
                return index;
            }
            index++;
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
            current.pre = null;
            current.next = null;
            pre.next = next;
            next.pre = pre;
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
        while (temp.next != null) {
            temp = temp.next;
            string.append(temp.element).append(", ");
        }

        string.replace(string.length() - 2, string.length(), "]");

        return string.toString();
    }


}
