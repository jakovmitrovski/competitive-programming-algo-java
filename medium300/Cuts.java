package medium300;

public class Cuts {

    int[][] dp = new int[101][101];
    String s;
    int K;

    int res(int ind, int cuts) {

        int rez = Integer.MIN_VALUE, ret = Integer.MAX_VALUE;

        if (ind == s.length()) {
            if (cuts == K)
                return Integer.MIN_VALUE;
            else
                return Integer.MAX_VALUE;
        }

        if (dp[ind][cuts] != -1)
            return dp[ind][cuts];

        for (int i = 1; i < 9; i++) {
            rez = Integer.MIN_VALUE;
            if (cuts < K && ind + i <= s.length()) {
                rez = Math.max(rez, Math.max(Integer.parseInt(s.substring(ind, ind + i)), res(ind + i, cuts + 1)));
                ret = Math.min(rez, ret);
            }
        }
        if (s.substring(ind).length() < 9 && cuts == K) {
            rez = Math.max(rez, Integer.parseInt(s.substring(ind)));
        } else
            rez = Integer.MAX_VALUE;

        ret = Math.min(rez, ret);
        return dp[ind][cuts] = ret;

    }

    public int getMax(String numbers, int k) {

        for (int i = 0; i <= numbers.length(); i++)
            for (int j = 0; j <= k; j++)
                dp[i][j] = -1;

        s = numbers;
        K = k;

        return res(0, 0);
    }

    public static void main(String[] args) {
        Cuts c = new Cuts();
        System.out.println(c.getMax("5123155", 2));
        System.out.println(c.getMax("13113314553176", 3));
        System.out.println(c.getMax(
                "68883985971589478494434300509919666397564218333837575157371807879413155054259037137655358", 40));
    }
}