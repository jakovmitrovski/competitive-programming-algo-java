package veryhard400;

import java.util.LinkedList;
import java.util.Queue;

public class WeakPortal {
    int N, M;

    class Item {
        int x, y, shots, dist;

        public Item(int x, int y, int shots, int dist) {
            this.x = x;
            this.y = y;
            this.shots = shots;
            this.dist = dist;
        }
    }

    private int Levo(int x, int y, String[] map) {
        while (y - 1 >= 0 && map[x].charAt(y - 1) != '#')
            y--;
        return y;
    }

    private int Desno(int x, int y, String[] map) {
        while (y + 1 < M && map[x].charAt(y + 1) != '#')
            y++;
        return y;
    }

    private int Gore(int x, int y, String[] map) {
        while (x - 1 >= 0 && map[x - 1].charAt(y) != '#')
            x--;
        return x;
    }

    private int Dolu(int x, int y, String[] map) {
        while (x + 1 < N && map[x + 1].charAt(y) != '#')
            x++;
        return x;
    }

    public int getEffort(String[] map, int shots) {
        int n = map.length, m = map[0].length();
        N = n;
        M = m;
        int sx = 0, sy = 0, ex = 0, ey = 0;

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (map[i].charAt(j) == 'H') {
                    sx = i;
                    sy = j;
                }
                if (map[i].charAt(j) == 'T') {
                    ex = i;
                    ey = j;
                }
            }

        Queue<Item> q = new LinkedList<>();
        boolean[][][] visited = new boolean[30][30][20];
        q.add(new Item(sx, sy, shots, 0));
        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };
        int minDist = 9999;
        while (!q.isEmpty()) {
            Item curr = q.remove();
            // System.out.println(curr.x + " " + curr.y + " " + curr.shots + " " + curr.dist
            // + " " + ex + " " + ey) ;
            if (curr.x == ex && curr.y == ey)
                minDist = Math.min(minDist, curr.dist);

            if (curr.shots > 0) {
                int newy = Levo(curr.x, curr.y, map);
                visited[curr.x][newy][shots - 1] = true;
                q.add(new Item(curr.x, newy, curr.shots - 1, curr.dist));

                newy = Desno(curr.x, curr.y, map);
                visited[curr.x][newy][shots - 1] = true;
                q.add(new Item(curr.x, newy, curr.shots - 1, curr.dist));

                int newx = Gore(curr.x, curr.y, map);
                visited[newx][curr.y][shots - 1] = true;
                q.add(new Item(newx, curr.y, curr.shots - 1, curr.dist));

                newx = Dolu(curr.x, curr.y, map);
                visited[newx][curr.y][shots - 1] = true;
                q.add(new Item(newx, curr.y, curr.shots - 1, curr.dist));

            }

            // move normally
            for (int move = 0; move < 4; move++) {
                int nx = curr.x + dx[move], ny = curr.y + dy[move];
                // if (curr.x == 9 && curr.y == 7 && curr.shots == 1 && curr.dist == 1)
                // System.out.println(nx + " " + ny + " OVA E NEXT MOVE ");
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny][curr.shots]
                        && map[nx].charAt(ny) != '#') {
                    visited[nx][ny][curr.shots] = true;
                    q.add(new Item(nx, ny, curr.shots, curr.dist + 1));
                }
            }
        }

        return minDist == 9999 ? -1 : minDist;
    }

}
