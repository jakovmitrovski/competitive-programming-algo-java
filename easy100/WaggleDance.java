package easy100;

import static java.lang.Math.*;

public class WaggleDance {
    public int getDistance(int[] angles) {

        final double times = Math.PI / 180.0;

        double x = 0.0, y = 0.0;

        for (int i = 0; i < angles.length; i++) {
            x += cos(angles[i] * times);
            y += sin(angles[i] * times);
        }
        double z = Math.sqrt((x * x + y * y));
        z = Math.round(z * 1000);
        return (int) z;
    }

    public static void main(String[] args) {
        WaggleDance w = new WaggleDance();
        System.out.println(w.getDistance(new int[] { 357, 107 }));
        System.out.println(w.getDistance(new int[] { 75, 224, 231, 346, 328 }));
    }
}
