package _12B;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class F_时间显示 {

    public static void main(String[] args) {
        long mms = 1618708103123L;

        System.out.println(timeShow(mms));
    }

    public static String timeShow(long mms) {
        // 将时区调整为格林时间时区
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        Date date = new Date(mms);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(date);
    }

}
