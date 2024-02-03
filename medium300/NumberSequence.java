package medium300;

public class NumberSequence {
    public int findDigit(int N) {
        int prev = 0;
        int n = 1;
        while (N > (String.valueOf(n).length() + prev)) {
            N = N - (String.valueOf(n).length() + prev);
            prev = prev + String.valueOf(n).length();
            n++;
        }

        String temp = "";
        for (int i = 1; i <= n; i++) {
            temp = temp.concat(String.valueOf(i));
        }

        return Integer.parseInt(String.valueOf(temp.charAt(N - 1)));
    }

    public static void main(String[] args) {
        NumberSequence n = new NumberSequence();
        System.out.println(n.findDigit(11));
    }
}
