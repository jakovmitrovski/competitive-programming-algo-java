package aHARD500;

import java.util.*;

public class HoneyBee {

    Node[] g = new Node[5000];
    int[][] distance = new int[15][15];
    int[][] dp = new int[15][1 << 15];

    class Node {
        int node;
        List<Integer> edges;

        public Node(int node) {
            this.node = node;
            this.edges = new ArrayList<>();
        }
    }

    List<Integer> bfs(int teme, int[] empty) {
        Queue<Integer> q = new LinkedList<>();
        q.add(teme);
        boolean[] visited = new boolean[5000];
        int[] dist = new int[5000];
        Arrays.fill(dist, 1 << 30);
        dist[teme] = 0;
        visited[teme] = true;
        List<Integer> ret = new ArrayList<>();

        while (!q.isEmpty()) {

            int curr = q.remove();

            for (int i = 0; i < g[curr].edges.size(); i++) {
                int next = g[curr].edges.get(i);
                if (!visited[next]) {
                    dist[next] = dist[curr] + 1;
                    visited[next] = true;
                    q.add(next);
                }
            }
        }

        for (int i = 0; i < empty.length; i++)
            ret.add(dist[empty[i]]);
        return ret;
    }

    int res(int ind, int mask, int load, int n) {
        if (dp[ind][mask] != -1)
            return dp[ind][mask];
        if (Integer.bitCount(mask) == load)
            return 0;
        if (Integer.bitCount(mask) > load)
            return 1 << 25;

        int ret = 1 << 25;

        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) == 0) {
                ret = Math.min(ret, distance[ind][i] + res(i, mask | (1 << i), load, n));
            }
        }

        return dp[ind][mask] = ret;
    }

    public int shortestPath(int size, int load, int[] empty, int[] blocked) {

        boolean[] vis = new boolean[5000];
        for (int i = 0; i < blocked.length; i++)
            vis[blocked[i]] = true;

        if (vis[1])
            return -1;

        int curr = size;
        int ind = 1;

        for (int i = 0; i < 5000; i++)
            g[i] = new Node(i);
        for (int i = 1; i < 2 * size; i++) {
            for (int j = 1; j <= curr; j++) {
                if (j != 1 && !vis[ind - 1])
                    g[ind].edges.add(ind - 1);
                if (j != curr && !vis[ind + 1])
                    g[ind].edges.add(ind + 1);

                if (i < size) {
                    if (j != 1 && i != 1 && !vis[ind - curr])
                        g[ind].edges.add(ind - curr);
                    if (j != curr && i != 1 && !vis[ind - curr + 1])
                        g[ind].edges.add(ind - curr + 1);

                    if (!vis[ind + curr])
                        g[ind].edges.add(ind + curr);
                    if (!vis[ind + curr + 1])
                        g[ind].edges.add(ind + curr + 1);
                } else if (i == size && j == 1) {
                    if (!vis[ind - curr + 1])
                        g[ind].edges.add(ind - curr + 1);
                    if (!vis[ind + curr])
                        g[ind].edges.add(ind + curr);
                } else if (i == size && j == curr) {
                    if (!vis[ind - curr])
                        g[ind].edges.add(ind - curr);
                    if (!vis[ind + curr - 1])
                        g[ind].edges.add(ind + curr - 1);
                } else if (i == size && j > 1 && j < curr) {
                    if (!vis[ind - curr])
                        g[ind].edges.add(ind - curr);
                    if (!vis[ind + curr])
                        g[ind].edges.add(ind + curr);
                    if (!vis[ind - curr + 1])
                        g[ind].edges.add(ind - curr + 1);
                    if (!vis[ind + curr - 1])
                        g[ind].edges.add(ind + curr - 1);
                } else {

                    if (i != 2 * size - 1 && j != 1 && !vis[ind + curr - 1])
                        g[ind].edges.add(ind + curr - 1);
                    if (i != 2 * size - 1 && j != curr && !vis[ind + curr])
                        g[ind].edges.add(ind + curr);

                    if (!vis[ind - curr - 1])
                        g[ind].edges.add(ind - curr - 1);
                    if (!vis[ind - curr])
                        g[ind].edges.add(ind - curr);
                }

                ind++;
            }
            if (i < size)
                curr++;
            else {
                curr--;
            }
        }

        // for (int i=0; i<ind; i++){
        // System.out.print("sosedi na "+ i + " se: " );
        // for (int j=0; j<g[i].edges.size(); j++) System.out.print( g[i].edges.get(j)+"
        // ");
        // System.out.println();
        // }

        for (int i = 0; i <= empty.length; i++) {
            if (i == empty.length) {
                List<Integer> dists = bfs(1, empty);
                for (int j = 0; j < empty.length; j++)
                    distance[14][j] = dists.get(j);
            } else {
                List<Integer> dists = bfs(empty[i], empty);
                for (int j = 0; j < empty.length; j++)
                    distance[i][j] = dists.get(j);
            }
        }

        // for (int i=0; i<=14; i++) {
        // System.out.println();
        // for (int j = 0; j <= 14; j++) System.out.print(dist[i][j] + " ");
        // }

        for (int i = 0; i < 15; i++)
            for (int j = 0; j < 1 << 15; j++)
                dp[i][j] = -1;

        int x = res(14, 0, load, empty.length);
        if (x >= 1 << 25)
            return -1;
        return x;
    }

    public static void main(String[] args) {
        HoneyBee h = new HoneyBee();
        System.out.println(h.shortestPath(3, 3, new int[] { 17, 2, 7 }, new int[] { 13, 16, 3, 4 }));
        System.out.println(h.shortestPath(12, 6, new int[] { 254, 57, 141, 350, 172, 160, 228 },
                new int[] { 39, 38, 140, 382, 358, 136, 293, 222, 125, 114, 42, 3, 255, 139, 302 }));
        System.out.println(h.shortestPath(

                20, 2, new int[] { 2, 1000 },
                new int[] { 552, 553, 554, 555, 556, 557, 558, 559, 560, 561, 562, 563, 564, 565, 566, 567, 568, 569,
                        570, 571, 572, 573, 574, 575, 576, 577, 578, 579, 580, 581, 582, 583, 584, 585, 586, 587, 588,
                        589, 590 }));
    }
}
