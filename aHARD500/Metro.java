package aHARD500;

import java.util.HashSet;
import java.util.Set;

public class Metro {
    Set<Integer> sums = new HashSet<>();
    int[][] dist = new int[101][101];

    void permutations(int ind, int[] niza, int n, int mask) {
        if (ind == n) {
            // permutation done;
            // for (int i=0; i<n; i++) System.out.print(niza[i] + " ");
            int sum = 0;
            for (int i = 1; i < n; i++) {
                sum += dist[niza[i - 1]][niza[i]];
                if (niza[i] == n - 1)
                    break;
            }
            if (sum <= 1100)
                sums.add(sum);
            // System.out.println();
        } else {
            for (int i = 1; i < n; i++)
                if ((mask & (1 << i)) == 0) {
                    niza[ind] = i;
                    permutations(ind + 1, niza, n, mask | (1 << i));
                    niza[ind] = -1;
                }
        }
    }

    public String getLongestRoute(String[] connections, int maxDistance) {
        int[] niza = new int[connections.length];
        niza[0] = 0;

        for (int i = 0; i < 101; i++)
            for (int j = 0; j < 101; j++)
                dist[i][j] = 0;

        for (int i = 0; i < connections.length; i++) {
            String[] in = connections[i].split(" ");
            for (int j = 0; j < in.length; j++) {
                if (in[j].equals("none"))
                    continue;
                String[] cr = in[j].split(":");
                int to = Integer.parseInt(cr[0]), cost = Integer.parseInt(cr[1]);
                dist[i][to] = cost;
                // dist[to][i] = cost;
            }
        }

        for (int i = 0; i < 101; i++)
            for (int j = 0; j < 101; j++)
                if (dist[i][j] == 0)
                    dist[i][j] = 1 << 25;

        for (int i = 0; i < 101; i++)
            for (int j = 0; j < 101; j++)
                if (dist[i][j] != 1 << 25 && dist[j][i] == 1 << 25)
                    dist[j][i] = dist[i][j];

        int[][] dp = new int[maxDistance + 1][2];
        int[][] cnt = new int[maxDistance + 1][2];

        permutations(1, niza, connections.length, 1);

        // for (Integer sum : sums ) System.out.println(sum);

        dp[0][0] = 1;
        int ret = 0;
        for (int i = 1; i <= maxDistance; i++) {
            for (Integer sum : sums) {
                if (i >= sum && dp[i - sum][0] == 1) {
                    dp[i][1] = 1;
                    cnt[i][1] = Math.max(cnt[i - sum][0], cnt[i][1]);
                }
            }
            for (Integer sum : sums) {
                if (i >= sum && dp[i - sum][1] == 1) {
                    dp[i][0] = 1;
                    cnt[i][0] = Math.max(cnt[i][0], cnt[i - sum][1] + 1);
                    ret = i;
                }
            }
        }

        return cnt[ret][0] + ":" + ret;
    }

    public static void main(String[] args) {
        Metro m = new Metro();
        System.out.println(m.getLongestRoute(new String[] { "2:23 4:50", "4:35", "1:41 3:58 4:54", "1:12", "3:55" },
                292));
        System.out.println(m.getLongestRoute(new String[] { "1:10 2:15", "3:10", "3:15", "2:90" },
                145));
        System.out.println(m.getLongestRoute(new String[] { "1:10 2:15", "3:10", "3:15", "none" }, 145));
    }
}
