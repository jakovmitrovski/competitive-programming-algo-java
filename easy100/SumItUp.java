package easy100;

public class SumItUp {
    public int sum(int N) {
        int sum = 0;
        while (N > 0) {
            sum += N % 10;
            N /= 10;
            if (N == 0) {
                if (sum > 9) {
                    N = sum;
                    sum = 0;
                } else {
                    return sum;
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        SumItUp s = new SumItUp();
        System.out.println(s.sum(56452));
    }
}