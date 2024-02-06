package medium300;

import java.util.*;

public class Positives {

    int [][]dp = new int[51][20001];
    Set<Integer> s = new HashSet<>();
    int res(int []a, int ind, int n, int sum){
        //System.out.println(ind + " " + sum + " " + dp[ind][sum+10000]);
        if (dp[ind][sum+10000] != -1) return dp[ind][sum+10000];
        //System.out.println(ind + " " + sum);
        if (ind == n && sum >= 0) {
            dp[ind][sum + 10000] = 0;
            s.add(sum);
            return 1;
        }
        else if (ind==n) {
            dp[ind][sum + 10000] = 0;
            return 0;
        }
        int left = res(a, ind+1, n, sum+a[ind]), right = res(a, ind+1, n, sum-a[ind]);
        return dp[ind][sum+10000] = left+right;

    }

    public int getPositiveCount(int[] numbers) {

        s.clear();
        for (int i=0; i<=numbers.length; i++)
            for (int j=0; j<20001; j++) dp[i][j] = -1;
        res(numbers, 0, numbers.length, 0);
        return s.size();
    }

//    public static void main(String[] args) {
//        Positives p = new Positives();
//        System.out.println(p.getPositiveCount(new int[]{13, 2, 17, 11, 18}));
//        //System.out.println(p.getPositiveCount(new int[]{3, 2, 1}));
//        System.out.println(p.getPositiveCount(
//                new int[]{40, 66, 63, 73, 52, 4, 47, 36, 100, 83, 62, 76, 13,
//                        31, 28, 1, 93, 74}));
//        System.out.println(p.getPositiveCount(
//
//                new int[]{70, 77, 78, 85, 77, 66, 82, 80, 74, 62, 92, 20, 52,
//                        82, 9, 61, 47, 84, 43, 59,43,1,3,5,3,5,3,4,3,4,3,4,5,45,3,2,2,4,5,5,3,2,2,3,4,3,3,4,5}));
//    }
}

