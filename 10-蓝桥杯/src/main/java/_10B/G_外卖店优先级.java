package _10B;

import java.util.*;
import java.util.stream.Collectors;

public class G_外卖店优先级 {

    static int N, M, T;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        // N 家店、M条订单、T时刻
        N = scanner.nextInt();
        M = scanner.nextInt();
        T = scanner.nextInt();

        Map<Integer, List<Integer>>order = new HashMap<>();
        // 接收每条订单
        for (int i = 0; i < M; i++) {
            int ts = scanner.nextInt();
            int id = scanner.nextInt();

            List<Integer> list = order.get(id);
            if (null == list) {
                list = new ArrayList<>();
            }
            list.add(ts);
            order.put(id, list);
        }

        int count = 0;
        for (Integer id : order.keySet()) {
            count += priority(order.get(id)) ? 1 : 0;
        }

        System.out.println(count);

    }

    public static boolean priority(List<Integer> orderList) {
        if (null == orderList || orderList.size() == 0) {
            return false;
        }
        orderList = orderList.stream().sorted().collect(Collectors.toList());

        int priority = 0;
        boolean isPriority = false;
        for (int i = 0; i < T; i++) {
            if (!orderList.contains(i + 1)) {
                priority -= priority == 0 ? 0 : 1;
            }

            while (orderList.contains(i + 1)) {
                priority += 2;
                orderList.remove(Integer.valueOf(i + 1));
            }

            if (!isPriority) {
                if (priority > 5) {
                    isPriority = true;
                }
            }
            if (isPriority) {
                if (priority <= 3) {
                    isPriority = false;
                }
            }
        }

        return isPriority;
    }

}
