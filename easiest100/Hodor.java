package easiest100;

public class Hodor {
    public int hodor(String hodor) {
        int res = 0;
        for (int i = 0; i < hodor.length() - 4; i++)
            if (Character.toLowerCase(hodor.charAt(i)) == 'h'
                    && Character.toLowerCase(hodor.charAt(i + 1)) == 'o'
                    && Character.toLowerCase(hodor.charAt(i + 2)) == 'd'
                    && Character.toLowerCase(hodor.charAt(i + 3)) == 'o'
                    && Character.toLowerCase(hodor.charAt(i + 4)) == 'r') {

                res++;
                i += 3;
            }

        return res;
    }
}
