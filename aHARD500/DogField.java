package aHARD500;

public class DogField {
    public String escape(int fieldSize, int personX, int personY, int[] dogX, int[] dogY) {

        double ans = 1e9;

        int left = 0;
        for (double r = 0; r <= fieldSize; r += 0.000005) {
            double per_dist = (personX * personX) + (personY - r) * (personY - r);
            boolean ok = true;
            for (int i = 0; i < dogX.length; i++) {
                double dist = (dogX[i] * dogX[i]) + (dogY[i] - r) * (dogY[i] - r);
                if (Math.abs(dist - per_dist) < 0.0000001)
                    continue;
                if (dist < per_dist) {
                    ok = false;
                    break;
                }
            }
            if (ok)
                ans = Math.min(ans, per_dist);
        }

        left = fieldSize;
        for (double r = 0; r <= fieldSize; r += 0.000005) {
            double per_dist = ((personX - left) * (personX - left)) + (personY - r) * (personY - r);
            boolean ok = true;
            for (int i = 0; i < dogX.length; i++) {
                double dist = ((dogX[i] - left) * (dogX[i] - left)) + (dogY[i] - r) * (dogY[i] - r);
                if (Math.abs(dist - per_dist) < 0.0000001)
                    continue;
                if (dist < per_dist) {
                    ok = false;
                    break;
                }
            }
            if (ok)
                ans = Math.min(ans, per_dist);
        }

        int right = 0;
        for (double l = 0; l <= fieldSize; l += 0.000005) {
            double per_dist = ((personX - l) * (personX - l)) + (personY) * (personY);
            boolean ok = true;
            for (int i = 0; i < dogX.length; i++) {
                double dist = ((dogX[i] - l) * (dogX[i] - l)) + (dogY[i]) * (dogY[i]);
                if (Math.abs(dist - per_dist) < 0.0000001)
                    continue;
                if (dist < per_dist) {
                    ok = false;
                    break;
                }
            }
            if (ok)
                ans = Math.min(ans, per_dist);
        }

        right = fieldSize;

        for (double l = 0; l <= fieldSize; l += 0.000005) {
            double per_dist = ((personX - l) * (personX - l)) + (personY - right) * (personY - right);
            boolean ok = true;
            for (int i = 0; i < dogX.length; i++) {
                double dist = ((dogX[i] - l) * (dogX[i] - l)) + (dogY[i] - right) * (dogY[i] - right);
                if (Math.abs(dist - per_dist) < 0.0000001)
                    continue;
                if (dist < per_dist) {
                    ok = false;
                    break;
                }
            }
            if (ok)
                ans = Math.min(ans, per_dist);
        }

        if (1e9 - ans <= 0.00001)
            return "no escape";
        ans = Math.sqrt(ans);

        return String.format("%.6f", ans);
    }

    public static void main(String[] args) {
        DogField d = new DogField();
        System.out.println(d.escape(30, 15, 10, new int[] { 10, 20 }, new int[] { 10, 10 }));
        System.out.println(d.escape(100,
                72,
                72,
                new int[] { 73, 41, 97, 28, 59, 61, 93, 15, 79, 96, 28, 45, 84, 32, 8, 25, 54, 6, 10, 13, 49, 60, 26,
                        52, 34, 35, 75, 72, 34, 16, 8, 54, 77, 46, 100, 45, 78, 72, 9, 41, 23, 93, 23, 58, 61 },
                new int[] { 34, 52, 18, 33, 80, 66, 40, 20, 5, 4, 89, 87, 23, 40, 31, 9, 67, 30, 30, 60, 5, 46, 90, 77,
                        83, 25, 22, 30, 36, 49, 19, 64, 67, 65, 21, 93, 52, 54, 82, 97, 64, 21, 80, 93, 55 }));
    }
}
