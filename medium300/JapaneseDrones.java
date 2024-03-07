package medium300;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class JapaneseDrones {

    class Item {
        int x, y;

        public Item(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public String calculateDroneCapacity(int n, int m, int[] a, int[] b) {

        List<Item> list = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            list.add(new Item(a[i], b[i]));
        }

        list.sort(Comparator.comparing(Item::getY).reversed());

        long res = 0;

        for (int i = 0; i < m; i++) {
            int take = Math.min(n, list.get(i).x);
            res += ((long) take * ((long) list.get(i).y));
            n -= take;
        }

        return String.valueOf(res);
    }

    public static void main(String[] args) {
        JapaneseDrones j = new JapaneseDrones();
        System.out.println(j.calculateDroneCapacity(11, 4, new int[] { 27, 42, 25, 32 }, new int[] { 3, 10, 4, 6 }));
    }
}
