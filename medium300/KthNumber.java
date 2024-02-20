package medium300;

public class KthNumber {

    public String kthNumber(int[] primes, int k) {

        long[] dp = new long[k + 1];
        dp[1] = 1;

        for (int x = 2; x <= k; x++) {
            long ans = Long.MAX_VALUE;
            for (int y = 1; y < x; y++) {
                for (int i = 0; i < primes.length; i++) {
                    long nval = dp[y] * primes[i];
                    if (nval > dp[x - 1])
                        ans = Math.min(nval, ans);
                }
                dp[x] = ans;
            }
        }

        return String.valueOf(dp[k]);
    }

    public static void main(String[] args) {
        KthNumber k = new KthNumber();
        System.out.println(k.kthNumber(new int[] { 2, 3 }, 1000));
    }
}
