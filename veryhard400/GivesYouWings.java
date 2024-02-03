package veryhard400;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class GivesYouWings {

    class State{
        int x, y, mask;

        public State(int x, int y, int mask) {
            this.x = x;
            this.y = y;
            this.mask = mask;
        }
    }

    public int solve(String[] maze) {

        int n = maze.length, m = maze[0].length();

        Map<Integer, Integer> map = new HashMap<>();
        State s = new State(0, 0, (1<<8)-1), t = new State(0, 0, (1<<8)-1);
        int cnt = 0;
        for (int i=0; i<n; i++)
            for (int j=0; j<m; j++){
                if (maze[i].charAt(j) == 'S') s = new State(i, j, (1<<8)-1);
                else if (maze[i].charAt(j) == 'T') t = new State(i, j, (1<<8)-1);
                else if (maze[i].charAt(j) == '*'){
                    map.put(i*n+j, cnt);
                    cnt++;
                }
            }

        Queue<State> q = new LinkedList<>();
        q.add(s);
        int [][][]dp = new int[n+1][m+2][(1<<9)+1];

        for (int i=0; i<n; i++)
            for (int j=0; j<m; j++)
                for (int k=0; k<(1<<9)+1; k++) dp[i][j][k] = 1<<30;

        dp[s.x][s.y][s.mask] = 0;

        int []dx = {-1, 1, 0, 0};
        int []dy = {0, 0, -1, 1};

        while(!q.isEmpty()){

            State curr = q.remove();

            //System.out.println(curr.x + " " + curr.y + " " + Integer.toBinaryString(curr.mask) + " " + dp[curr.x][curr.y][curr.mask]);


            //if (curr.x == t.x && curr.y == t.y) return dp[curr.x][curr.y][curr.mask];

            for (int move=0; move<4; move++){
                int nx = curr.x+dx[move], ny = curr.y+dy[move];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && maze[nx].charAt(ny) != '#'){
                    //System.out.println("Prethodnik e "+ curr.x + " " + curr.y  + " sl e " + nx + " " + ny);
                    int stepweight = (1<<(Integer.bitCount(curr.mask)));
                    if (maze[nx].charAt(ny) == '*' && (curr.mask & (1<<(map.get(nx*n+ny)))) > 0){
                        int redbull = map.get(nx*n+ny);
                        //System.out.println(redbull);
                        int nmask = curr.mask ^ (1<<redbull);

                        if (dp[nx][ny][nmask] > dp[curr.x][curr.y][curr.mask] + stepweight) {
                            dp[nx][ny][nmask] = dp[curr.x][curr.y][curr.mask] + stepweight;
                            q.add(new State(nx, ny, nmask));
                        }
                    }else{
                        if (dp[nx][ny][curr.mask] > dp[curr.x][curr.y][curr.mask] + stepweight) {
                            dp[nx][ny][curr.mask] = dp[curr.x][curr.y][curr.mask] + stepweight;
                            q.add(new State(nx, ny, curr.mask));
                        }
                    }
                }
            }

        }
        int rez = 1<<30;
        for (int i=0; i<1<<9; i++) rez = Math.min(rez, dp[t.x][t.y][i]);

        return rez == 1<<30? -1 : rez;

        //return dp[t.x][t.y][-1;
    }
//
   public static void main(String[] args) {
       GivesYouWings g = new GivesYouWings();
       System.out.println(g.solve(new String[]{"********......S..............T"}));
   }
}
