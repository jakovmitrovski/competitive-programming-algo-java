package medium300;

import java.util.*;

public class SimpleProblem {

    int mod = 1000000007;

    class Current {
        long x;
        int num;

        public Current(long x, int num) {
            this.x = x;
            this.num = num;
        }
    }

    public int minimumNumbers(int[] arr, int K) {

        int n = arr.length;

        List<Long> firstHalf = new ArrayList<>();
        List<Long> secondHalf = new ArrayList<>();

        for (int i = 0; i < n / 2; i++)
            firstHalf.add((long) arr[i]);
        for (int i = n / 2; i < n; i++)
            secondHalf.add((long) arr[i]);

        Map<Long, Integer> sums1 = new TreeMap<>();
        Map<Long, Integer> sums2 = new TreeMap<>();

        for (int mask = 0; mask < (1 << (n / 2)); mask++) {
            long sum = 0;
            for (int j = 0; j < n / 2; j++)
                if ((mask & (1 << j)) > 0) {
                    sum += arr[j];
                    sum %= mod;
                }
            if (sums1.containsKey(sum) && Integer.bitCount(mask) < sums1.get(sum)) {
                sums1.remove(sum);
                sums1.put(sum, Integer.bitCount(mask));
            } else if (!sums1.containsKey(sum))
                sums1.put(sum, Integer.bitCount(mask));
        }

        for (int mask = 0; mask < (1 << (n / 2 + n % 2)); mask++) {
            long sum = 0;
            for (int j = n / 2; j < n; j++)
                if ((mask & (1 << (j - n / 2))) > 0) {
                    sum += arr[j];
                    sum %= mod;
                }
            if (sums2.containsKey(sum) && Integer.bitCount(mask) < sums2.get(sum)) {
                sums2.remove(sum);
                sums2.put(sum, Integer.bitCount(mask));
            } else if (!sums2.containsKey(sum))
                sums2.put(sum, Integer.bitCount(mask));
        }

        // sums2.sort(Comparator.comparingLong(x -> x.x));

        int ret = 50;

        // for (Map.Entry<Long, Integer> t : sums1.entrySet())
        // System.out.print(t.getKey() + " " + t.getValue() + " ");
        // System.out.println();
        // for (Map.Entry<Long, Integer> t : sums2.entrySet())
        // System.out.print(t.getKey() + " " + t.getValue() + " ");
        // System.out.println();

        for (Map.Entry<Long, Integer> t : sums1.entrySet()) {

            long k = t.getKey();
            int val = t.getValue();

            long mom = K - k;
            while (mom < 0)
                mom += mod;

            if (sums2.containsKey(mom)) {
                ret = Math.min(ret, val + sums2.get(mom));
            }
        }

        if (ret == 50)
            ret = -1;

        return ret;
    }

    public static void main(String[] args) {
        SimpleProblem s = new SimpleProblem();
        System.out.println(s.minimumNumbers(new int[] { 11, 14, 9, 2, 2 }, 16));
        System.out.println(s.minimumNumbers(new int[] { 12, 10, 14, 18, 2, 15, 6, 18, 5, 8, 2, 2, 12 }, 54));
        System.out.println(s.minimumNumbers(
                new int[] { 41, 96, 62, 83, 58, 91, 31, 82, 9, 33, 86, 58, 94, 58, 83, 48, 4, 43, 32, 16 }, 708));
    }
}
