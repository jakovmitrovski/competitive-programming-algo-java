package aHARD500;

import java.util.Comparator;
import java.util.PriorityQueue;

public class SuperSnakes {

    class Node {
        int node;
        long dist;
        int shots;

        public Node(int node, long dist, int shots) {
            this.node = node;
            this.dist = dist;
            this.shots = shots;
        }

        long getDist() {
            return dist;
        }
    }

    public int minRaysCast(int princeCity, int princessCity, int totalTime,
            int maxSuperSnake, String[] superSnakes) {

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(Node::getDist));
        pq.add(new Node(princeCity, 0, 0));
        long[][] dp = new long[51][1000];

        for (int i = 0; i < 51; i++)
            for (int j = 0; j < 1000; j++)
                dp[i][j] = 1L << 60L;

        dp[princeCity][0] = 0;

        while (!pq.isEmpty()) {
            Node curr = pq.remove();

            if (dp[curr.node][curr.shots] > totalTime)
                continue;

            for (int next = 0; next < superSnakes[curr.node].length(); next++) {
                char for_cost = superSnakes[curr.node].charAt(next);
                long now_cost;
                if (Character.isUpperCase(for_cost))
                    now_cost = for_cost - 'A' + 26;
                else
                    now_cost = for_cost - 'a';
                now_cost = 1L << now_cost;
                for (int j = 0; (curr.shots + j < 1000 && (1L << j) <= now_cost); j++) {
                    long cost = now_cost >> j;
                    if (cost > maxSuperSnake)
                        continue;
                    if (curr.dist + cost <= totalTime && dp[next][curr.shots + j] > dp[curr.node][curr.shots] + cost) {
                        dp[next][curr.shots + j] = dp[curr.node][curr.shots] + cost;
                        pq.add(new Node(next, dp[next][curr.shots + j], curr.shots + j));
                    }
                }
            }
        }

        for (int i = 0; i < 61; i++) {
            // System.out.println(dp[princessCity][i]);
            if (dp[princessCity][i] <= totalTime)
                return i;
        }

        return -1;
    }

    public static void main(String[] args) {
        SuperSnakes s = new SuperSnakes();
        System.out.println(s.minRaysCast(9, 2, 1, 12, new String[] { "udrwynnggq", "dewsssbgys", "rwjlhkopps",
                "wslgwnnzxi", "yshwnwblxs", "nsknwfthkf", "nbonbtuhsf", "ggpzlhhhsb", "gypxxkssmy", "qssisffbyl" }));
        System.out.println(s.minRaysCast(0, 4, 8, 57, new String[] { "agffh", "gbahe", "fahec", "fhehg", "hecgf" }));
        System.out.println(s.minRaysCast(2, 1, 42, 3, new String[] { "fde", "dgf", "efd" }));
    }
}
