package easiest100;

public class TheLoneInteger {
    public int findIt(int[] a) {

        int[] f = new int[1000];

        for (int i = 0; i < a.length; i++)
            f[a[i]]++;

        for (int i = 0; i < 1000; i++)
            if (f[i] == 1)
                return i;

        return 0;
    }
}
