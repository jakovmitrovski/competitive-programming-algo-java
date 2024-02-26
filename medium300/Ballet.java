package medium300;

public class Ballet {

    int mod = 1000007;

    int[][] dp = new int[201][201];

    int res(int n, int k) {

        if (dp[n][k] != 0)
            return dp[n][k];

        if (k == n || k == 0)
            return dp[n][k] = 1;
        if (k == 1 || k == n - 1)
            return dp[n][k] = n;

        return dp[n][k] = (res(n - 1, k - 1) + res(n - 1, k)) % mod;
    }

    public int count(int m, int n, int t, int k, int l) {

        if (k > m || l > n)
            return 0;

        long ret = 0;

        for (int i = k; i <= t; i++)
            for (int j = l; j <= t; j++) {
                if (i + j == t && i <= m && j <= n) {

                    ret += (long) res(m, i) * (long) res(n, j);
                    ret %= mod;
                }
            }

        return (int) ret;
    }

    public static void main(String[] args) {
        Ballet b = new Ballet();
        System.out.println(b.count(9, 17, 13, 3, 5));
        System.out.println(b.count(35, 16, 32, 6, 3));
    }
}
