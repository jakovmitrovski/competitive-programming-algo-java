package medium300;

import java.util.LinkedList;
import java.util.Queue;

public class WaterTanks {

    class State {
        int f, s;

        public State(int f, int s) {
            this.f = f;
            this.s = s;
        }
    }

    public int minSteps(int x, int y, int m) {

        Queue<State> q = new LinkedList<>();

        q.add(new State(x, y));

        int[][] dp = new int[1001][1001];

        for (int i = 0; i < 1001; i++)
            for (int j = 0; j < 1001; j++)
                dp[i][j] = 1 << 25;

        dp[x][y] = 0;

        while (!q.isEmpty()) {
            State curr = q.remove();

            if (curr.f == m || curr.s == m)
                return dp[curr.f][curr.s];

            int nf = curr.f + curr.s;
            if (nf <= m && dp[nf][curr.s] > dp[curr.f][curr.s] + 1) {
                q.add(new State(nf, curr.s));
                dp[nf][curr.s] = dp[curr.f][curr.s] + 1;
            }
            int ns = curr.f + curr.s;
            if (ns <= m && dp[curr.f][ns] > dp[curr.f][curr.s] + 1) {
                q.add(new State(curr.f, ns));
                dp[curr.f][ns] = dp[curr.f][curr.s] + 1;
            }
        }

        return -1;
    }
}
