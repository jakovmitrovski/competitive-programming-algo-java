package veryhard400;

import java.util.*;

public class MultiSubstract {

    public int getMinOperations(int M, int S, int T) {

        int []dp = new int[1000001];
        Arrays.fill(dp, -1);
        Queue<Integer> q = new LinkedList<>();
        dp[1] = 0;
        q.add(1);

        while (!q.isEmpty()){
            Integer curr = q.remove();

            if (curr == T) return dp[T];

            if (curr * M <= 1000000 && (dp[curr*M] == -1 || dp[curr*M] > dp[curr]+1)){
                dp[curr*M] = dp[curr] + 1;
                q.add(curr*M);
            }if (curr - S > 0&& (dp[curr-S] == -1 || dp[curr-S] > dp[curr]+1)){
                dp[curr-S] = dp[curr]+1;
                q.add(curr-S);
            }
        }

        return -1;
    }

   public static void main(String[] args) {
       MultiSubstract m = new MultiSubstract();
       System.out.println(m.getMinOperations(5, 7, 20));
   }
}
