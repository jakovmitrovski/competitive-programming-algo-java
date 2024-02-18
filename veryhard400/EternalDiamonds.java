package veryhard400;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EternalDiamonds {

    class Par {
        int x, y;

        public Par(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Par par = (Par) o;
            return x == par.x && y == par.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public int getMaxScore(int size, String[] diamonds, String[] walls) {

        Map<Par, Par> map = new HashMap<>();
        int[] cnt = new int[10000];
        int[][] mat = new int[size + 2][size + 2];

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {

                Par p = new Par(i + j, cnt[i + j]);
                Par p1 = new Par(i, j);
                map.put(p, p1);
                cnt[i + j]++;
            }

        for (int i = 0; i < diamonds.length; i++) {
            String[] pos = diamonds[i].split(";");
            Par x = map.get(new Par(Integer.parseInt(pos[0]), Integer.parseInt(pos[1])));
            mat[x.x][x.y] = 1;
        }

        for (int i = 0; i < walls.length; i++) {
            String[] wall = walls[i].split(";");
            int x = Integer.parseInt(wall[0]), y = Integer.parseInt(wall[1]);
            for (int j = y; j <= Integer.parseInt(wall[2]); j++) {
                Par curr = map.get(new Par(x, j));
                mat[curr.x][curr.y] = -1 << 25;
            }
        }

        int[][] dp = new int[size + 2][size + 2];
        for (int i = 1; i < size; i++)
            dp[0][i] = dp[0][i - 1] + mat[0][i];
        for (int i = 1; i < size; i++)
            dp[i][0] = dp[i - 1][0] + mat[i][0];

        for (int i = 1; i < size; i++)
            for (int j = 1; j < size; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + mat[i][j];
            }

        if (dp[size - 1][size - 1] < 0)
            return -1;

        return dp[size - 1][size - 1];
    }

    public static void main(String[] args) {
        EternalDiamonds e = new EternalDiamonds();
        System.out.println(
                e.getMaxScore(11, new String[] { "14;3", "8;1", "11;9", "13;5", "6;5", "13;7", "13;1", "5;4", "10;3" },
                        new String[] { "11;6;6", "2;1;1", "6;1;2" }));
        System.out.println(e.getMaxScore(3, new String[] { "2;0" }, new String[] { "2;1;1" }));
        System.out.println(e.getMaxScore(5, new String[] { "2;0", "2;1", "3;1" }, new String[] { "4;0;3", "7;1;1" }));
        System.out.println(e.getMaxScore(3, new String[] { "2;0", "2;1" }, new String[] { "2;2;2" }));
        System.out.println(e.getMaxScore(3, new String[] { "2;0", "2;1", "3;1" }, new String[] { "2;2;2" }));
    }
}
