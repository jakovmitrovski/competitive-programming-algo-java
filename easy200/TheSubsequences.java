package easy200;

public class TheSubsequences {

    boolean subs(String a, String b) {
        int[][] dp = new int[8][8];

        for (int i = 1; i <= a.length(); i++)
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }

        if (dp[a.length()][b.length()] == b.length())
            return true;

        return false;
    }

    public int count(int A, int B, int C) {
        int res = 0;
        for (int i = A; i <= B; i++) {
            if (subs(String.valueOf(i), String.valueOf(C)))
                res++;

        }

        return res;
    }

    // public static void main(String[] args) {
    // TheSubsequences s = new TheSubsequences();
    // System.out.println(s.count(1, 1000000, 1));
    // }
}
