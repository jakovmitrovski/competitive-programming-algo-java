package aHARD500;

import java.util.Arrays;

public class BalumBalum {

    int n;
    int[] niza;
    int[][] dp = new int[17][1 << 16];

    int res(int ind, int mask) {

        if (dp[ind][mask] != -1)
            return dp[ind][mask];
        if (mask == (1 << n) - 1) {
            return niza[ind];
        }
        int ret = -1 << 20;

        for (int i = 0; i < n; i++) {
            int diff = Math.abs(niza[ind] - niza[i]) + 1;
            // System.out.println(ind + " " + i + " " + Integer.toBinaryString(mask) + " " +
            // (mask & (1<<i)));
            if ((mask & (1 << i)) == 0 && (diff <= 6)) {
                ret = Math.max(ret, diff + res(i, (mask | (1 << i))));
            }
        }

        return dp[ind][mask] = ret;
    }

    public int mixItUp(int[] heights) {

        for (int i = 0; i < 17; i++)
            Arrays.fill(dp[i], -1);
        niza = new int[heights.length];
        for (int i = 0; i < heights.length; i++)
            niza[i] = heights[i];
        n = heights.length;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            ret = Math.max(ret, 1 + niza[i] + res(i, (1 << i)));
        }
        return ret;
    }

    public static void main(String[] args) {
        BalumBalum b = new BalumBalum();
        System.out.println(b.mixItUp(new int[] { 6, 5, 1, 6, 6, 2, 4 }));
        System.out.println(b.mixItUp(new int[] { 15, 15, 6, 10, 9 }));
        System.out.println(b.mixItUp(new int[] { 16, 25, 6, 42, 5, 33, 18, 8, 23, 36, 24, 23, 11, 30, 40 }));
    }
}
