package _02链表._03单向环形链表;

import _02链表._01单链表.SingleLinkedList;
import org.junit.Test;

public class CircleSingleLinkedListDemo<E> {

    /**
     * 约瑟夫测试用例
     */
    @Test
    public void hosephu() {
        CircleSingleLinkedList<String> linkedList = new CircleSingleLinkedList<>();

        for (int i = 1; i <= 10; i++) {
            linkedList.add(i + "");
        }

        linkedList.hosephu(0, 3);

    }

    @Test
    public void testAdd() {
        SingleLinkedList<String> linkedList = new SingleLinkedList<>();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add("4");
        linkedList.add("5");
        linkedList.add("6");
        linkedList.add("7");
        linkedList.add("8");
        linkedList.add("9");
        linkedList.add("10");

        linkedList.remove("3");
        linkedList.remove("6");
        linkedList.remove("9");
        linkedList.remove("2");
        linkedList.remove("7");
        linkedList.remove("1");
        linkedList.remove("8");
        linkedList.remove("5");
        linkedList.remove("10");
        linkedList.remove("4");


    }




}
