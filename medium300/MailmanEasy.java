package medium300;

public class MailmanEasy {
    public String totalHouses(int H, int W) {

        long x = ((long) H * W);

        if (H == 1 && W > 2 || W == 1 && H > 2)
            return "0";
        else if (H % 2 == 0 || W % 2 == 0)
            return String.valueOf(x);

        return "0";

    }
}
