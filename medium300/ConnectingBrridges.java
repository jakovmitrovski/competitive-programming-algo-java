package medium300;

public class ConnectingBridges {
    public int bridges(int[] cities1, int[] cities2) {

        int[][] dp = new int[101][101];

        for (int i = 0; i <= cities1.length; i++) {
            for (int j = 0; j <= cities2.length; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = 0;
                else if (cities1[i - 1] == cities2[j - 1])
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[cities1.length][cities2.length];
    }

    // public static void main(String[] args) {
    // ConnectingBridges c = new ConnectingBridges();
    // System.out.println(c.bridges(new int[]{1,2,3,4}, new int[]{2,3,4,1}));
    // }
}
