package _03栈._02链表实现栈;

import _03栈._01数组实现栈.ArrayStack;
import org.junit.Test;

public class LinkedStackDemo {

    @Test
    public void test() {

        ArrayStack<Integer> arrayStack = new ArrayStack<>(10);

        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);
        arrayStack.push(5);

        System.out.println(arrayStack.size());
        System.out.println(arrayStack.peek());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.peek());
        System.out.println(arrayStack.size());

        System.out.println(arrayStack);

    }

}
