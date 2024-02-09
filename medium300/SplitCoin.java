package medium300;

import java.util.HashMap;
import java.util.Map;

public class SplitCoin {
    public int split(int[] coins) {

        int sum = 0;
        for (int i = 0; i < coins.length; i++)
            sum += coins[i];

        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 1);
        for (int j = 0; j < coins.length; j++)
            for (int i = sum / 2; i >= 0; i--)
                if (dp.containsKey(i)) {
                    // System.out.println(i + coins[j]);
                    dp.put(i + coins[j], 1);
                }

        for (int i = sum / 2; i >= 0; i--) {
            if (dp.containsKey(i))
                return Math.abs(i - (sum - i));
        }

        return 0;
    }

    public static void main(String[] args) {
        SplitCoin s = new SplitCoin();
        System.out.println(s.split(new int[] { 25, 5, 15, 62, 9, 87, 3, 35, 44, 9 }));
        System.out.println(s.split(new int[] { 1, 10000 }));
    }
}
