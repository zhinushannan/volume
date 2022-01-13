package _02链表._01单链表;

import java.util.Objects;

public class SingleLinkedList<E> {

    private class Node<E> {
        E element;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            return "element=" + element + ", next=" + next.element;
        }
    }

    private Node<E> head;

    private int size;

    public SingleLinkedList() {
        head = new Node<>(null, null);
    }

    /**
     * 将元素追加到末尾
     * @param element 需要追加的元素
     * @return 是否追加成功
     */
    public boolean add(E element) {
        if (null == element) {
            throw new NullPointerException();
        }
        Node<E> temp = this.head;

        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node<>(element, null);
        this.size++;
        return true;
    }

    /**
     * <p>将元素追加到指定位置</p>
     * <p></p>
     * <p>1、获取指定位置的前一个结点和指定位置的结点</p>
     * <p>2、构造新结点</p>
     * <p>3、将前一个结点的next指针指向构造的新结点</p>
     * <p>4、将新结点的next指针指向原指定位置的结点</p>
     * <p>5、size++</p>
     * @param element 需要追加的元素
     * @return 是否追加成功
     */
    public boolean add(int index, E element) {
        if (index == this.size - 1) {
            add(element);
        }
        if (null == element) {
            throw new NullPointerException();
        }
        if (index == 0) {
            Node<E> first = this.head.next;

            this.head.next = new Node<>(element, null);
            this.head.next.next = first;
            return true;
        }
        // 获取前一个结点
        Node<E> pre = this.getNode(index - 1);
        // 构造新结点
        Node<E> current = new Node<>(element, null);
        // 当前结点
        Node<E> next = pre.next;

        // 将前一个结点的next指针指向构造的新结点
        pre.next = current;
        // 将新结点的next指针指向原指定位置的结点
        current.next = next;

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
     *
     * <p>1、获取指定结点的前一个结点</p>
     * <p>2、获取指定结点</p>
     * <p>3、获取指定结点的下一个结点</p>
     * <p>4、将指定结点的next指针置空</p>
     * <p>5、将上一个结点的next指针指向下一个结点</p>
     *
     * @param index 指定索引
     * @return 返回被删除的元素
     */
    public E remove(int index) {
        // 当指定结点时首结点时
        if (index == 0) {
            Node<E> current = this.getNode(index);
            E element = current.element;
            this.head = current;
            this.head.element = null;
            this.size--;
            return element;
        }
        // 当指定结点为尾结点时
        if (index == this.size - 1) {
            Node<E> pre = this.getNode(index - 1);
            Node<E> current = pre.next;

            pre.next = null;
            return current.element;
        }

        Node<E> pre = this.getNode(index - 1);

        Node<E> current = pre.next;
        E element = current.element;

        pre.next = current.next;

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
