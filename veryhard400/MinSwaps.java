package veryhard400;

public class MinSwaps {

    int find(int num, int[] a) {
        for (int i = 0; i < a.length; i++)
            if (a[i] == num)
                return i;
        return 1;
    }

    public int min(int[] a) {

        int[] good = new int[a.length];

        for (int i = 1; i <= a.length; i++)
            good[i - 1] = i;

        int swaps = 0;

        for (int i = 0; i < a.length; i++) {
            if (a[i] != good[i]) {
                int ind = find(good[i], a);
                int tmp = a[ind];
                a[ind] = a[i];
                a[i] = tmp;
                swaps++;
            }
        }
        return swaps;
    }
}
