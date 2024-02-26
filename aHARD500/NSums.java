package aHARD500;

public class NSums {

    int n;
    int mod = 1000007;

    long[][] mul(long[][] a, long[][] b) {
        long[][] ret = new long[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    ret[i][j] += a[i][k] * b[k][j];
                    ret[i][j] %= mod;
                }
            }
        return ret;
    }

    long[][] pow(long[][] a, int n) {
        if (n == 1)
            return a;

        if (n % 2 == 0) {
            long[][] nmx = pow(a, n / 2);
            return mul(nmx, nmx);
        } else {
            long[][] nmx = pow(a, n - 1);
            return mul(nmx, a);
        }
    }

    public String sum(int[] a, int N) {
        n = a.length;

        long[][] matrix = new long[n][n];

        for (int i = 0; i < n; i++) {
            matrix[i][i] = 1;
            matrix[i][(i + 1) % n] = 1;
        }

        matrix = pow(matrix, N);
        long[] ret = new long[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ret[i] += matrix[i][j] * a[j];
                ret[i] %= mod;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n - 1; i++)
            sb.append(ret[i]).append(",");
        sb.append(ret[n - 1]);

        return sb.toString();
    }

    public static void main(String[] args) {
        NSums n = new NSums();
        System.out.println(n.sum(new int[] { 15, 33, 75, 19, 61, 18, 3, 50, 21, 67, 37, 63, 94, 31, 76, 96, 77, 32, 5,
                93, 25, 20, 38, 63, 33, 8, 17, 62, 65, 56, 84, 12, 50, 80, 21, 80, 43, 53, 73, 90, 5, 29, 63, 52, 1, 35,
                12, 78, 49, 46 }, 1710116208));
    }
}
