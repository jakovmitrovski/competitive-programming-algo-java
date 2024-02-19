package medium300;

public class CyclingRace {

    int res(int speed, int ind, int[] a) {

        if (ind == a.length)
            return 0;
        if (speed > a[ind] || speed <= 0)
            return -1 << 20;

        return speed + Math.max(res(speed + 5, ind + 1, a), res(speed - 5, ind + 1, a));
    }

    public int totalSpeed(int speed, int[] segments) {

        int x = res(speed, 0, segments);
        if (x < 0)
            return -1;
        return x;
    }

    public static void main(String[] args) {
        CyclingRace c = new CyclingRace();
        System.out.println(c.totalSpeed(3, new int[] { 17, 14, 23, 19, 6, 19, 45, 41, 22, 17, 17, 45, 39 }));
    }
}
