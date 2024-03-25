package easiest100;

public class CodeFuTesting {
    public int tester(String testCorrectness) {

        int cnt = 0;
        for (int i = 0; i < testCorrectness.length(); i++) {

            if (testCorrectness.charAt(i) == '1')
                cnt++;

            int next = cnt <= 50 ? 51 : cnt <= 90 ? 91 : 100;

            if (next - cnt > testCorrectness.length() - i - 1)
                return i + 1;
        }

        return 100;
    }
}
