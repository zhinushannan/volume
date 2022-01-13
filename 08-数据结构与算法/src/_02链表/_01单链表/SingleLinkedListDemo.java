package _02链表._01单链表;

import org.junit.Test;

public class SingleLinkedListDemo {

    @Test
    public void testAdd() {
        SingleLinkedList<String> linkedList = new SingleLinkedList<>();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add("5");

        // 添加元素
        linkedList.add("6");
        linkedList.add(3, "4");
        System.out.println(linkedList);

        // 删除元素
        linkedList.remove(0);
        linkedList.remove(linkedList.size() - 1);
        System.out.println(linkedList);

        linkedList.add(0, "1");
        linkedList.add("6");
        linkedList.remove("6");
        System.out.println(linkedList);
        linkedList.add("6");

        // 查找元素
        System.out.println(linkedList.get(0));
        System.out.println(linkedList.indexOf("6"));
        System.out.println(linkedList.indexOf("7"));


    }

}
