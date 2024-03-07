package easy200;

public class DiagonalCipher {
    public String encode(String message) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < message.length(); i++)
            sb.append(message.charAt(i));

        if (message.length() == 1)
            return message;

        boolean found = false;
        int[] powers = new int[11];
        for (int i = 1; i <= 10; i++) {
            powers[i] = i * i;
        }
        int i = 1, sz = 0;
        while (!found) {
            if (i > 10)
                break;
            if (message.length() <= powers[i]) {
                sz = i;
                found = true;
            }
            i++;
        }

        while (sb.toString().length() < sz * sz) {
            sb.append('*');
        }

        char[][] mat = new char[sz][sz];

        for (i = 0; i < sz; i++)
            for (int j = 0; j < sz; j++)
                mat[i][j] = sb.charAt(i * sz + j);

        StringBuilder res = new StringBuilder();

        for (int col = sz - 1; col >= 0; col--) {
            int row = sz - 1, cl = col;
            while (row >= 0 && row < sz && cl >= 0 && cl < sz) {
                res.append(mat[row][cl]);
                cl++;
                row--;
            }
        }

        for (int row = sz - 2; row >= 0; row--) {
            int rw = row, col = 0;
            while (rw >= 0 && rw < sz && col >= 0 && col < sz) {
                res.append(mat[rw][col]);
                col++;
                rw--;
            }
        }

        return res.toString();
    }

    public static void main(String[] args) {
        DiagonalCipher d = new DiagonalCipher();
        System.out.println(d.encode("replhiagcnadoi"));
    }
}
