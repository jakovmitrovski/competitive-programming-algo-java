package medium300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumDifference {

    List<String> permutations;

    void permute(int k, String x, int mask) {
        if (mask == ((1 << k) - 1)) {
            permutations.add(x);
        } else {
            for (int i = 0; i < k; i++)
                if ((mask & (1 << i)) == 0) {
                    permute(k, x + i, mask | (1 << i));
                }
        }
    }

    int diff(String a, String num1) {
        int sum1 = 0;
        for (int i = 0; i < a.length(); i++)
            sum1 = (sum1 * 10 + (num1.charAt(a.charAt(i) - '0') - '0'));

        return Math.abs(sum1);

    }

    public int compute(int n, int k, String[] numbers) {

        permutations = new ArrayList<>();
        permute(k, "", 0);
        int ans = 1 << 30;
        for (int i = 0; i < permutations.size(); i++) {
            String curr = permutations.get(i);

            int[] nums = new int[n];

            for (int j = 0; j < n; j++)
                nums[j] = diff(curr, numbers[j]);
            Arrays.sort(nums);
            ans = Math.min(nums[n - 1] - nums[0], ans);

        }

        return ans;
    }

    public static void main(String[] args) {
        MinimumDifference m = new MinimumDifference();
        System.out.println(m.compute(3, 3, new String[] { "436", "915", "534" }));
    }
}
