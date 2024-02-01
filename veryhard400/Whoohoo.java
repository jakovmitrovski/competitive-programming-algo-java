package veryhard400;

import java.util.LinkedList;
import java.util.Queue;

public class Whoohoo {

    class State{
        int x, y, steps;

        public State(int x, int y, int steps) {
            this.x = x;
            this.y = y;
            this.steps = steps;
        }
    }

    public int getCount(String[] map, int maxTime) {

        Queue<State> q = new LinkedList<>();

        State start = new State(0, 0, maxTime);
        q.add(start);
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int n = map.length, m = map[0].length();
        int[][][] cntwohoos = new int[n + 2][m + 2][maxTime + 1];

        for (int i = 0; i <= n; i++)
            for (int j = 0; j <= m; j++)
                for (int k = 0; k <= maxTime; k++) cntwohoos[i][j][k] = -1;

        cntwohoos[0][0][maxTime] = 0;

        while (!q.isEmpty()) {
            State curr = q.remove();
            //System.out.println(curr.x + " " + curr.y + " " + curr.steps);

            if (curr.steps > 0) {
                int x = curr.x, y = curr.y;
                if (x > 0 && map[x].charAt(y) == 'U' && cntwohoos[x - 1][y][curr.steps - 1] < cntwohoos[x][y][curr.steps] + 1) {
                    cntwohoos[x - 1][y][curr.steps - 1] = cntwohoos[x][y][curr.steps] + 1;
                    q.add(new State(x - 1, y, curr.steps - 1));
                }
                if (x < n - 1 && map[x].charAt(y) == 'D' && cntwohoos[x + 1][y][curr.steps - 1] < cntwohoos[x][y][curr.steps] + 1) {
                    cntwohoos[x + 1][y][curr.steps - 1] = cntwohoos[x][y][curr.steps] + 1;
                    q.add(new State(x + 1, y, curr.steps - 1));
                }
                if (y > 0 && map[x].charAt(y) == 'L' && cntwohoos[x][y - 1][curr.steps - 1] < cntwohoos[x][y][curr.steps] + 1) {
                    cntwohoos[x][y - 1][curr.steps - 1] = cntwohoos[x][y][curr.steps] + 1;
                    q.add(new State(x, y - 1, curr.steps - 1));
                }
                if (y < m - 1 && map[x].charAt(y) == 'R' && cntwohoos[x][y + 1][curr.steps - 1] < cntwohoos[x][y][curr.steps] + 1) {
                    cntwohoos[x][y + 1][curr.steps - 1] = cntwohoos[x][y][curr.steps] + 1;
                    q.add(new State(x, y + 1, curr.steps - 1));
                }
                if (map[x].charAt(y) == '.') {
                    for (int move = 0; move < 4; move++) {
                        int nx = curr.x + dx[move], ny = curr.y + dy[move];
                        if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                            if (cntwohoos[nx][ny][curr.steps - 1] < cntwohoos[curr.x][curr.y][curr.steps]) {
                                cntwohoos[nx][ny][curr.steps - 1] = cntwohoos[curr.x][curr.y][curr.steps];
                                q.add(new State(nx, ny, curr.steps - 1));

                            }
                        }
                    }
                }
            }
        }
        int res = -1;
        for (int i = 0; i < maxTime; i++) res = Math.max(res, cntwohoos[n - 1][m - 1][i]);

        return res;
    }

   public static void main(String[] args) {
       Whoohoo w = new Whoohoo();
       System.out.println(w.getCount(new String[]{"...", ".D.", "..."}, 16));
       System.out.println(w.getCount(new String[]{"....L", "....R", "UDDDL", "...R.", "L...."
       }, 19));
   }
}