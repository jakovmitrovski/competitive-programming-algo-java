package veryhard400;

public class MegaTetris {
    public int getCount(int[] height, int[] width, int[] positions) {

        int[] dp = new int[100000000];
        int res = 0;
        for (int i = 0; i < positions.length; i++) {
            int mx = 0;
            for (int j = positions[i]; j < positions[i] + width[i]; j++)
                mx = Math.max(mx, dp[j]);
            for (int j = positions[i]; j < positions[i] + width[i]; j++) {
                res += mx - dp[j];
                res %= 1000007;
                dp[j] = mx + height[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MegaTetris m = new MegaTetris();
        System.out.println(m.getCount(new int[] { 3, 4, 5, 4, 1, 2 }, new int[] { 3, 5, 1, 1, 1, 2 },
                new int[] { 2, 3, 3, 6, 9, 6 }));
    }
}
