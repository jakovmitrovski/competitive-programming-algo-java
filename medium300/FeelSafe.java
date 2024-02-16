package medium300;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FeelSafe {

    int[][] dp = new int[1001][1001];

    class Canal {
        int x1, y1, x2, y2;

        public Canal(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        int getArea() {
            return (y2 - y1) * (x2 - x1);
        }
    }

    boolean inside(Canal c1, Canal c2) {
        return c2.x1 > c1.x1 && c2.y1 > c1.y1 && c2.x2 < c1.x2 && c2.y2 < c1.y2;
    }

    public int maximumCanalCount(int[] x1, int[] y1, int[] x2, int[] y2) {

        Canal[] c = new Canal[x1.length];

        for (int i = 0; i < x1.length; i++)
            c[i] = new Canal(Math.min(x1[i], x2[i]),
                    Math.min(y1[i], y2[i]),
                    Math.max(x1[i], x2[i]),
                    Math.max(y1[i], y2[i]));

        List<Canal> list = Arrays.stream(c).sorted(Comparator.comparing(Canal::getArea)).collect(Collectors.toList());

        int[] dp = new int[51];
        int rez = 0;
        for (int i = 0; i < x1.length; i++)
            for (int j = 0; j < i; j++) {
                if (inside(list.get(i), list.get(j))) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    rez = Math.max(rez, dp[i]);
                }
            }

        return rez + 1;
    }

    public static void main(String[] args) {
        FeelSafe f = new FeelSafe();
        System.out.println(f.maximumCanalCount(new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 },
                new int[] { 20, 19, 18, 17, 16, 15, 14, 13, 12, 11 },
                new int[] { 20, 19, 18, 17, 16, 15, 14, 13, 12, 11 }, new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }));
    }
}
