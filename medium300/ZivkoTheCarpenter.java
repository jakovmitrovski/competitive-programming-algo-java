package medium300;

public class ZivkoTheCarpenter {
    public int getMinNails(int[] left, int[] right) {

        for (int i = 0; i < left.length; i++)
            for (int j = 0; j < left.length; j++)
                if (left[i] > left[j]) {
                    int tmp = left[i];
                    left[i] = left[j];
                    left[j] = tmp;
                    tmp = right[i];
                    right[i] = right[j];
                    right[j] = tmp;
                }

        boolean[] visited = new boolean[16];
        int cnt = 0;
        for (int i = 0; i < left.length; i++) {
            if (visited[i])
                continue;
            cnt++;
            for (int j = 0; j < left.length; j++) {
                if (left[i] > right[j] || right[i] < left[j])
                    continue;
                else
                    visited[j] = true;
            }
        }

        return cnt;
    }

    public static void main(String[] args) {
        ZivkoTheCarpenter z = new ZivkoTheCarpenter();
        System.out.println(z.getMinNails(new int[] { 49, 48, 8, 40, 26, 41, 49, 29, 23, 37 },
                new int[] { 51, 49, 9, 42, 28, 43, 50, 31, 25, 38 }));
    }
}
