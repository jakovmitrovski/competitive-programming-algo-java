package easy200;

public class CountingPaths {

    static int[][] multiply(int a[][], int b[][], int n) {
        int[][] mul = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mul[i][j] = 0;
                for (int k = 0; k < n; k++)
                    mul[i][j] += a[i][k]
                            * b[k][j];
            }
        }

        return mul;
    }

    static int[][] power(int mx[][], int n, int p) {
        if (p == 1)
            return mx;
        if (p % 2 == 0) {
            int[][] mat = power(mx, n, p / 2);
            return multiply(mat, mat, n);
        } else {
            int[][] mat = power(mx, n, p - 1);
            return multiply(mx, mat, n);
        }
    }

    public int countPaths(int n, String[] adjMatrix) {

        int[][] mat = new int[n][n];

        for (int i = 0; i < adjMatrix.length; i++) {
            String[] in = adjMatrix[i].split(":");
            int from = Integer.parseInt(in[0]);

            String[] toos = in[1].split(";");
            for (int j = 0; j < toos.length; j++) {
                int to = Integer.parseInt(toos[j]);
                mat[from][to] = 1;
            }
        }
        int sum = 0;
        for (int k = 1; k <= 50; k++) {
            int[][] nmat = power(mat, n, k);
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (nmat[i][j] != 0)
                        sum++;
        }

        return sum;
    }

    public static void main(String[] args) {
        CountingPaths c = new CountingPaths();
        System.out.println(c.countPaths(18, new String[] { "0:1;3;5;11;12", "1:2;8", "2:9;10", "3:4;7", "4:6", "5:17",
                "6:13", "11:14", "13:15", "14:16" }));
    }
}
