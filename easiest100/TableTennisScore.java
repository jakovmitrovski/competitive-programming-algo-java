package easiest100;

public class TableTennisScore {
    public String whoWon(String score) {

        String[] scores = score.split(" ");

        String one = scores[0];

        int a = 0, p = 0;

        for (int i = 2; i < scores.length; i++) {
            String[] now = scores[i].split(":");
            int first = Integer.parseInt(now[0]), second = Integer.parseInt(now[1]);
            if (one.equals("Anna")) {
                if (first > second)
                    a++;
                else
                    p++;
            } else {
                if (first > second)
                    p++;
                else
                    a++;
            }
        }
        String resName = a > p ? "Anna" : "Peter";
        String other = "";

        if (resName.equals("Anna"))
            other = a + ":" + p;
        else
            other = p + ":" + a;
        return resName + " " + other;
    }
}
