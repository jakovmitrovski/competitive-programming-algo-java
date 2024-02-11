package veryhard400;

import java.util.LinkedList;
import java.util.Queue;

public class TrampolineMaze {
    class Item {
        int x, y, facing;

        public Item(int x, int y, int facing) {
            this.x = x;
            this.y = y;
            this.facing = facing;
        }
    }

    public int solve(String[] maze) {
        int n = maze.length, m = maze[0].length(), sx = 0, sy = 0, ex = 0, ey = 0;

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (maze[i].charAt(j) == 'S') {
                    sx = i;
                    sy = j;
                }
                if (maze[i].charAt(j) == 'E') {
                    ex = i;
                    ey = j;
                }
            }

        Queue<Item> q = new LinkedList<>();

        int[][][] dp = new int[101][101][5];
        for (int i = 0; i < 101; i++)
            for (int j = 0; j < 101; j++)
                for (int k = 0; k < 5; k++)
                    dp[i][j][k] = 1 << 30;

        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };
        int[] facing = { 0, 1, 2, 3 };
        for (int i = 0; i < 4; i++) {
            q.add(new Item(sx, sy, i));
            dp[sx][sy][i] = 0;
        }

        while (!q.isEmpty()) {
            Item curr = q.remove();
            if (curr.x == ex && curr.y == ey)
                return dp[curr.x][curr.y][curr.facing];

            for (int move = 0; move < 4; move++) {
                int nx = curr.x + dx[move], ny = curr.y + dy[move];
                int nf = facing[move];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && maze[nx].charAt(ny) != '#'
                        && dp[nx][ny][nf] > dp[curr.x][curr.y][curr.facing] + 1) {
                    dp[nx][ny][nf] = dp[curr.x][curr.y][curr.facing] + 1;
                    q.add(new Item(nx, ny, nf));
                }
            }

            if (maze[curr.x].charAt(curr.y) == 'T') {
                int nx = curr.x + 2 * dx[curr.facing];
                int ny = curr.y + 2 * dy[curr.facing];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && maze[nx].charAt(ny) != '#'
                        && dp[nx][ny][curr.facing] > dp[curr.x][curr.y][curr.facing] + 1) {
                    dp[nx][ny][curr.facing] = dp[curr.x][curr.y][curr.facing] + 1;
                    q.add(new Item(nx, ny, curr.facing));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        TrampolineMaze m = new TrampolineMaze();
        System.out.println(
                m.solve(new String[] { "#.....#", "#######", "#T#T#T#", "S...T.E", "#T#T#T#", "#######", "#.....#" }));
        System.out
                .println(m.solve(new String[] { "#####.T#.##.T#.#.T#....###.E.#", "S..T#.##....##...####TT#..T#.#" }));
        // System.out.println(m.solve(new
        // String[]{"#####.T#.##.T#.#.T#....###.E.#","S..T#.##....##...####TT#..T#.#"}));
    }

}