package _02链表._03单向环形链表;

import java.util.Objects;

public class CircleSingleLinkedList<E> {

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

    public CircleSingleLinkedList(){
        this.head = new Node<>(null, null);
    }

    /**
     * 将元素追加到末尾
     *
     * <p>1、获取最后一个元素</p>
     * <p>2、构造新结点</p>
     * <p>3、将最后一个元素的next指针指向新结点</p>
     * <p>4、将新结点的next指针指向head</p>
     * <p></p>
     *
     * @param element 需要追加的元素
     * @return 是否追加成功
     */
    public boolean add(E element) {
        if (null == element) {
            throw new NullPointerException();
        }

        if (isEmpty()) {
            this.head.next = new Node<>(element, this.head);
            this.size++;
            return true;
        }

        Node<E> temp = this.head;

        while (!temp.next.equals(this.head)) {
            temp = temp.next;
        }

        // 构造新结点
        Node<E> current = new Node<>(element, null);

        // 尾结点的next指针指向
        temp.next = current;

        // 新结点的next指针
        current.next = this.head;

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
        Node<E> temp = this.head.next;
        int index = 0;
        while (null != temp) {
            if (Objects.equals(o, temp)) {
                return index;
            }
            index++;
            temp = temp.next;
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
            this.head.next = current.next;
            this.size--;
            return element;
        }
        // 当指定结点为尾结点时
        if (index == this.size - 1) {
            Node<E> pre = this.getNode(index - 1);
            Node<E> current = pre.next;

            pre.next = this.head;
            this.size--;
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

        Node<E> temp = this.head.next;
        while (!Objects.equals(temp, this.head)) {
            string.append(temp.element).append(", ");
            temp = temp.next;
        }

        string.replace(string.length() - 2, string.length(), "]");

        return string.toString();
    }

    /**
     *
     * <java>
     *     public void hosephu() {
     *         CircleSingleLinkedList<String> linkedList = new CircleSingleLinkedList<>();
     *
     *         // i <= 10， 10是人数
     *         for (int i = 1; i <= 10; i++) {
     *             linkedList.add(i + "");
     *         }
     *
     *         // 从第一人开始报数是0，第二人是1
     *         // 3是指报数123结束
     *         linkedList.hosephu(0, 3);
     *
     *     }
     * </java>
     *
     * @param start 开始的位置
     * @param m 每次跳过的人数
     */
    public void hosephu(int start, int m) {

        Node<E> pointer = getNode(start);

        while (size() != 1) {
            int index = 1;
            while (index < m) {
                pointer = pointer.next;
                if (pointer.equals(this.head)) {
                    pointer = pointer.next;
                }
                index++;
            }

            System.out.println(pointer.element + "出圈");
            if (pointer.equals(this.head)) {
                pointer = pointer.next;
            }
            Node<E> out = pointer;
            pointer = pointer.next;
            if (pointer.equals(this.head)) {
                pointer = pointer.next;
            }
            remove(out);
        }

        System.out.println(this.toString());


    }

}
