package easy200;

public class Carambole {
    public String finalPosition(int dx, int dy, int time) {

        long xx = (long) dx * time, yy = (long) dy * time;

        if ((xx / 3000) % 2 == 1) {
            xx = 3000 - (xx % 3000);
        } else
            xx = xx % 3000;

        if ((yy / 1500) % 2 == 1) {
            yy = 1500 - (yy % 1500);
        } else
            yy = yy % 1500;

        return xx + "," + yy;
    }

    public static void main(String[] args) {
        Carambole c = new Carambole();
        System.out.println(c.finalPosition(943658,
                108930,
                211273));
        System.out.println(c.finalPosition(27544,
                850878,
                777924));
        System.out.println(c.finalPosition(30, 30, 100));
    }
}
