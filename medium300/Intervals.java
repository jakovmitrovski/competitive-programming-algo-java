package medium300;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Intervals {

    class Interval {
        long left, right;

        public Interval(long left, long right) {
            this.left = left;
            this.right = right;
        }

        long getLeft() {
            return left;
        }
    }

    public String smallest(String intervals) {

        String[] ivals = intervals.split(",");
        List<Interval> list = new ArrayList<>();
        for (String x : ivals) {
            String[] in = x.split("-");
            list.add(new Interval(Long.parseLong(in[0]), Long.parseLong(in[1])));
        }

        while (true) {
            boolean change = false;
            // System.out.println("New iteration");
            for (int i = 0; i < list.size(); i++) {
                Interval el = list.get(i);

                for (int j = 0; j < list.size(); j++) {
                    if (i == j)
                        continue;
                    else {
                        Interval el2 = list.get(j);

                        if (el.right < el2.left - 1 || el.left > el2.right + 1) {
                            // System.out.println(el.left + "-" + el.right + " is completely outside of " +
                            // el2.left+"-"+el2.right);
                            continue;
                        } else if (el.left <= el2.left && el.right >= el2.right) {
                            // System.out.println(el.left + "-" + el.right + " contains " +
                            // el2.left+"-"+el2.right);
                            list.remove(el2);
                            change = true;
                            break;
                        } else {
                            // System.out.println(el.left + "-" + el.right + " concatenated with " +
                            // el2.left+"-"+el2.right);
                            list.remove(el);
                            list.remove(el2);
                            list.add(new Interval(Math.min(el.left, el2.left), Math.max(el.right, el2.right)));
                            change = true;
                            break;
                        }
                    }
                }
            }

            if (!change)
                break;
        }

        list.sort(Comparator.comparing(Interval::getLeft));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            Interval el = list.get(i);
            sb.append(el.left).append("-").append(el.right);
            if (i != list.size() - 1)
                sb.append(",");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Intervals i = new Intervals();
        System.out.println(i.smallest("2-4,5-5,6-8"));
        System.out
                .println(i.smallest("327802850-593687580,288675425-410271975,386737447-388754878,96575567-222789979"));
    }
}
