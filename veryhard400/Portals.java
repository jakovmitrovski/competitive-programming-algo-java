package veryhard400;

import java.util.*;

public class Portal {

    class Item {
        int x, y, bx, by, rx, ry;

        public Item(int x, int y, int bx, int by, int rx, int ry) {
            this.x = x;
            this.y = y;
            this.bx = bx;
            this.by = by;
            this.rx = rx;
            this.ry = ry;
        }
    }

    int[] fire_portal(int posx, int posy, int dir, String[] maze) {
        int[] ret = new int[2];
        if (maze[posx].charAt(posy) == '#') {
            ret[0] = posx;
            ret[1] = posy;
            return ret;
        }

        if (dir == 0) {
            for (int i = posx; i >= 0; i--)
                if (maze[i].charAt(posy) == '#') {
                    ret[0] = i;
                    ret[1] = posy;
                    return ret;
                }
        }

        if (dir == 1) {
            for (int i = posx; i < maze.length; i++)
                if (maze[i].charAt(posy) == '#') {
                    ret[0] = i;
                    ret[1] = posy;
                    return ret;
                }
        }

        if (dir == 2) {
            for (int i = posy; i >= 0; i--)
                if (maze[posx].charAt(i) == '#') {
                    ret[0] = posx;
                    ret[1] = i;
                    return ret;
                }
        }

        if (dir == 3) {
            for (int i = posy; i < maze[0].length(); i++)
                if (maze[posx].charAt(i) == '#') {
                    ret[0] = posx;
                    ret[1] = i;
                    return ret;
                }
        }

        ret[0] = -1;
        ret[1] = -1;
        return ret;

    }

    public int mimimumTime(String[] maze) {

        int n = maze.length, m = maze[0].length();
        int sx = 0, sy = 0, ex = 0, ey = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (maze[i].charAt(j) == 'S') {
                    sx = i;
                    sy = j;
                } else if (maze[i].charAt(j) == 'E') {
                    ex = i;
                    ey = j;
                }

        Queue<Item> q = new LinkedList<>();
        Item start = new Item(sx, sy, n, m, n, m);
        q.add(start);

        // for (int i=0; i<n; i++) System.out.println(maze[i]);

        int[][][][][][] dp = new int[n][m][n + 1][m + 1][n + 1][m + 1];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                for (int k = 0; k < n + 1; k++)
                    for (int l = 0; l < m + 1; l++)
                        for (int p = 0; p < n + 1; p++)
                            for (int t = 0; t < m + 1; t++)
                                dp[i][j][k][l][p][t] = 1 << 30;

        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        dp[start.x][start.y][n][m][n][m] = 0;

        int result = 1 << 30;

        while (!q.isEmpty()) {

            Item curr = q.remove();

            if (curr.x == curr.bx && curr.y == curr.by) {

                Item item = new Item(curr.rx, curr.ry, curr.bx, curr.by, curr.rx, curr.ry);

                if (dp[curr.rx][curr.ry][curr.bx][curr.by][curr.rx][curr.ry] > dp[curr.x][curr.y][curr.bx][curr.by][curr.rx][curr.ry]) {
                    q.add(item);
                    dp[curr.rx][curr.ry][curr.bx][curr.by][curr.rx][curr.ry] = dp[curr.x][curr.y][curr.bx][curr.by][curr.rx][curr.ry];
                }
            }

            if (maze[curr.x].charAt(curr.y) == '#' && (curr.rx != curr.x || curr.ry != curr.y))
                continue;

            if (curr.x == ex && curr.y == ey) {
                result = Math.min(result, dp[curr.x][curr.y][curr.bx][curr.by][curr.rx][curr.ry]);
                continue;
            }

            for (int move = 0; move < 4; move++) {
                int nx = curr.x + dx[move], ny = curr.y + dy[move];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && maze[nx].charAt(ny) != '@') {

                    // move normally
                    Item next = new Item(nx, ny, curr.bx, curr.by, curr.rx, curr.ry);

                    if (dp[nx][ny][curr.bx][curr.by][curr.rx][curr.ry] > dp[curr.x][curr.y][curr.bx][curr.by][curr.rx][curr.ry]
                            + 1) {
                        dp[nx][ny][curr.bx][curr.by][curr.rx][curr.ry] = dp[curr.x][curr.y][curr.bx][curr.by][curr.rx][curr.ry]
                                + 1;
                        q.add(next);
                    }

                    // fire a portal
                    if ((curr.x != curr.bx || curr.y != curr.by) && (curr.x != curr.rx || curr.y != curr.ry)
                            && maze[curr.x].charAt(curr.y) != '#') {

                        int[] pos_b = fire_portal(curr.x, curr.y, move, maze);

                        if (pos_b[0] == -1)
                            continue;

                        for (int dir = 0; dir < 4; dir++) {
                            if (dir == move)
                                continue;

                            int[] pos_r = fire_portal(curr.x, curr.y, dir, maze);
                            if (pos_r[0] == -1)
                                continue;

                            next = new Item(curr.x, curr.y, pos_b[0], pos_b[1], pos_r[0], pos_r[1]);
                            if (dp[curr.x][curr.y][pos_b[0]][pos_b[1]][pos_r[0]][pos_r[1]] > dp[curr.x][curr.y][curr.bx][curr.by][curr.rx][curr.ry]) {
                                dp[curr.x][curr.y][pos_b[0]][pos_b[1]][pos_r[0]][pos_r[1]] = dp[curr.x][curr.y][curr.bx][curr.by][curr.rx][curr.ry];
                                q.add(next);
                            }
                        }
                    }
                }
            }

        }

        if (result == 1 << 30)
            result = -1;
        return result;
    }

    public static void main(String[] args) {
        Portal p = new Portal();
        System.out.println(p.mimimumTime(new String[] { "###############", "#@######## @###", "#  @@@@@@#@#@ #",
                "##   #    @# ##", "# #  S@@@@#@###", "#@# #### ## @##", "########@##@  #", "## #@##  #   ##",
                "##@#### #@@@# #", "# @ ## ### ## #", "#@#@#@E@#######", "## #@#@#@##@@##", "########@ @## #",
                "##  @#@#@@#  @#", "###############" }));
        System.out.println(p.mimimumTime(new String[] { "###############", "# @##@# ##@# ##", "##@##@@# # ## #",
                "##@## ### @## #", "# # @@@@ @ ####", "## E### #@ ####", "##@@#@@  # #  #", "### #### #@####",
                "#@@@#   ##@# ##", "###@# @@#@ @###", "##@### #@ ###@#", "#######@@#S#@##", "##@@ #### ##  #",
                "# @    # # ####", "###############" }));
        System.out.println(p.mimimumTime(new String[] { "#######", "#  ## #", "#@# #@#", "#@@ #@#", "##@ ###",
                "#@##@##", "#######", "##@##@#", "#S@@# #", "#@ ####", "###  @#", "# #E###", "#######" }));
    }
}
