package medium300;

import java.util.LinkedList;
import java.util.Queue;

public class CandyCrush {

    class Node {
        int pos, time;

        public Node(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }
    }

    boolean check(boolean[] a) {
        for (int i = 0; i < a.length; i++)
            if (!a[i])
                return false;
        return true;
    }

    public int howLong(int[] times, int position) {

        Queue<Node> q = new LinkedList<>();

        Node start = new Node(position, 0);
        q.add(start);

        boolean[] crushed = new boolean[times.length];
        boolean[] atm = new boolean[times.length];

        int res = 0;
        atm[position] = true;
        for (int i = 1; i <= 1000; i++) {

            // for (int j=0; j<crushed.length; j++)
            // System.out.print(crushed[j] + " ");
            // System.out.println();
            if (check(crushed))
                return res;

            boolean[] natm = new boolean[times.length];
            for (int j = 0; j < times.length; j++) {
                if (atm[j] && !crushed[j]) {
                    if (j > 0 && !crushed[j - 1])
                        natm[j - 1] = true;
                    if (j < times.length - 1 && !crushed[j + 1])
                        natm[j + 1] = true;
                    natm[j] = true;
                }
            }
            for (int j = 0; j < times.length; j++)
                if (natm[j]) {
                    res = i;
                    break;
                }
            for (int j = 0; j < crushed.length; j++)
                if (times[j] <= i)
                    crushed[j] = true;

            for (int j = 0; j < times.length; j++)
                atm[j] = natm[j];
        }

        return res;
    }

    public static void main(String[] args) {
        CandyCrush c = new CandyCrush();
        System.out.println(c.howLong(new int[] { 1, 2, 3, 4 }, 0));
        System.out.println(c.howLong(new int[] { 1, 2, 10, 4 }, 0));
    }
}
