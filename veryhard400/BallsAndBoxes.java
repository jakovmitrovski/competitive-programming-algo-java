package veryhard400;

public class BallsAndBoxes {
    public int count(int N, String boxes) {

        String[] bx = boxes.split(";");

        int[] dp = new int[N + 1];

        for (int i = 0; i < bx.length; i++) {
            String[] in = bx[i].split(":");
            int ind = Integer.parseInt(in[0]), val = Integer.parseInt(in[1]);
            dp[ind] = val;
        }
        // for (int k=0; k<N; k++) System.out.print(dp[k] + " ");
        // System.out.println();
        int res = 0, br_left = 0, br_right = 0;
        for (int i = 0; i < N; i++) {
            if (dp[i] >= 1)
                continue;
            // take from left
            int left = 1 << 20, right = 1 << 20;
            int j = i - 1, br = 1;
            while (j != i) {
                if (j == -1)
                    j = N - 1;
                if (dp[j] > 1) {
                    left = j;
                    br_left = br;
                    break;
                }
                j--;
                br++;
            }
            j = i + 1;
            br = 1;
            while (j != i) {
                if (j == N)
                    j = 0;
                if (dp[j] > 1) {
                    right = j;
                    br_right = br;
                    break;
                }
                j++;
                br++;
            }
            if (br_left < br_right) {
                res += br_left;
                dp[left]--;
                dp[i]++;
            } else {
                res += br_right;
                dp[right]--;
                dp[i]++;
            }
            // System.out.println("Za " + i + " left=" + left + " right=" + right + "
            // br_left=" + br_left + " br_right=" + br_left);
            // for (int k=0; k<N; k++) System.out.print(dp[k] + " ");
            // System.out.println();
        }

        return res;
    }

    public static void main(String[] args) {
        BallsAndBoxes b = new BallsAndBoxes();
        System.out.println(b.count(5, "3:5"));
        System.out.println(b.count(27, "11:12;14:8;13:6;7:1"));
        System.out.println(b.count(50, "40:15;45:5;37:1;16:11;12:13;33:5"));
        // System.out.println(b.count(83,
        // "52:3;40:14;75:15;64:9;54:1;73:11;60:10;6:1;67:1;24:13;15:2;68:3"));
    }
}
