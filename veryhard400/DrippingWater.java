package veryhard400;

import java.util.*;

public class DrippingWater {

    int[][] mat = new int[5502][5502];
    boolean[] visited = new boolean[5502];
    int[] parent = new int[5502];
    boolean[] used = new boolean[5502];

    boolean bfs(int s, int t) {
        Queue<Integer> q = new LinkedList<>();

        q.add(s);
        visited[s] = true;
        parent[s] = s;

        while (!q.isEmpty()) {

            Integer curr = q.remove();
            for (int i = 0; i <= t; i++) {
                if (mat[curr][i] == 1 && !visited[i] && !used[i]) {
                    visited[i] = true;
                    parent[i] = curr;
                    q.add(i);
                }
            }
        }

        return visited[t];
    }

    int fflow(int s, int t) {

        int maxflow = 0;

        Arrays.fill(visited, false);
        Arrays.fill(parent, -1);
        Arrays.fill(used, false);

        while (bfs(s, t)) {

            int flow = 1 << 25;
            // Arrays.fill(visited, false);
            for (int u = t; u != s; u = parent[u]) {
                flow = Math.min(mat[parent[u]][u], flow);
                // used[u] = true;
            }

            for (int u = t; u != s; u = parent[u]) {
                mat[u][parent[u]] += flow;
                mat[parent[u]][u] -= flow;
            }

            maxflow += flow;

            Arrays.fill(visited, false);
            used[s] = false;
            used[t] = false;
            Arrays.fill(parent, -1);

        }

        return maxflow;
    }

    public int fixCeiling(String[] ceilMap) {

        int n = ceilMap.length, m = ceilMap[0].length();

        Map<Integer, Integer> map = new HashMap<>();

        int num = 1;

        int mx = 1000;

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (ceilMap[i].charAt(j) == 'x') {
                    map.put(i * mx + j, num++);
                    map.put(i * mx * mx + j, num++);
                }
            }

        for (int i = 0; i <= num; i++)
            for (int j = 0; j <= num; j++)
                mat[i][j] = 0;

        // connect source
        for (int i = 0; i < m; i++)
            if (ceilMap[0].charAt(i) == 'x') {
                mat[0][map.get(i)] = 1;
                mat[map.get(i)][0] = 0;
            }

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (ceilMap[i].charAt(j) == 'x') {
                    int ind = map.get(i * mx + j);
                    int oind = map.get(i * mx * mx + j);
                    mat[ind][oind] = 1;
                    if (i > 0 && ceilMap[i - 1].charAt(j) == 'x') {
                        int nxind = map.get((i - 1) * mx + j);
                        mat[oind][nxind] = 1;
                    }
                    if (i < n - 1 && ceilMap[i + 1].charAt(j) == 'x') {
                        int nxind = map.get((i + 1) * mx + j);
                        mat[oind][nxind] = 1;
                    }
                    if (j > 0 && ceilMap[i].charAt(j - 1) == 'x') {
                        int nxind = map.get(i * mx + j - 1);
                        mat[oind][nxind] = 1;
                    }
                    if (j < m - 1 && ceilMap[i].charAt(j + 1) == 'x') {
                        int nxind = map.get(i * mx + j + 1);
                        mat[oind][nxind] = 1;
                    }
                }

        // connect sink
        for (int i = 0; i < m; i++) {
            if (ceilMap[n - 1].charAt(i) == 'x') {
                int ind = map.get(mx * (n - 1) + i);
                mat[num][ind] = 0;
                mat[ind][num] = 1;
            }
        }

        int maxflow = fflow(0, num);

        return maxflow;
    }

    public static void main(String[] args) {
        DrippingWater d = new DrippingWater();
        System.out.println(d.fixCeiling(new String[] { "xxoxoxooxxxxxxooooooxxoxxooooxxxxxxxoxxxxooooxox",
                "oxxoxoxxxxoxoooxxxxooxoxoxoxxooxxxxxxooxxxxxoooo",
                "ooxxoxxxxxxoooxxooxooxooxxxxxoxoxooxooxoxoxxooox" }));
        System.out.println(
                d.fixCeiling(new String[] { "xooxoxooxxooooooxxoxoxxoooxxoxxxo", "ooxooxxxxoooxooxxoxoxxoxoxoooxxxx",
                        "xxxooxxooooxxxoxxxoooxxoxxoooooxx", "oooooxoxxoooooxooxxxooxoooxooxoxx",
                        "xooooooxoooxxoooxoxooxoxxxooxxxxo", "oxxxoooooooooxoxxxxxooxxxoxxoxoxo",
                        "xxoooxooxoxoxxxooxxxooxoooxxxooxx", "xoxxxoxxoxooxxoxoxoxxxoxxooxoxxoo",
                        "xxxxxxxoxoxoxoxxxxoxoxoxoxoxooxxx", "xxxxoxxxxoxxoooxxxooxxoxxooxxxoox",
                        "xxoxooxxxxooxxxooxxooxxxxooxxxoxx", "xoooxxooooooxxooxxooxooxxoooxxoox" }));
        System.out.println(d.fixCeiling(new String[] { "xxoxx", "oxoxo", "xxoxx", "xxoxx", "xxoxx" }));
        System.out.println(d.fixCeiling(new String[] { "xoxox", "xoxox", "xoxox", "xoxox", "xoxox" }));
        System.out.println(d.fixCeiling(
                new String[] { "ooooooxoxoo", "ooooooxoxoo", "oooxxxxxxoo", "oooxooxoooo", "oooxooxoooo" }));
    }
}
