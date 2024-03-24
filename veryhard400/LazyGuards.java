package veryhard400;

import java.util.LinkedList;
import java.util.Queue;

public class LazyGuards {

    class Guard{
        int x, y, side;

        public Guard(int x, int y, int side) {
            this.x = x;
            this.y = y;
            this.side = side;
        }
    }

    class Item{
        int x, y, step;

        public Item(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }

    boolean ok(Item curr, Guard []guards, String []maze){

        int n = maze.length, m = maze[0].length();

        for (int t=0; t< guards.length; t++){
            int x = guards[t].x, y = guards[t].y, side = guards[t].side;

            if (side == 1){
                for (int i=y; i<m; i++)
                    if (curr.x == x && curr.y == i) return false;
            }else if (side == 2){
                for (int i=x; i<n; i++)
                    if (curr.x == i && curr.y == y) return false;
            }else if (side == 3){
                for (int i=0; i<=y; i++)
                    if (curr.x == x && curr.y == i) return false;
            }else if (side == 4){
                for (int i=0; i<=x; i++)
                    if (curr.x == i && curr.y == y) return false;
            }
        }

        return true;
    }

    Guard []swapGuards(Guard []guards){
        Guard []ret = new Guard[guards.length];

        for (int i=0; i<guards.length; i++){
            ret[i] = new Guard(guards[i].x, guards[i].y, (guards[i].side%4)+1);
        }
        return ret;
    }

    public int numberOfSteps(String[] maze) {


        int sx = 0, sy = 0, ex = 0, ey = 0, g = 0;
        int n = maze.length, m = maze[0].length();
        int num = 0;
        for (int i=0; i<n; i++)
            for (int j=0; j<m; j++)
                if (Character.isDigit(maze[i].charAt(j))) num++;

        Guard []guards = new Guard[num];

        for (int i=0; i<n; i++)
            for (int j=0; j<m; j++){
                if (maze[i].charAt(j) == 'S'){
                    sx = i;
                    sy = j;
                }else if (maze[i].charAt(j) == 'E'){
                    ex = i;
                    ey = j;
                }else if (Character.isDigit(maze[i].charAt(j))){
                    guards[g++] = new Guard(i, j, Integer.parseInt(String.valueOf(maze[i].charAt(j))));
                }
            }

        int [][][]dp = new int[51][51][5];
        for (int i=0; i<51; i++)
            for (int j=0; j<51; j++)
                for (int t=0; t<5; t++) dp[i][j][t] = 1<<25;

        dp[sx][sy][1] = 0;

        Queue<Item> q = new LinkedList<>();

        q.add(new Item(sx, sy, 1));

        int []dx = {-1, 1, 0 , 0};
        int []dy = {0, 0, -1, 1};

        int lastDist = 0;

        //for (Guard abc : guards) System.out.println(abc.x + " " + abc.y + " " + abc.side);

        while(!q.isEmpty()) {

            Item curr = q.remove();

            //System.out.println("State ");
            //System.out.print(curr.x + " " + curr.y + " " + curr.step + " guards: ");
            //for (Guard abc : guards) System.out.println(abc.x + " " + abc.y + " " + abc.side);


            if (lastDist < dp[curr.x][curr.y][curr.step]){
                lastDist = dp[curr.x][curr.y][curr.step];
                guards = swapGuards(guards);



            }

            if (!ok(curr, guards, maze)){
                //System.out.println("not ok " + curr.x + " " + curr.y + " " + guards[0].side + " " + dp[curr.x][curr.y][curr.step]);
                continue;
            }

            if (curr.x == ex && curr.y == ey) return dp[curr.x][curr.y][curr.step];

            for (int move=0; move<4; move++){
                int nx = curr.x+dx[move], ny = curr.y + dy[move], ns = (curr.step%4)+1;
                Item item = new Item(nx, ny, ns);
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && maze[nx].charAt(ny) != '#'
                        && !Character.isDigit(maze[nx].charAt(ny))){

                    if (dp[nx][ny][ns] > dp[curr.x][curr.y][curr.step] + 1){
                        dp[nx][ny][ns] = dp[curr.x][curr.y][curr.step] + 1;
                        q.add(item);
                    }
                }
            }
        }

        return -1;
    }

   public static void main(String[] args) {
       LazyGuards l = new LazyGuards();
       System.out.println(l.numberOfSteps(new String[]{"######", "#...S#", "##2.3#", "#....#", "#.1.3#", "#..E.#", "######"}));
       System.out.println(l.numberOfSteps(new String[]{"####", "#S4#", "#.E#", "####"}));
   }
}
