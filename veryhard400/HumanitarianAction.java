package veryhard400;

import java.util.LinkedList;
import java.util.Queue;

public class HumanitarianAction {

    class Item {
        int first, second, dist;

        public Item(int first, int second, int dist) {
            this.first = first;
            this.second = second;
            this.dist = dist;
        }
    }

    public String taken(String[] map, String dropouts) {

        String[] drops = dropouts.split(";");

        StringBuilder ret = new StringBuilder();
        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        for (String drop : drops) {
            String[] momdrop = drop.split(",");
            int ycoord = Integer.parseInt(momdrop[0]), xcoord = Integer.parseInt(momdrop[1]);
            if (map[xcoord].charAt(ycoord) == '#') {
                ret.append("*");
                continue;
            }
            Integer numAccess = 0, min = 1 << 20;
            Character lastAccess = 'z';
            boolean[][] visited = new boolean[map.length + 1][map[0].length() + 1];
            boolean l = false;
            Queue<Item> q = new LinkedList<>();
            q.add(new Item(xcoord, ycoord, 0));
            visited[xcoord][ycoord] = true;

            while (!q.isEmpty()) {
                Item curr = q.remove();

                if (Character.isDigit(map[curr.first].charAt(curr.second))) {
                    if (lastAccess == 'z' || min >= curr.dist) {
                        lastAccess = map[curr.first].charAt(curr.second);
                        numAccess++;
                        min = curr.dist;
                    }
                }
                if (numAccess > 1) {
                    ret.append("?");
                    l = true;
                    break;
                }

                for (int move = 0; move < 4; move++) {
                    int nx = curr.first + dx[move], ny = curr.second + dy[move];
                    if (nx >= 0 && nx < map.length && ny >= 0 && ny < map[0].length() && map[nx].charAt(ny) != '#'
                            && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        q.add(new Item(nx, ny, curr.dist + 1));
                    }
                }
            }

            if (l)
                continue;
            if (lastAccess == 'z') {
                ret.append("*");
                continue;
            }
            ret.append(lastAccess);
        }

        return ret.toString();
    }
}
