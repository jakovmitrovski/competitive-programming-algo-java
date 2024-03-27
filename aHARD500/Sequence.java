package aHARD500;

public class Sequence {

    int mod = 1000007;

    long stp(int p) {
        long x = 1;
        for (int i = 0; i < p; i++)
            x *= 10;
        return x;
    }

    long[][] mul(long[][] a, long[][] b) {

        long[][] res = new long[a.length][a.length];

        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a.length; j++) {
                for (int k = 0; k < a.length; k++) {
                    res[i][j] += (a[i][k] % mod * b[k][j] % mod) % mod;
                    res[i][j] %= mod;
                }
            }

        return res;
    }

    long[][] pw(long[][] mx, long n) {

        if (n == 1)
            return mx;

        if (n % 2 == 0) {
            long[][] tmp = pw(mx, n / 2);
            return mul(tmp, tmp);
        } else {
            long[][] tmp = pw(mx, n - 1);
            return mul(mx, tmp);
        }
    }

    public int findElement(int k, int[] coefficients, int[] elements, int p) {

        long[][] mx = new long[k][k];

        for (int i = 0; i < k; i++)
            mx[0][i] = coefficients[k - i - 1];
        for (int i = 1; i < k; i++)
            mx[i][i - 1] = 1;

        if (p == 1 && k == 10)
            return elements[k - 1];

        long[][] nm = pw(mx, stp(p) - k);

        long[][] ret = new long[k][1];

        long[][] right = new long[k][1];
        for (int i = k - 1; i >= 0; i--)
            right[i][0] = elements[k - i - 1] % mod;

        for (int i = 0; i < k; i++)
            for (int j = 0; j < 1; j++)
                for (int t = 0; t < k; t++) {
                    ret[i][j] += nm[i][t] * right[t][j];
                    ret[i][j] %= mod;
                }

        // for (int i=0; i<k; i++) System.out.println(ret[i][0]);

        return (int) (ret[0][0] % mod);
    }

    public static void main(String[] args) {
        Sequence s = new Sequence();

        System.out.println(s.findElement(5, new int[] { 1, 1691, 2895, 8882, 7564 },
                new int[] { 6622, 1590, 1011, 5587, 2761 }, 10));
        System.out
                .println(s.findElement(4, new int[] { 1, 9593, 3903, 7484 }, new int[] { 6551, 9498, 4176, 3185 }, 1));
        System.out.println(s.findElement(3, new int[] { 1, 8794, 1797 }, new int[] { 9139, 582, 5845 }, 6));
        System.out.println(s.findElement(3, new int[] { 1, 8129, 4129 }, new int[] { 5516, 3948, 4933 }, 4));
        System.out.println(s.findElement(5, new int[] { 1, 2572, 1679, 6362, 1655 },
                new int[] { 6427, 1526, 4002, 6976, 1036 }, 8));
    }
}
