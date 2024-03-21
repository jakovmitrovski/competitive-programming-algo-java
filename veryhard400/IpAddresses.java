package veryhard400;

public class IpAddresses {

    int mod = 1000000007;
    String s;
    int[][] dp = new int[101][101];

    int parse(String a) {
        int n = 0;
        for (int i = 0; i < a.length(); i++)
            n = n * 10 + (a.charAt(i) - '0');
        return n;
    }

    int rec(int ind, int cuts) {

        if (dp[ind][cuts] != -1)
            return dp[ind][cuts];

        if (ind == s.length()) {
            if (cuts == 0)
                return dp[ind][cuts] = 1;
            return dp[ind][cuts] = 0;
        }
        long ret = 0;

        for (int i = 1; i <= 3; i++) {
            if (ind + i > s.length())
                break;
            int num = parse(s.substring(ind, ind + i));
            // System.out.println(num + " " + s.substring(ind, ind+i));
            if (num <= 255 && cuts > 0) {
                // System.out.println(num);
                ret += rec(ind + i, cuts - 1);
                ret %= mod;
            }
            if (num == 0)
                break;
        }

        return dp[ind][cuts] = (int) ret;
    }

    public int count(String S, int K) {

        s = S;

        for (int i = 0; i < 101; i++)
            for (int j = 0; j < 101; j++)
                dp[i][j] = -1;

        return rec(0, K);
    }

    public static void main(String[] args) {
        IpAddresses i = new IpAddresses();
        System.out.println(i.count("0000", 3));
        System.out.println(i.count("0000", 4));
        System.out.println(i.count("100111", 3));
        System.out.println(i.count("76591049743654070355", 19));
    }
}
