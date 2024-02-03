package easy200;

import java.util.HashMap;
import java.util.Map;

public class TrainRoute {
    public String findActualSchedule(String plannedSchedule, String updates) {

        String[] sched = plannedSchedule.split(" ");

        Map<Character, String> map = new HashMap<>();

        for (int i = 0; i < sched.length; i += 2) {
            String[] curr = { sched[i], sched[i + 1] };
            if (map.containsKey(curr[0].charAt(0))) {
                map.remove(curr[0].charAt(0));
            }
            map.put(curr[0].charAt(0), curr[1]);
        }

        if (updates.length() == 0)
            return plannedSchedule;

        sched = updates.split(" ");

        for (int i = 0; i < sched.length; i += 2) {
            String[] curr = { sched[i], sched[i + 1] };
            if (map.containsKey(curr[0].charAt(0))) {
                map.remove(curr[0].charAt(0));
            }
            map.put(curr[0].charAt(0), curr[1]);
        }

        sched = plannedSchedule.split(" ");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sched.length; i += 2) {
            if (i == 0)
                sb.append(sched[i].charAt(0)).append(" ").append(map.get(sched[i].charAt(0)));
            else
                sb.append(" ").append(sched[i].charAt(0)).append(" ").append(map.get(sched[i].charAt(0)));
        }

        return sb.toString();
    }
}