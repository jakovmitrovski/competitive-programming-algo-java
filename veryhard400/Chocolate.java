package veryhard400;

import java.util.Scanner;

public class Chocolate {
    static long[][][] dp = new long[102][102][12];

    static long rekruzija(int r, int c, int b, int n) {
        if (dp[r][c][n] != -1)
            return dp[r][c][n];

        if (n == 0 && r * c >= b)
            return 1;
        else if (n == 0 && (r * c) < b)
            return 0;
        else if (n > 0 && (r == 0 || c == 0))
            return 0;

        long x = 0, y = 0;

        for (int i = 1; i < c; i++)
            if (i * r >= b) {
                x += rekruzija(r, c - i, b, n - 1);
                x %= 1000000007;
            }

        for (int i = 1; i < r; i++)
            if (i * c >= b) {
                y += rekruzija(r - i, c, b, n - 1);
                y %= 1000000007;
            }

        return dp[r][c][n] = (2 * (x + y)) % 1000000007;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t > 0) {
            int r = sc.nextInt(), c = sc.nextInt(), b = sc.nextInt(), n = sc.nextInt();

            for (int i = 0; i <= 100; i++)
                for (int j = 0; j <= 100; j++)
                    for (int k = 0; k <= 11; k++)
                        dp[i][j][k] = -1;

            System.out.println(rekruzija(r, c, b, n - 1));

            t--;
        }
    }
}