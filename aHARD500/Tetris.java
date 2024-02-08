package aHARD500;

public class Tetris {

    boolean[][][] one = new boolean[4][3][3];
    boolean[][][] two = new boolean[4][3][3];
    boolean[][][] three = new boolean[4][3][3];

    /*
     * Type 1:
     * ##
     * ##
     * 
     * Type 2:
     * #
     * ###
     * 
     * Type 3:
     * ###
     * #
     */

    public boolean[][] rotate(boolean[][] arr) {
        boolean[][] ret = new boolean[3][3];

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                ret[j][2 - i] = arr[i][j];
        return ret;
    }

    int rek(int t1, int t2, int t3) {
        // System.out.println(t1 + " " + t2 + " " + t3);

        if ((t1 == 1 && t2 == 0 && t3 == 0) ||
                (t1 == 0 && t2 == 1 && t3 == 0) ||
                (t1 == 0 && t2 == 0 && t3 == 1))
            return 2;

        if (t1 == 0 && t2 == 0 && t3 == 4)
            return 4;
        if (t1 == 0 && t2 == 4 && t3 == 0)
            return 4;

        if (t1 == t2 && t2 == t3 && t1 % 2 == 1)
            return t1 + t2 + t3 + 1;

        if (t1 == t2 && t2 == t3 && t1 % 2 == 0)
            return t1 + t2 + t3;

        if (t1 == 0 && t2 == 0 && t3 > 0)
            return t3 + 1;
        if (t1 == 0 && t2 > 0 && t3 == 0)
            return t2 + 1;
        if (t1 > 0 && t2 == 0 && t3 == 0)
            return t1 + 1;

        if (t1 == 3 && t2 == 5) {
            if (t3 == 1)
                return 10;
            if (t3 == 3)
                return 12;
        }

        if (t1 == 4 && t2 == 0 && t3 == 5)
            return 10;
        if (t1 == 4 && t2 == 2 && t3 == 0)
            return 7;
        if (t1 == 5 && t2 == 1) {
            if (t3 == 1)
                return 8;
            if (t3 == 3)
                return 10;
            if (t3 == 5)
                return 12;
        }
        if (t1 == 5 && t2 == 2 && t3 == 0)
            return 8;

        if (t1 % 2 == 0 && t2 % 2 == 0 && t3 % 2 == 0)
            return t1 + t2 + t3;
        if (t1 % 2 == 0 && t2 % 2 == 0 && t3 % 2 == 1)
            return t1 + t2 + t3;
        if (t1 % 2 == 0 && t2 % 2 == 1 && t3 % 2 == 0)
            return t1 + t2 + t3 + 1;
        if (t1 % 2 == 0 && t2 % 2 == 1 && t3 % 2 == 1)
            return t1 + t2 + t3 + 1;
        if (t1 % 2 == 1 && t2 % 2 == 0 && t3 % 2 == 0)
            return t1 + t2 + t3;
        if (t1 % 2 == 1 && t2 % 2 == 0 && t3 % 2 == 1) {
            if (t2 == 0)
                return t1 + t2 + t3 + 1;
            return t1 + t2 + t3;
        }
        if (t1 % 2 == 1 && t2 % 2 == 1 && t3 % 2 == 0)
            return t1 + t2 + t3 + 1;
        if (t1 % 2 == 1 && t2 % 2 == 1 && t3 % 2 == 1)
            return t1 + t2 + t3;

        return t1 + t2 + t3;
    }

    public int minheight(int type1, int type2, int type3) {

        boolean[][] first = { { false, false, false }, { true, true, false }, { false, true, true } };
        boolean[][] second = { { false, false, false }, { false, true, false }, { true, true, true } };
        boolean[][] third = { { false, false, false }, { true, true, true }, { true, false, false } };

        one[0] = first;
        two[0] = second;
        three[0] = third;

        for (int i = 1; i < 4; i++) {
            one[i] = rotate(one[i - 1]);
            two[i] = rotate(two[i - 1]);
            three[i] = rotate(three[i - 1]);
        }

        return rek(type1, type2, type3);
    }

    public static void main(String[] args) {
        Tetris t = new Tetris();
        System.out.println(t.minheight(3, 4, 6));
    }
}
