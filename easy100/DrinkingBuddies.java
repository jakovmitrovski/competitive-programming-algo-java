package easy100;

public class DrinkingBuddies {
    public String whoWon(String[] receipts) {

        double a = 0.0, p = 0.0;

        for (int i = 0; i < receipts.length; i++) {
            String[] in = receipts[i].split(" ");
            if (in[0].equals("Anna"))
                a += Double.parseDouble(in[1]);
            else
                p += Double.parseDouble(in[1]);
        }

        if (a - p > 0.0000001)
            return "Anna";
        else if (p - a > 0.0000001)
            return "Peter";

        return "Tied";
    }
}
