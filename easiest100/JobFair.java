package easiest100;

public class JobFair {
    public int maxStands(int[] startingStand, int[] endingStand, int N) {

        int res = 0;

        boolean[] overlap = new boolean[startingStand.length];

        for (int i = 0; i < startingStand.length; i++) {
            for (int j = i + 1; j < startingStand.length; j++)
                if (endingStand[j] < startingStand[j] || startingStand[j] > endingStand[i]) {
                } else {
                    overlap[i] = true;
                    overlap[j] = true;
                }
        }

        for (int i = 0; i < startingStand.length; i++)
            if (!overlap[i])
                res++;

        return res;
    }

    public static void main(String[] args) {
        JobFair j = new JobFair();
        System.out.println(j.maxStands(new int[] { 6, 10, 11, 33 }, new int[] { 9, 13, 14, 35 }, 35));
        System.out.println(j.maxStands(new int[] { 179, 327, 460, 828 }, new int[] { 234, 362, 484, 836 }, 1000));
    }
}
