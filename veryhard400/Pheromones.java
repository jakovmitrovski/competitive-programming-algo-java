package veryhard400;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Pheromones {

    class Rebro {
        int from, to, cost;
        String name;

        public Rebro(int from, int to, int cost, String name) {
            this.from = from;
            this.to = to;
            this.cost = cost;
            this.name = name;
        }

        public int getCost() {
            return cost;
        }

        public String getName() {
            return name;
        }
    }

    int[] parent = new int[51];
    int[] depth = new int[51];

    int find(int x) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }

        return x;
    }

    void unite(int x, int y) {
        x = find(x);
        y = find(y);

        if (depth[x] >= depth[y]) {
            parent[x] = y;
            depth[y]++;
        } else {
            parent[y] = x;
            depth[x]++;
        }
    }

    public String connectNetwork(int N, String[] flowerRoads) {

        for (int i = 1; i <= N; i++) {
            depth[i] = 1;
            parent[i] = i;
        }
        List<Rebro> edges = new ArrayList<>();
        for (int i = 0; i < flowerRoads.length; i++) {
            String[] in = flowerRoads[i].split(" ");
            int from = Integer.parseInt(in[0]), to = Integer.parseInt(in[1]), cost = Integer.parseInt(in[3]);

            Rebro r = new Rebro(from, to, cost, in[2]);
            edges.add(r);
        }

        edges.sort(Comparator.comparing(Rebro::getCost).thenComparing(Rebro::getName));

        List<String> list = new ArrayList<>();

        for (Rebro r : edges) {
            int from = r.from, to = r.to;
            String name = r.name;

            if (find(from) != find(to)) {
                unite(from, to);
                list.add(name);
            }
        }

        int par = find(1);

        for (int i = 2; i <= N; i++)
            if (find(i) != par)
                return "impossible";
        list.sort(Comparator.comparing(x -> x));

        return String.join(" ", list);
    }

    public static void main(String[] args) {
        Pheromones p = new Pheromones();
        System.out.println(p.connectNetwork(7,
                new String[] { "1 2 OM 86", "3 4 DKNP 19", "6 2 TXAL 65", "6 5 QIT 50", "6 3 XRV 16", "2 6 EEG 71",
                        "5 6 LQ 67", "7 2 Q 11", "7 5 RTXZ 72", "4 2 X 29", "4 1 GN 74", "7 4 ZMTNX 79", "2 7 L 11",
                        "5 3 S 54" }));
        System.out.println(p.connectNetwork(6, new String[] { "2 1 MK 10", "3 4 BG 3", "2 4 GR 5", "4 1 SR 3",
                "5 4 NL 8", "1 5 ES 4", "1 6 IT 2", "6 5 AL 7" }));
        System.out.println(
                p.connectNetwork(4, new String[] { "1 2 A 5", "1 3 B 5", "1 4 C 2", "2 3 D 5", "2 4 E 3", "3 4 F 1" }));
        System.out.println(p.connectNetwork(50,
                new String[] { "1 2 A 5", "1 3 B 5", "1 4 C 2", "2 3 D 5", "2 4 E 3", "3 4 F 1" }));
    }
}
