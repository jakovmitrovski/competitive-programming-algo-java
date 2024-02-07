package medium300;

import	java.util.Arrays;

public class AllThemSMS {
    int []dp = new int[100];
    String s;

    boolean postoi(String x){
        for (int i=0; i<x.length()-1; i++)
            if (x.charAt(i) != x.charAt(i+1)) return false;

        return true;
    }

    int getEnd(char c){
        if (c == '2') return 3;
        if (c == '3') return 3;
        if (c == '4') return 3;
        if (c == '5') return 3;
        if (c == '6') return 3;
        if (c == '7') return 4;
        if (c == '8') return 3;
        if (c == '9') return 4;
        if (c == '0') return 1;

        return 0;
    }

    int res(int pos){
        if (pos == 0) return 1;
        if (dp[pos] != -1) return dp[pos];
        dp[pos] = 0;

        int end = getEnd(s.charAt(pos-1));

        for (int i=1; i<=end; i++) {
            if (pos - i >= 0 && postoi(s.substring(pos-i, pos))) {
                dp[pos] += res(pos - i);
                dp[pos] %= 1000007;
            }
        }

        return dp[pos];
    }

    public int getCount(String sequence) {
        s = sequence;
        Arrays.fill(dp, -1);
        return res(sequence.length());
    }

   public static void main(String[] args) {
       AllThemSMS a = new AllThemSMS();
       System.out.println(a.getCount("777999999999999999999999922"));
   }
}