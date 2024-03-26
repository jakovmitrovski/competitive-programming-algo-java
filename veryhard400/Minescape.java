package veryhard400;

import java.util.LinkedList;
import java.util.Queue;

public class Minescape {

    class Item {
        int x, y, dynamites, mask;

        public Item(int x, int y, int dynamites, int mask) {
            this.x = x;
            this.y = y;
            this.dynamites = dynamites;
            this.mask = mask;
        }
    }

    public int escape(String[] maze1) {

        char[][] maze = new char[50][20];
        int n = maze1.length, sx = 0, sy = 0, ex = 0, ey = 0, br = 0,
                indx[][] = new int[100][100];

        for (int i = 0; i < 100; i++)
            for (int j = 0; j < 100; j++)
                indx[i][j] = -1;

        for (int i = 0; i < n; i++)
            for (int j = 0; j < maze1[i].length(); j++) {
                maze[i][j] = maze1[i].charAt(j);
                if (maze[i][j] == 'S') {
                    sx = i;
                    sy = j;
                }

                if (maze[i][j] == 'E') {
                    ex = i;
                    ey = j;
                }

                if (maze[i][j] == '@') {
                    indx[i][j] = br;
                    br++;
                }
            }

        Queue<Item> q = new LinkedList<>();
        int[][][][] dp = new int[21][16][100][1 << (br) + 1];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < maze1[i].length(); j++)
                for (int k = 0; k < 100; k++)
                    for (int l = 0; l < (1 << br); l++)
                        dp[i][j][k][l] = 999999;

        dp[sx][sy][0][0] = 0;
        q.add(new Item(sx, sy, 0, 0));

        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        while (!q.isEmpty()) {
            Item curr = q.remove();

            if (curr.x == ex && curr.y == ey)
                return dp[curr.x][curr.y][curr.dynamites][curr.mask];

            for (int move = 0; move < 4; move++) {
                int nx = curr.x + dx[move], ny = curr.y + dy[move];

                if (nx >= 0 && nx < n && ny >= 0 && ny < maze1[nx].length()) {

                    if (maze[nx][ny] == '@') {
                        int newMask, newDynamites;
                        if ((curr.mask & (1 << indx[nx][ny])) == 0) {
                            newMask = curr.mask | (1 << indx[nx][ny]);
                            newDynamites = curr.dynamites + 1;
                        } else {
                            newMask = curr.mask;
                            newDynamites = curr.dynamites;
                        }
                        if (dp[nx][ny][newDynamites][newMask] > dp[curr.x][curr.y][curr.dynamites][curr.mask] + 1) {
                            dp[nx][ny][newDynamites][newMask] = dp[curr.x][curr.y][curr.dynamites][curr.mask] + 1;
                            q.add(new Item(nx, ny, newDynamites, newMask));
                        }
                    }

                    else if (maze[nx][ny] == '#' && curr.dynamites > 0) {
                        int newDynamites = curr.dynamites - 1;
                        if (dp[nx][ny][newDynamites][curr.mask] > dp[curr.x][curr.y][curr.dynamites][curr.mask] + 1) {
                            dp[nx][ny][newDynamites][curr.mask] = dp[curr.x][curr.y][curr.dynamites][curr.mask] + 1;
                            q.add(new Item(nx, ny, newDynamites, curr.mask));
                        }
                    }

                    else if (maze[nx][ny] == ' ' || maze[nx][ny] == 'S' || maze[nx][ny] == 'E') {
                        if (dp[nx][ny][curr.dynamites][curr.mask] > dp[curr.x][curr.y][curr.dynamites][curr.mask] + 1) {
                            dp[nx][ny][curr.dynamites][curr.mask] = dp[curr.x][curr.y][curr.dynamites][curr.mask] + 1;
                            q.add(new Item(nx, ny, curr.dynamites, curr.mask));
                        }
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Minescape m = new Minescape();
        System.out.println(
                m.escape(new String[] { "###############", "#@@@#S   ######", "#@@##@   ####E#", "###############" }));
    }
}
