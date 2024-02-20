package easiest100;

public class SmallestPair {
    public int smallest(int[] numbers) {

        int rez = 1 << 20;
        for (int i = 0; i < numbers.length - 1; i++)
            rez = Math.min(rez, numbers[i] + numbers[i + 1]);

        return rez;
    }
}
