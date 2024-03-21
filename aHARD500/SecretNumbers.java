package aHARD500;

public class SecretNumbers {

    String s;
    int k;
    int mod = 1000000007;
    long[][][] dp = new long[101][1001][2];
    long[][][] dp_sum = new long[101][1001][2];
    long[] pow = new long[101];

    long rec(int ind, int sum, int eq) {

        if (ind == s.length()) {
            if (sum == 0) {
                // System.out.println("found");
                return dp[ind][sum][eq] = 1;
            } else
                return dp[ind][sum][eq] = 0;
        } else {
            if (dp[ind][sum][eq] != -1)
                return dp[ind][sum][eq];

            // dp_sum[ind][sum][eq] = 0;
            long ret = 0;

            if (eq == 1) {
                int nx = s.charAt(ind) - '0';
                if (sum - nx >= 0) {
                    ret += rec(ind + 1, sum - nx, 1);
                    ret %= mod;
                    dp_sum[ind][sum][eq] += dp_sum[ind + 1][sum - nx][1];
                    dp_sum[ind][sum][eq] += (dp[ind + 1][sum - nx][1] * (nx * pow[ind]));
                    dp_sum[ind][sum][eq] %= mod;
                }
                for (int i = nx - 1; i >= 0; i--)
                    if (sum - i >= 0) {
                        ret += rec(ind + 1, sum - i, 0);
                        ret %= mod;
                        dp_sum[ind][sum][eq] += dp_sum[ind + 1][sum - i][0];
                        dp_sum[ind][sum][eq] += dp[ind + 1][sum - i][0] * (i * pow[ind]);
                        dp_sum[ind][sum][eq] %= mod;
                    }
            } else {
                for (int i = 0; i <= 9; i++)
                    if (sum - i >= 0) {
                        ret += rec(ind + 1, sum - i, 0);
                        ret %= mod;
                        dp_sum[ind][sum][eq] += dp_sum[ind + 1][sum - i][0];
                        dp_sum[ind][sum][eq] += dp[ind + 1][sum - i][0] * (i * pow[ind]);
                        dp_sum[ind][sum][eq] %= mod;
                    }
            }
            // System.out.println(ind + " " + sum + " " + eq + " " + dp_sum[ind][sum][eq]);
            return dp[ind][sum][eq] = ret;
        }

    }

    public int findSum(String N, int K) {

        s = N;
        k = K;

        pow[N.length() - 1] = 1;
        for (int i = N.length() - 2; i >= 0; i--) {
            pow[i] = (pow[i + 1] * 10);
            pow[i] %= mod;
        }

        // System.out.println(pow[0]);

        for (int i = 0; i < 101; i++)
            for (int j = 0; j < 1001; j++)
                for (int l = 0; l < 2; l++) {
                    dp[i][j][l] = -1;
                    dp_sum[i][j][l] = 0;
                }
        rec(0, K, 1);

        return (int) (dp_sum[0][K][1] % mod);
    }

    public static void main(String[] args) {
        SecretNumbers s = new SecretNumbers();
        System.out.println(s.findSum("100", 3));
        System.out.println(s.findSum("100", 1));
        // System.out.println(s.findSum("1243332", 2));
        System.out.println(s.findSum("104", 2));
        System.out.println(s.findSum("40178114685284207017", 60));
    }
}
