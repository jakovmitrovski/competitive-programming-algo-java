package easy200;

public class HexPalindroms {

    String get(int x) {
        if (x < 10)
            return String.valueOf(x);
        if (x == 10)
            return "A";
        if (x == 11)
            return "B";
        if (x == 12)
            return "C";
        if (x == 13)
            return "D";
        if (x == 14)
            return "E";
        return "F";
    }

    String hex(int x) {
        StringBuilder sb = new StringBuilder();
        while (x > 0) {
            sb.append(get(x % 16));
            x /= 16;
        }

        return sb.toString();
    }

    boolean check(String s) {
        for (int i = 0; i < s.length() / 2; i++)
            if (s.charAt(i) != s.charAt(s.length() - 1 - i))
                return false;

        return true;
    }

    public int getHexPalindromes(int start, int end) {
        int cnt = 0;
        for (int i = start; i <= end; i++)
            if (check(hex(i)))
                cnt++;
        return cnt;
    }
}