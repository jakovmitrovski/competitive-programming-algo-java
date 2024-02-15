package aHARD500;

import java.util.Arrays;

public class ArithmeticProgression {

    boolean check(int[] array) {
        for (int i = 0; i < array.length - 1; i++)
            if (array[i] > array[i + 1])
                return false;

        return true;
    }

    public int maxChange(int[] array) {

        if (check(array))
            return 0;

        Arrays.sort(array);
        int res = 1 << 30;
        int[] arth = new int[array.length];
        for (int i = -10000; i <= 10000; i++) {
            int largest = -1 << 29, smallest = 1 << 29;
            for (int j = 0; j < array.length; j++) {
                arth[j] = array[j] - j * i;
                largest = Math.max(arth[j], largest);
                smallest = Math.min(arth[j], smallest);
            }
            res = Math.min(res, (int) ((long) largest - (long) smallest + 1L) / 2);
        }

        return res;
    }

    public static void main(String[] args) {
        ArithmeticProgression ap = new ArithmeticProgression();
        System.out.println(ap.maxChange(new int[] { -3, -4, -2, -3, 3 }));
    }
}
