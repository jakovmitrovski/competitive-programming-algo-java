package aHARD500;

import	java.util.Arrays;
import java.util.List;

public class LinearOverlap {

    int [][]dp = new int[1<<16][16];

    String get(String a, String b){
        StringBuilder x = new StringBuilder(a);
        for (int i=Math.min(b.length(), a.length()); i>=1; i--){
            String subs = b.substring(0,i);
            if (a.endsWith(subs)) {
                x.append(b.substring(i));
                return x.toString();
            }
        }
        x.append(b);
        return x.toString();
    }

    int res(int mask, int ind, String mom, String []words){
        if (mask == ((1<<words.length)-1)) return 0;
        if (dp[mask][ind] != -1) return dp[mask][ind];

        int rez = 1<<30;

        for (int i=0; i<words.length; i++)
            if ((mask &(1<<i)) == 0) {
                String s = get(mom, words[i]);
                rez = Math.min(rez, s.length()-mom.length() + res(mask | (1<<i), i, s, words));
            }

        return dp[mask][ind] = rez;
    }

    public int getMinString(String[] words) {
        for (int i=0; i<(1<<16); i++)
            Arrays.fill(dp[i], -1);
        return res(0,15,"", words);
    }

   public static void main(String[] args) {
       LinearOverlap l = new LinearOverlap();
       System.out.println(l.getMinString(new String[]{"abc", "def", "ghi", "jkl", "mno"}));
   }
}
