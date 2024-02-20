package easiest100;

public class BestLocals {
    public String getLocalBest(int[] points, String[] names, int start, int end) {

        int best = -1 << 20;
        String res = "";
        for (int i = start; i <= end; i++)
            if (points[i] > best) {
                best = points[i];
                res = names[i];
            }

        return res;
    }

    public static void main(String[] args) {
        BestLocals b = new BestLocals();
        System.out.println(b.getLocalBest(new int[] { 46, 11, 18, 41, 13, 27, 33 },
                new String[] { "VIs", "Hfj", "S", "h", "AwoB", "BL", "VE" }, 0, 2));
    }
}