package veryhard400;

import java.util.*;

public class KillingZombies {
    char turnClockwise(char c) {
        if (c == 'N')
            return 'E';
        else if (c == 'E')
            return 'S';
        else if (c == 'S')
            return 'W';
        else if (c == 'W')
            return 'N';

        return c;
    }

    class Zombie {
        int id;
        int x, y;
        char direction;

        public Zombie(int id, int x, int y, char direction) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Zombie zombie = (Zombie) o;
            return x == zombie.x && y == zombie.y && direction == zombie.direction;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, direction);
        }
    }

    class State {
        int x, y;
        List<Zombie> zombies;
        int mask;

        public State(int x, int y, List<Zombie> zombies, int mask) {
            this.x = x;
            this.y = y;
            this.zombies = zombies;
            this.mask = mask;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            State state = (State) o;
            return x == state.x && y == state.y && Objects.equals(zombies, state.zombies);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, zombies);
        }
    }

    Zombie moveZombie(Zombie z, int n, int m, String[] field) {
        if (z.direction == 'N') {
            if (field[z.x - 1].charAt(z.y) != '#')
                return new Zombie(z.id, z.x - 1, z.y, z.direction);
            else
                return new Zombie(z.id, z.x, z.y, turnClockwise(z.direction));
        } else if (z.direction == 'S') {
            if (field[z.x + 1].charAt(z.y) != '#')
                return new Zombie(z.id, z.x + 1, z.y, z.direction);
            else
                return new Zombie(z.id, z.x, z.y, turnClockwise(z.direction));
        } else if (z.direction == 'W') {
            if (field[z.x].charAt(z.y - 1) != '#')
                return new Zombie(z.id, z.x, z.y - 1, z.direction);
            else
                return new Zombie(z.id, z.x, z.y, turnClockwise(z.direction));
        } else if (z.direction == 'E') {
            if (field[z.x].charAt(z.y + 1) != '#')
                return new Zombie(z.id, z.x, z.y + 1, z.direction);
            else
                return new Zombie(z.id, z.x, z.y, turnClockwise(z.direction));
        }

        return new Zombie(0, 0, 0, 'x');
    }

    public int timeToKill(String[] field) {

        int n = field.length, m = field[0].length();
        int sx = 0, sy = 0;
        int cnt = 0;
        List<Zombie> zombies = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (field[i].charAt(j) == 'H') {
                    sx = i;
                    sy = j;
                }
                if (field[i].charAt(j) == 'N' || field[i].charAt(j) == 'W' || field[i].charAt(j) == 'S'
                        || field[i].charAt(j) == 'E') {
                    zombies.add(new Zombie(cnt++, i, j, field[i].charAt(j)));
                }
            }
        }

        // Map<State, Boolean> visited = new HashMap<>();
        // Map<State, Integer> dp = new HashMap<>();

        int[][][] dp = new int[21][21][1 << 12];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                for (int k = 0; k < 1 << cnt; k++)
                    dp[i][j][k] = 1 << 25;

        Queue<State> q = new LinkedList<>();

        State start = new State(sx, sy, zombies, 0);
        dp[sx][sy][0] = 0;
        q.add(start);

        int[] dx = { -1, 1, 0, 0, 0 };
        int[] dy = { 0, 0, -1, 1, 0 };

        while (!q.isEmpty()) {
            State curr = q.remove();
            // System.out.println(curr.x + " " + curr.y + " " +
            // Integer.toBinaryString(curr.mask) + " " + " Zombies: ");
            // for (int i=0; i< curr.zombies.size(); i++)
            // System.out.println(curr.zombies.get(i).x + " " + curr.zombies.get(i).y + " "
            // + curr.zombies.get(i).direction);
            if (dp[curr.x][curr.y][curr.mask] > 100)
                continue;
            if (curr.mask == ((1 << cnt) - 1)) {
                return dp[curr.x][curr.y][curr.mask];
            }

            List<Zombie> movedZombies = new ArrayList<>();

            for (Zombie z : curr.zombies)
                movedZombies.add(moveZombie(z, n, m, field));

            for (int move = 0; move < 5; move++) {
                int nx = curr.x + dx[move], ny = curr.y + dy[move];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && field[nx].charAt(ny) != '#') {

                    List<Zombie> samePos = new ArrayList<>();
                    for (Zombie zombie : movedZombies) {
                        if ((nx == zombie.x && ny == zombie.y))
                            samePos.add(zombie);
                    }

                    if (dp[nx][ny][curr.mask] > dp[curr.x][curr.y][curr.mask] + 1) {
                        State next = new State(nx, ny, movedZombies, curr.mask);
                        dp[nx][ny][curr.mask] = dp[curr.x][curr.y][curr.mask] + 1;
                        q.add(next);
                    }

                    if (samePos.size() > 0) {
                        for (Zombie samePo : samePos) {
                            List<Zombie> nextZombies = new ArrayList<>(movedZombies);
                            nextZombies.remove(samePo);
                            int nmask = (curr.mask | (1 << samePo.id));
                            State next = new State(nx, ny, nextZombies, nmask);
                            if (dp[nx][ny][nmask] > dp[curr.x][curr.y][curr.mask] + 1) {
                                dp[nx][ny][nmask] = dp[curr.x][curr.y][curr.mask] + 1;
                                q.add(next);
                            }
                        }
                    }

                }
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        KillingZombies k = new KillingZombies();
        System.out.println(k.timeToKill(new String[] { "#######", "#H  E #", "#######" }));
        System.out.println(k.timeToKill(
                new String[] { "#######", "# # # #", "##  N##", "#W    #", "#H#   #", "#N ## #", "#######" }));
        System.out.println(k.timeToKill(
                new String[] { "#######", "## # ##", "#   E##", "# S # #", "# E S #", "#H   ##", "#######" }));
    }
}
