package _03栈._03中缀表达式;

import _03栈._02链表实现栈.LinkedStack;
import org.junit.Test;

public class Demo {

    /**
     * 将数字和符号区分开
     */
    @Test
    public void splitNumAndOpera() {

        // 数学表达式
        String expression = "7*2*2-5+1-2+3-4";

        // 创建两个栈，数字栈和符号栈
        LinkedStack<Integer> numStack = new LinkedStack<>();
        LinkedStack<String> operaStack = new LinkedStack<>();

        // 用来记录数字的字符缓冲，为了保证多位数字的安全性
        StringBuilder num = new StringBuilder();
        // 标记当前字符是否为数字
        boolean isNum = false;
        for (int i = 0; i < expression.length(); i++) {
            int tempIndex = i;

            char ch = expression.charAt(i);

            // 若字符是数字则进入循环
            while (ch >= '0' && ch <= '9') {
                // 修改标记
                isNum = true;
                // 将字符追加进字符串缓冲
                num.append(ch);
                // 增加临时索引
                tempIndex++;
                // 如果临时索引等于字符串长度了，则跳出while
                if (tempIndex == expression.length()) {
                    break;
                }
                // 更新ch至下一位字符
                ch = expression.charAt(tempIndex);
            }

            // 如果不是数字，则直接push进符号栈
            if (!isNum) {
                operaStack.push(String.valueOf(ch));
            } else
            // 如果是数字
            {
                // 将字符串缓冲转化为String并翻译成整型，push进数字栈
                numStack.push(Integer.parseInt(num.toString()));
                // 修改标记
                isNum = false;
                // 将字符串缓冲清空
                num = new StringBuilder();
                // 更新索引（因为在while里为了判断下一位是否是数字，多加了1，所以在这里要减1）
                i = tempIndex - 1;
            }

        }

        System.out.println(numStack);
        System.out.println(operaStack);

    }

    @Test
    public void Calculator() {

        // 数学表达式
        String expression = "7*2-5*2+1-2+3-4";

        // 创建两个栈，数字栈和符号栈
        LinkedStack<Integer> numStack = new LinkedStack<>();
        LinkedStack<String> operaStack = new LinkedStack<>();

        // 用来记录数字的字符缓冲
        StringBuilder num = new StringBuilder();
        // 标记当前字符是否为数字
        boolean isNum = false;
        for (int i = 0; i < expression.length(); i++) {
            int tempIndex = i;

            char ch = expression.charAt(i);

            // 若字符是数字则进入循环
            while (ch >= '0' && ch <= '9') {
                // 修改标记
                isNum = true;
                // 将字符追加进字符串缓冲
                num.append(ch);
                // 增加临时索引
                tempIndex++;
                // 如果临时索引等于字符串长度了，则跳出while
                if (tempIndex == expression.length()) {
                    break;
                }
                // 更新ch至下一位字符
                ch = expression.charAt(tempIndex);
            }

            // 如果不是数字，则直接push进符号栈
            if (!isNum) {

                // 如果 符号栈不为空 且 当前符号的优先级小于等于栈顶符号
                while (!operaStack.empty() && higherOrSame(String.valueOf(ch), operaStack.peek())) {
                    Integer pop1 = numStack.pop();
                    Integer pop2 = numStack.pop();
                    String opera = operaStack.pop();
                    // pop1 在 pop2 上面，所以在计算时需要 pop2 在前面
                    int result = calculator(pop2, pop1, opera);
                    numStack.push(result);
                }
                operaStack.push(String.valueOf(ch));

            } else
            // 如果是数字
            {
                // 将字符串缓冲转化为String并翻译成整型，push进数字栈
                numStack.push(Integer.parseInt(num.toString()));
                // 修改标记
                isNum = false;
                // 将字符串缓冲清空
                num = new StringBuilder();
                // 更新索引（因为在while里为了判断下一位是否是数字，多加了1，所以在这里要减1）
                i = tempIndex - 1;
            }

        }

        System.out.println(numStack);
        System.out.println(operaStack);


        Integer pop1 = numStack.pop();
        Integer pop2 = numStack.pop();
        String opera = operaStack.pop();
        System.out.println(calculator(pop2, pop1, opera));

    }

    /**
     *
     * 判断 opera2 的优先级是否小于或等于 opera1 的优先级
     *
     * @param opera1
     * @param opera2
     * @return 是true，否false
     */
    public static boolean higherOrSame(String opera1, String opera2) {
        if (opera1.equals("*") || opera1.equals("/")) {
            return !opera2.equals("+") && !opera2.equals("-");
        }
        return true;
    }

    public static int calculator(int num1, int num2, String opera) {
        int result = 0;
        switch (opera) {
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
        }
        return result;
    }


}
