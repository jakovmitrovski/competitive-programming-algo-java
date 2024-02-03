package aHARD500;

import java.util.HashSet;
import java.util.Set;

public class FibonacciBits {

    int [][][]dp = new int[32][32][2];

    int dfs(String s, int slots, int ones, int idx, int smaller) {
        if (idx == s.length())
            return fib.contains(ones) ? 1 : 0;
        if (dp[idx][ones][smaller] != -1)
            return dp[idx][ones][smaller];
        int res = 0;
        
        if (smaller == 1) {
            res += dfs(s, slots - 1, ones, idx + 1, 1);
            res += dfs(s, slots - 1, ones + 1, idx + 1, 1);
        } else {
            if (s.charAt(idx) == '0')
                res += dfs(s, slots - 1, ones, idx + 1, 0);
            else {
                res += dfs(s, slots - 1, ones, idx + 1, 1);
                res += dfs(s, slots - 1, ones + 1, idx + 1, 0);
            }
        }


        return dp[idx][ones][smaller] = res;
    }

    int res(int n) {
        String s = Integer.toBinaryString(n);
        int slots = s.length();
        
        for (int i = 0; i < 32; i++)
            for (int j = 0; j < 32; j++)
                for (int k = 0; k < 2; k++)
                    dp[i][j][k] = -1;

        return dfs(s, slots, 0, 0, 0);

    }

    Set<Integer> fib = new HashSet<>();

    public int modernFibonacci(int S, int E) {

        int a = 1, b = 2;
        fib.add(a);
        fib.add(b);
        while (fib.size() < 32) {
            int c = a + b;
            fib.add(c);
            a = b;
            b = c;
        }

        return res(E) - res(S - 1);
    }

    public static void main(String[] args) {
        FibonacciBits f = new FibonacciBits();
        System.out.println(f.modernFibonacci(529, 149104));
    }
}
