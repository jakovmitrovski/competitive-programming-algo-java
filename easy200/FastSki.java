package easy200;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FastSki {
    public String getLast(String[] times) {
        long max = -1 << 20;

        for (int i = 0; i < times.length; i++) {
            String[] time = times[i].split("-");
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            Date reference = null, date1 = null, date2 = null;
            try {
                reference = dateFormat.parse("00:00:00");
                date1 = dateFormat.parse(time[0]);
                date2 = dateFormat.parse(time[1]);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            long secondsLeft = (date1.getTime() - reference.getTime()) / 1000L;
            long secondsRight = (date2.getTime() - reference.getTime()) / 1000L;
            max = Math.max(max, secondsRight - secondsLeft);
        }

        int hours = 0, minutes = 0, seconds = 0;

        while (max >= 3600) {
            hours++;
            max -= 3600;
        }
        while (max >= 60) {
            minutes++;
            max -= 60;
        }
        while (max > 0) {
            seconds++;
            max--;
        }

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public static void main(String[] args) {
        FastSki f = new FastSki();
        System.out.println(f.getLast(new String[] { "20:03:05-22:06:31", "04:20:05-15:51:14", "08:39:33-10:31:49" }));
        System.out.println(f.getLast(new String[] { "13:09:14-13:10:27", "12:47:19-20:40:58", "08:47:40-14:25:34",
                "13:44:31-14:17:07", "18:22:38-18:27:29" }));
        System.out.println(f.getLast(new String[] { "14:13:08-23:40:27", "10:35:30-17:01:21", "12:30:02-23:27:19",
                "01:41:40-14:08:05", "11:03:53-16:56:54", "03:07:59-08:08:06", "16:48:06-23:41:52", "10:56:14-20:25:58",
                "10:25:54-17:43:48" }));
    }
}
