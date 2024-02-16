package veryhard400;

import java.util.HashSet;

public class SpikeMaze {

    public int mimimumTime(String[] maze, int[] horizontal, int[] vertical) {
        int n = maze.length;
        boolean spikes[][] = new boolean[n][n];
        boolean current_locations[][] = new boolean[n][n];
        boolean next_locations[][] = new boolean[n][n];
        current_locations[0][0] = true;
        for (int i = 0; i < n; ++i) {
            for (int k = 0; k < n; ++k) {
                spikes[i][k] = maze[i].charAt(k) == 'X';
            }
        }
        HashSet<Integer> hors = new HashSet<Integer>();
        HashSet<Integer> vers = new HashSet<Integer>();
        add(horizontal, hors);
        add(vertical, vers);
        int dx[] = { -1, 1, 0, 0, 0 };
        int dy[] = { 0, 0, -1, 1, 0 };

        // System.out.println();
        // pirntMatrix(spikes);
        for (int t = 1; t < 1000; ++t) {
            if (hors.contains(t))
                spikes = flipVer(spikes);
            if (vers.contains(t))
                spikes = flipHor(spikes);
            for (int i = 0; i < n; ++i) {
                for (int k = 0; k < n; ++k) {
                    if (spikes[i][k])
                        current_locations[i][k] = false;
                    if (!current_locations[i][k])
                        continue;
                    for (int w = 0; w < dx.length; ++w) {
                        int nx = i + dx[w];
                        int ny = k + dy[w];
                        if (check(n, nx, ny, spikes))
                            next_locations[nx][ny] = true;
                    }
                }
            }
            current_locations = next_locations;
            if (current_locations[n - 1][n - 1])
                return t;
            next_locations = new boolean[n][n];
            // System.out.println();
            // System.out.println(t);
            // pirntMatrix(spikes);
        }
        return -1;
    }

    private boolean[][] flipHor(boolean[][] spikes) {
        int n = spikes.length;
        boolean res[][] = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            for (int k = 0; k < n; ++k) {
                res[i][k] = spikes[n - i - 1][k];
            }
        }
        return res;
    }

    private boolean[][] flipVer(boolean[][] spikes) {
        int n = spikes.length;
        boolean res[][] = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            for (int k = 0; k < n; ++k) {
                res[i][k] = spikes[i][n - k - 1];
            }
        }
        return res;
    }

    private boolean check(int n, int nx, int ny, boolean[][] spikes) {
        return nx >= 0 && nx < n && ny >= 0 && ny < n && !spikes[nx][ny];
    }

    private void add(int[] horizontal, HashSet<Integer> hors) {
        for (int i = 0; i < horizontal.length; ++i) {
            hors.add(horizontal[i]);
        }
    }

}
