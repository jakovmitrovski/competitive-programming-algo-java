package veryhard400;

import java.util.LinkedList;
import java.util.Queue;

public class CrushingSnake {

    class Item {
        int x, y, dist, cnt, mapstartx, mapstarty, mapendx, mapendy;

        public Item(int x, int y, int dist, int cnt, int mapstartx, int mapstarty, int mapendx, int mapendy) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.cnt = cnt;
            this.mapstartx = mapstartx;
            this.mapstarty = mapstarty;
            this.mapendx = mapendx;
            this.mapendy = mapendy;
        }
    }

    public int getSurvivalTime(String[] maze) {
        int n = maze.length;
        int m = maze[0].length();

        int sx = 0, sy = 0, ex = 0, ey = 0;

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (maze[i].charAt(j) == 'X') {
                    sx = i;
                    sy = j;
                }
                if (maze[i].charAt(j) == 'T') {
                    ex = i;
                    ey = j;
                }
            }

        Queue<Item> q = new LinkedList<>();
        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        q.add(new Item(sx, sy, 0, 0, 0, 0, n, m));
        boolean[][] vis = new boolean[n + 2][m + 2];
        vis[sx][sy] = true;
        while (!q.isEmpty()) {
            Item curr = q.remove();
            // System.out.println(curr.x + " " + curr.y + " " + curr.dist + " " +
            // toCrush[curr.cnt]);
            if (ex == curr.x && ey == curr.y)
                return curr.dist;
            if (curr.x < curr.mapstartx || curr.x >= curr.mapendx || curr.y < curr.mapstarty || curr.y >= curr.mapendy)
                continue;
            for (int move = 0; move < 4; move++) {
                int nx = curr.x + dx[move], ny = curr.y + dy[move], ntc = (curr.cnt + 1) % 4;
                if (nx >= curr.mapstartx && nx < curr.mapendx && ny >= curr.mapstarty && ny < curr.mapendy
                        && !vis[nx][ny] && maze[nx].charAt(ny) != '#') {
                    // System.out.println(nx + " " + ny + " " + curr.mapstartx + " " +
                    // curr.mapstarty + " " + curr.mapendx + " " + curr.mapendy);
                    if (curr.cnt == 0) {
                        int newmapstartx = curr.mapstartx + 1;
                        vis[nx][ny] = true;
                        q.add(new Item(nx, ny, curr.dist + 1, ntc, newmapstartx, curr.mapstarty, curr.mapendx,
                                curr.mapendy));
                    } else if (curr.cnt == 1) {
                        int newmapstarty = curr.mapstarty + 1;
                        vis[nx][ny] = true;
                        q.add(new Item(nx, ny, curr.dist + 1, ntc, curr.mapstartx, newmapstarty, curr.mapendx,
                                curr.mapendy));
                    } else if (curr.cnt == 2) {
                        int newmapendx = curr.mapendx - 1;
                        vis[nx][ny] = true;
                        q.add(new Item(nx, ny, curr.dist + 1, ntc, curr.mapstartx, curr.mapstarty, newmapendx,
                                curr.mapendy));
                    } else {
                        int newmapendy = curr.mapendy - 1;
                        vis[nx][ny] = true;
                        q.add(new Item(nx, ny, curr.dist + 1, ntc, curr.mapstartx, curr.mapstarty, curr.mapendx,
                                newmapendy));
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        CrushingSnake s = new CrushingSnake();
        System.out.println(s.getSurvivalTime(new String[] { "...##....", ".#.....#.", ".##T..#..", "....#...#",
                ".##..##..", ".#..#....", ".#.###...", "##..#..#.", ".X.#..#.." }));
    }
}
