package easy200;

public class Volleyball {
    public String getResult(String games) {

        StringBuilder sb = new StringBuilder();
        boolean first = true;
        int serving = 1, cntT1 = 0, cntT2 = 0, game = 0;
        for (int i = 0; i < games.length(); i++) {
            if (serving == 1 && games.charAt(i) == '1')
                cntT1++;
            else if (serving == 1 && games.charAt(i) == '2')
                serving = 2;
            else if (serving == 2 && games.charAt(i) == '1')
                serving = 1;
            else if (serving == 2 && games.charAt(i) == '2')
                cntT2++;

            if (cntT1 >= 15 && cntT1 - cntT2 >= 2) {
                if (!first) {
                    sb.append("-").append(cntT1).append(":").append(cntT2);
                } else {
                    sb.append(cntT1).append(":").append(cntT2);
                }
                game++;
                first = false;
                cntT1 = 0;
                cntT2 = 0;
                serving = game == 1 ? 2 : game == 2 ? 1 : 2;
            }
            if (cntT2 >= 15 && cntT2 - cntT1 >= 2) {
                if (!first) {
                    sb.append("-").append(cntT1).append(":").append(cntT2);
                } else {
                    sb.append(cntT1).append(":").append(cntT2);
                }
                game++;
                first = false;
                cntT1 = 0;
                cntT2 = 0;
                serving = game == 1 ? 2 : game == 2 ? 1 : 2;

            }
        }

        // if (cntT1 != 0 || cntT2 != 0)
        // sb.append("-").append(cntT1).append(":").append(cntT2);

        return sb.toString();
    }
}