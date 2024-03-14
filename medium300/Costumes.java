package medium300;

public class Costumes {

    int[][] dp;
    int[][] dist;
    int n;

    int res(int ind, int mask) {

        if (mask == ((1 << n) - 1))
            return dist[ind][0];

        if (dp[ind][mask] != -1)
            return dp[ind][mask];

        int ret = 1 << 20;

        for (int i = 0; i < n; i++)
            if ((mask & (1 << i)) == 0)
                ret = Math.min(ret, dist[ind][i] + res(i, mask | (1 << i)));
        return dp[ind][mask] = ret;
    }

    public int minimumPath(int[] x, int[] y) {
        n = x.length;
        dist = new int[20][20];
        dp = new int[18][1 << 18];
        for (int i = 0; i < x.length; i++)
            for (int j = i + 1; j < y.length; j++) {
                int cost = Math.abs(x[i] - x[j]) + Math.abs(y[i] - y[j]);
                dist[i][j] = cost;
                dist[j][i] = cost;
            }

        for (int i = 0; i < 18; i++)
            for (int j = 0; j < 1 << 18; j++)
                dp[i][j] = -1;

        return res(0, 1);
    }
}
