package easiest100;

public class ChessPieces {
    public String howMany(int[] pieces) {

        int sum = 0, br = 0;
        for (int i = 0; i < pieces.length; i++)
            sum += pieces[i];

        for (int i = 1; i <= 100; i++) {
            boolean x = true;
            if (pieces[0] >= 8) {
                pieces[0] -= 8;
            } else {
                x = false;
            }
            if (pieces[1] >= 2) {
                pieces[1] -= 2;
            } else {
                x = false;
            }
            if (pieces[2] >= 2) {
                pieces[2] -= 2;
            } else {
                x = false;
            }
            if (pieces[3] >= 2) {
                pieces[3] -= 2;
            } else {
                x = false;
            }
            if (pieces[4] >= 1) {
                pieces[4]--;
            } else {
                x = false;
            }
            if (pieces[5] >= 1) {
                pieces[5]--;
            } else {
                x = false;
            }
            if (pieces[6] >= 8) {
                pieces[6] -= 8;
            } else {
                x = false;
            }
            if (pieces[7] >= 2) {
                pieces[7] -= 2;
            } else {
                x = false;
            }
            if (pieces[8] >= 2) {
                pieces[8] -= 2;
            } else {
                x = false;
            }
            if (pieces[9] >= 2) {
                pieces[9] -= 2;
            } else {
                x = false;
            }
            if (pieces[10] >= 1) {
                pieces[10]--;
            } else {
                x = false;
            }
            if (pieces[11] >= 1) {
                pieces[11]--;
            } else {
                x = false;
            }
            if (x) {
                sum -= 32;
                br = i;
            }
        }

        return br + "," + sum;
    }
}
