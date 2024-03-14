package aHARD500;

import java.util.*;

public class Earthquake {

    class Node {
        int node;
        List<Integer> neighbours;

        public Node(int node) {
            this.node = node;
            this.neighbours = new ArrayList<>();
        }
    }

    int n;
    boolean[] visited;
    List<Integer> red;
    Node[] g;
    Node[] g1;
    int[] group;

    void dfs(int teme) {
        visited[teme] = true;

        for (int i = 0; i < g[teme].neighbours.size(); i++)
            if (!visited[g[teme].neighbours.get(i)])
                dfs(g[teme].neighbours.get(i));

        red.add(teme);
    }

    void dfs2(int teme, int gr) {
        group[teme] = gr;

        visited[teme] = true;

        for (int i = 0; i < g1[teme].neighbours.size(); i++)
            if (!visited[g1[teme].neighbours.get(i)])
                dfs2(g1[teme].neighbours.get(i), gr);
    }

    public int newRoads(String[] roads) {

        g = new Node[51];
        g1 = new Node[51];
        group = new int[51];
        n = roads.length;
        red = new ArrayList<>();
        for (int i = 0; i < roads.length; i++) {
            g1[i] = new Node(i);
            g[i] = new Node(i);
        }
        visited = new boolean[51];

        for (int i = 0; i < roads.length; i++) {
            group[i] = -1;
            String[] nums = roads[i].split(" ");
            for (int j = 0; j < nums.length; j++)
                if (nums[j].equals("none"))
                    continue;
                else {
                    int from = i;
                    int to = Integer.parseInt(nums[j]);

                    g[from].neighbours.add(to);
                    g1[to].neighbours.add(from);
                }
        }

        for (int i = 0; i < n; i++)
            if (!visited[i])
                dfs(i);

        visited = new boolean[51];

        int gr = 0;
        for (int i = red.size() - 1; i >= 0; i--)
            if (!visited[red.get(i)]) {
                gr++;
                dfs2(red.get(i), red.get(i));
            }

        if (gr == 1)
            return 0;

        int[][] dist = new int[51][51];

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < g[i].neighbours.size(); j++) {
                int nx = g[i].neighbours.get(j);
                if (group[i] != group[nx]) {
                    dist[group[i]][group[nx]] = 1;
                }
            }
        }

        int[] inDeg = new int[51];
        int[] outDeg = new int[51];

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++)
                if (dist[i][j] == 1) {
                    inDeg[j] = 1;
                    outDeg[i] = 1;
                }
        }

        int inn = 0, out = 0;
        for (int i = 0; i < 51; i++) {
            out += outDeg[i];
            inn += inDeg[i];
        }

        return Math.max(gr - out, gr - inn);
    }

    public static void main(String[] args) {
        Earthquake e = new Earthquake();
        System.out.println(
                e.newRoads(new String[] { "1 5 6 8", "5 6 9", "9", "none", "7", "3", "9", "3 4 8", "1 2 9", "none" }));
        System.out.println(e.newRoads(new String[] { "2", "2", "none", "0 3", "none" }));
    }
}