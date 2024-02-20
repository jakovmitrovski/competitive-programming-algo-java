package easiest100;

public class SimpleAddition {
    public int smallestNumber(int N, int M, int X) {
        int[] results = new int[100001];
        int[] coins = new int[N + M + 1];

        results[0] = 1;

        for (int i = 0; i < N; i++)
            coins[i] = 3;
        for (int j = N; j < N + M; j++)
            coins[j] = 5;

        for (int j = 0; j < N + M; j++)
            for (int i = 100000; i >= 0; i--) {
                if (results[i] == 1) {
                    results[i + coins[j]] = 1;
                }
            }

        for (int i = X + 1; i <= 100000; i++)
            if (results[i] == 1)
                return i;

        return -1;
    }
}
