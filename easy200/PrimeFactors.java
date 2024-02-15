package easy200;

public class PrimeFactors {
    public String primeFactors(int n) {

        StringBuilder sb = new StringBuilder();

        boolean[] prime = new boolean[10001];

        for (int i = 2; i <= n; i++) {
            if (!prime[i]) {
                for (int j = i + i; j <= n; j += i)
                    prime[j] = true;
            }
        }
        int i = 2;

        while (n > 1) {

            if (prime[i]) {
                i++;
                continue;
            }

            int num = 0;
            while (n % i == 0) {
                n /= i;
                num++;
            }

            if (n == 1) {
                for (int j = 0; j < num; j++) {
                    if (j == num - 1)
                        sb.append(i);
                    else
                        sb.append(i).append(",");
                }
            } else {
                for (int j = 0; j < num; j++) {
                    sb.append(i).append(",");
                }
            }
            i++;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        PrimeFactors p = new PrimeFactors();
        System.out.println(p.primeFactors(219));
    }
}
