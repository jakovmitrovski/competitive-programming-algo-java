package aHARD500;

public class LeeroyJenkins {
    long[] coins;
    long[] fw;

    void upd(int ind, long val) {
        for (int i = ind; i < 100005; i += i & -i) {
            fw[i] += val;
            fw[i] %= 1000007;
        }
    }

    int q(int ind) {
        int sum = 0;
        for (int i = ind; i > 0; i -= i & -i) {
            sum += fw[i];
            sum %= 1000007;
        }
        return sum;
    }

    public int solve(int Start, int N, int M) {
        coins = new long[N + 2];
        fw = new long[100105];

        coins[0] = Start;
        upd(Start, Start);

        long sum = 0;
        for (int i = 1; i < N; i++) {
            coins[i] = ((coins[i - 1] * (long) 1103515245 + 12345) / 65536) % M;
            if (coins[i] == 0)
                continue;
            upd((int) coins[i], coins[i]);
            sum += q((int) coins[i] - 1);
            sum %= 1000007;
        }
        return (int) sum;
    }

}