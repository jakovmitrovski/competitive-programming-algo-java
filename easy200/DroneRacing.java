package easy200;

import java.util.*;

public class DroneRacing {
    public int winner(int N, String races) {

        List<Integer> set = new ArrayList<>();

        for (int i = 1; i <= N; i++)
            set.add(i);

        int left = 0, right = N - 1;
        int t = 0;
        while (N >= 1) {

            // for (Integer x : set) System.out.print(x + " ");
            // System.out.println();
            List<Integer> toRemove = new ArrayList<>();

            for (int i = 0; i < N / 2; i++) {
                if (races.charAt(t) == '1') {
                    // System.out.println("removing " + " " +set.get(right));
                    toRemove.add(set.get(right));

                } else {
                    // System.out.println("removing " + " " + set.get(left));
                    toRemove.add(set.get(left));
                }
                left++;
                right--;
                t++;
            }

            // System.out.println("removing : ");
            // for (Integer x : toRemove){
            // System.out.print(x + " ");
            // }
            // System.out.println();
            List<Integer> set2 = new ArrayList<>();
            for (int i = 0; i < set.size(); i++) {
                boolean flag = false;
                for (int j = 0; j < toRemove.size(); j++)
                    if (set.get(i).equals(toRemove.get(j))) {
                        flag = true;
                    }
                if (!flag)
                    set2.add(set.get(i));
            }

            set = new ArrayList<>(set2);

            set.sort(Comparator.comparingInt(x -> x));
            N /= 2;
            left = 0;
            right = N - 1;
        }
        return set.get(0);
    }

    public static void main(String[] args) {
        DroneRacing d = new DroneRacing();
        System.out.println(d.winner(8, "2212211"));
        System.out.println(d.winner(16, "212122221212222"));
    }
}
