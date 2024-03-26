package medium300;

public class Squares {

    int dist(int x1, int x2, int y1, int y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }

    public int findMax(int[] x, int[] y) {

        int res = 0;

        for (int i = 0; i < x.length; i++)
            for (int j = 0; j < x.length; j++)
                for (int k = 0; k < x.length; k++)
                    for (int t = 0; t < x.length; t++) {
                        // first pt
                        if (i == j || i == k || i == t || j == k || j == t || k == t)
                            continue;
                        int a1 = x[i], b1 = y[i];
                        int a2 = x[j], b2 = y[j];
                        int a3 = x[k], b3 = y[k];
                        int a4 = x[t], b4 = y[t];

                        int d12 = dist(a1, a2, b1, b2);
                        int d13 = dist(a1, a3, b1, b3);
                        int d14 = dist(a1, a4, b1, b4);
                        int d23 = dist(a2, a3, b2, b3);
                        int d24 = dist(a2, a4, b2, b4);
                        int d34 = dist(a3, a4, b3, b4);

                        if (d12 == d13 && d13 == d14)
                            continue;
                        if (d12 == 0 || d13 == 0 || d14 == 0 || d23 == 0 || d24 == 0 || d34 == 0)
                            continue;

                        if (d12 == d13) {
                            if (2 * d12 == d14 && d12 == d24 && d24 == d34 && d14 == d23)
                                res++;
                        }
                    }
        return res / 8;
    }
}
