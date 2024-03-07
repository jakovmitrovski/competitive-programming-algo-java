package veryhard400;

import java.util.*;

public class Kermit {

    double[] dist = new double[55];
    Map<Integer, Point> map = new HashMap<>();

    class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    class Edge {
        int to;
        double cost;

        public Edge(int to, double cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    class Node {
        int me;
        List<Edge> edges;

        public Node(int me) {
            this.me = me;
            this.edges = new ArrayList<>();
        }

        double getWeight() {
            return dist[me];
        }
    }

    double dist(Point x, Point y) {
        Point a = new Point(x.x, x.y);
        Point b = new Point(y.x, y.y);

        if (a.y == -1 && b.y == -1) {
            return Math.abs(a.x - b.x);
        }

        if (a.y == -1)
            a.y = b.y;
        if (b.y == -1)
            b.y = a.y;

        return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }

    public String swim(int R, int[] X, int[] Y) {

        map.clear();

        List<Point> points = new ArrayList<>();

        for (int i = 0; i < X.length; i++) {
            Point p = new Point(X[i], Y[i]);
            points.add(p);
            map.put(i + 1, p);
        }

        map.put(0, new Point(0, -1));
        map.put(X.length + 1, new Point(R, -1));

        Node[] g = new Node[55];
        int n = X.length + 1;
        for (int i = 0; i <= n; i++)
            g[i] = new Node(i);

        for (int i = 0; i <= n; i++)
            for (int j = i + 1; j <= n; j++) {
                Node from = g[i], to = g[j];
                double cost = dist(map.get(from.me), map.get(to.me));
                if (cost - 10 < 0.00001) {
                    // System.out.println("added edge between " + map.get(from.me).x + " " +
                    // map.get(from.me).y + " and " + map.get(to.me).x + " " + map.get(to.me).y + "
                    // and cost is " + cost);
                    g[i].edges.add(new Edge(to.me, cost));
                    g[j].edges.add(new Edge(from.me, cost));
                }
            }
        Arrays.fill(dist, 1e9);
        boolean[] visited = new boolean[55];
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(Node::getWeight));

        // System.out.println(map.get(0).x + " " + map.get(0).y);
        dist[g[0].me] = 0;
        pq.add(g[0]);

        // for (int i=0; i<g[0].edges.size(); i++) System.out.print(g[0].edges.get(i).to
        // + " ");
        // System.out.println();

        while (!pq.isEmpty()) {
            Node curr = pq.remove();
            if (visited[curr.me])
                continue;
            // System.out.println(map.get(curr.me).x + " " + map.get(curr.me).y);
            visited[curr.me] = true;
            if (curr.me == n || map.get(curr.me).x == R) {
                return String.format("%.2f", dist[curr.me]);
                // return String.valueOf(Math.round(dist[curr.me])*100.0/100.0);
            }
            for (int i = 0; i < curr.edges.size(); i++) {
                int next = curr.edges.get(i).to;
                double cost = curr.edges.get(i).cost;

                if (!visited[next] && dist[next] - (dist[curr.me] + cost) > 0.00001) {
                    dist[next] = dist[curr.me] + cost;
                    pq.add(g[next]);

                    visited[curr.me] = true;
                }
            }
        }

        return "poor kermit";
    }

    public static void main(String[] args) {
        Kermit k = new Kermit();
        System.out.println(k.swim(20, new int[] { 10 }, new int[] { 15 }));
        System.out.println(k.swim(20, new int[] { 9, 4, 1, 7, 7, 7, 18 }, new int[] { 20, 0, 6, 25, 19, 2, 30 }));
        System.out.println(k.swim(24, new int[] { 7, 20, 2, 22, 14, 5, 1 }, new int[] { 18, 19, 2, 14, 24, 20, 20 }));
    }
}
