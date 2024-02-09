package aHARD500;

import java.util.ArrayList;
import java.util.List;

public class MaxSum {
    public int max(int cols, int rows, int k, int m) {

        boolean[] sito = new boolean[500002];

        sito[0] = true;
        sito[1] = true;

        for (int i = 2; i <= 500000; i++)
            if (!sito[i]) {
                for (int j = i + i; j <= 500000; j += i)
                    sito[j] = true;
            }

        List<Integer> primes = new ArrayList<>();

        for (int i = 2; i <= 500000; i++)
            if (!sito[i])
                primes.add(i);

        int prime = 0;
        int[][] mat = new int[rows + 2][cols + 2];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {

                long mul = (long) primes.get(prime) * (long) k;
                mat[i][j] = (int) (((mul) % m) - m / 2);
                prime++;
            }

        long[][] dp = new long[rows + 2][cols + 2];
        for (int i = 0; i <= rows; i++)
            dp[i][0] = 0;
        for (int j = 0; j <= cols; j++)
            dp[0][j] = 0;

        for (int i = 1; i <= rows; i++)
            for (int j = 1; j <= cols; j++)
                dp[i][j] = dp[i - 1][j] + (dp[i][j - 1] - dp[i - 1][j - 1]) + mat[i - 1][j - 1];

        int res = -1 << 30;

        for (int i = 1; i <= rows; i++)
            for (int j = 1; j <= cols; j++)
                for (int l = i; l <= rows; l++)
                    for (int t = j; t <= cols; t++) {
                        if (i == l && j == t)
                            res = Math.max(res, mat[i - 1][j - 1]);
                        else
                            res = (int) Math.max(res, ((dp[l][t] - dp[i - 1][t]) - dp[l][j - 1]) + dp[i - 1][j - 1]);
                    }

        return res;
    }

}
