package veryhard400;

public class Dominoes {
    public int findMin(int N, int M, int[] count) {

        int[][] mat = new int[N][2 * M];

        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 2 * M; j += 2) {
                    if (count[0] > 0) {
                        mat[i][j] = 1;
                        mat[i][j + 1] = 1;
                        count[0]--;
                    } else if (count[1] > 0) {
                        mat[i][j] = 0;
                        mat[i][j + 1] = 1;
                        count[1]--;
                    } else {
                        mat[i][j] = 0;
                        mat[i][j + 1] = 0;
                        count[2]--;
                    }
                }
            } else {
                for (int j = 2 * M - 1; j > 0; j -= 2) {
                    if (count[0] > 0) {
                        mat[i][j] = 1;
                        mat[i][j - 1] = 1;
                        count[0]--;
                    } else if (count[1] > 0) {
                        mat[i][j] = 0;
                        mat[i][j - 1] = 1;
                        count[1]--;
                    } else {
                        mat[i][j] = 0;
                        mat[i][j - 1] = 0;
                        count[2]--;
                    }
                }
            }
        }

        int res = -1 << 20;
        for (int j = 0; j < 2 * M; j++) {
            int sum = 0;
            for (int i = 0; i < N; i++)
                sum += mat[i][j];
            res = Math.max(sum, res);
        }
        return res;
    }

    public static void main(String[] args) {
        Dominoes d = new Dominoes();
        System.out.println(d.findMin(8,
                9,
                new int[] { 26, 23, 23 }));
    }

}
