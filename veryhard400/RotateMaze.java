package veryhard400;

import java.util.LinkedList;
import java.util.Queue;

public class RotateMaze {

    class State {
        boolean[][] obj;
        int x, y, rot;

        public State(boolean[][] obj, int x, int y, int rot) {
            this.obj = obj;
            this.x = x;
            this.y = y;
            this.rot = rot;
        }
    }

    public boolean[][] rotate(boolean[][] arr) {
        boolean[][] ret = new boolean[3][3];

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                ret[j][2 - i] = arr[i][j];
        return ret;
    }

    public boolean isValid(boolean[][] obj, int nx, int ny, String[] map) {
        return map[nx].charAt(ny) != '#'
                &&
                (!obj[0][0] || map[nx - 1].charAt(ny - 1) != '#')
                &&
                (!obj[0][1] || map[nx - 1].charAt(ny) != '#')
                &&
                (!obj[0][2] || map[nx - 1].charAt(ny + 1) != '#')
                &&
                (!obj[1][0] || map[nx].charAt(ny - 1) != '#')
                &&
                (!obj[1][2] || map[nx].charAt(ny + 1) != '#')
                &&
                (!obj[2][0] || map[nx + 1].charAt(ny - 1) != '#')
                &&
                (!obj[2][1] || map[nx + 1].charAt(ny) != '#')
                &&
                (!obj[2][2] || map[nx + 1].charAt(ny + 1) != '#');
    }

    public int find(String[] map) {

        int sx = 0, sy = 0;
        boolean[][][] object = new boolean[4][3][3];

        int[] dx = { -1, 1, 0, 0, -1, -1, 1, 1 };
        int[] dy = { 0, 0, -1, 1, -1, 1, -1, 1 };

        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[0].length(); j++) {
                if (map[i].charAt(j) == 'O') {
                    sx = i;
                    sy = j;

                    object[0][1][1] = true;
                    if (map[i - 1].charAt(j - 1) == '*')
                        object[0][0][0] = true;
                    if (map[i - 1].charAt(j) == '*')
                        object[0][0][1] = true;
                    if (map[i - 1].charAt(j + 1) == '*')
                        object[0][0][2] = true;
                    if (map[i].charAt(j - 1) == '*')
                        object[0][1][0] = true;
                    if (map[i].charAt(j + 1) == '*')
                        object[0][1][2] = true;
                    if (map[i + 1].charAt(j - 1) == '*')
                        object[0][2][0] = true;
                    if (map[i + 1].charAt(j) == '*')
                        object[0][2][1] = true;
                    if (map[i + 1].charAt(j + 1) == '*')
                        object[0][2][2] = true;
                }
            }

        for (int i = 1; i < 4; i++)
            object[i] = rotate(object[i - 1]);

        // for (int k=0; k<4; k++) {
        // for (int i = 0; i < 3; i++) {
        // System.out.println();
        // for (int j = 0; j < 3; j++) System.out.print(object[k][i][j] + " ");
        // }
        // System.out.println();
        // }

        State start = new State(object[0], sx, sy, 0);
        Queue<State> q = new LinkedList<>();
        q.add(start);

        int[][][] dp = new int[4][21][21];

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 21; j++)
                for (int k = 0; k < 21; k++)
                    dp[i][j][k] = 1 << 20;

        dp[0][sx][sy] = 0;

        while (!q.isEmpty()) {
            State curr = q.remove();

            // check if we have reached the finish
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    if (curr.obj[i][j] && map[curr.x + (i - 1)].charAt(curr.y + (j - 1)) == 'X')
                        return dp[curr.rot][curr.x][curr.y];

            // try to move normally
            for (int move = 0; move < 4; move++) {
                int nx = curr.x + dx[move], ny = curr.y + dy[move];

                if (isValid(curr.obj, nx, ny, map) &&
                        dp[curr.rot][nx][ny] > dp[curr.rot][curr.x][curr.y] + 1) {
                    dp[curr.rot][nx][ny] = dp[curr.rot][curr.x][curr.y] + 1;
                    q.add(new State(curr.obj, nx, ny, curr.rot));
                }
            }

            // rotate right
            int rotRight = (curr.rot + 1) % 4;
            boolean[][] rro = object[rotRight];

            rotationMove(map, (Queue<State>) q, dp, curr, rro, rotRight);

            // rotate left
            int rotLeft = curr.rot == 0 ? 3 : (curr.rot - 1);
            rro = object[rotLeft];

            rotationMove(map, (Queue<State>) q, dp, curr, rro, rotLeft);

        }

        return -1;
    }

    private void rotationMove(String[] map, Queue<State> q, int[][][] dp, State curr, boolean[][] rro, int rotLeft) {
        if (isValid(rro, curr.x, curr.y, map) && dp[rotLeft][curr.x][curr.y] > dp[curr.rot][curr.x][curr.y] + 1) {
            dp[rotLeft][curr.x][curr.y] = dp[curr.rot][curr.x][curr.y] + 1;
            q.add(new State(rro, curr.x, curr.y, rotLeft));
        }
    }

    public static void main(String[] args) {
        RotateMaze r = new RotateMaze();
        System.out.println(r.find(new String[] { "######", "##  X#", "#  ###", "## O*#", "######" }));
        System.out.println(r.find(new String[] { "#####", "#  X#", "# ###", "# O*#", "#####" }));
        System.out.println(r.find(new String[] { "##########", "#    ##  #", "# ##    ##", "#O*    X #", "# *  #   #",
                "#     # ##", "#    #   #", "##########" }));
    }
}
