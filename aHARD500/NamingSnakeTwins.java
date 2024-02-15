package aHARD500;

public class NamingSnakeTwins {
    String s = "";

    int res(int ind, String a, String b) {

        if (ind == s.length()) {
            if (a.equals(b))
                return 1;
            return 0;
        }

        else if (a.startsWith(b) || b.startsWith(a)) {
            long rez = res(ind + 1, a + s.charAt(ind), b) + res(ind + 1, a, b + s.charAt(ind));
            rez %= 1000007;
            return (int) rez;
        }

        return 0;
    }

    public int getCount(String snakeString) {
        s = snakeString;

        return res(0, "", "");
    }

    public static void main(String[] args) {
        NamingSnakeTwins n = new NamingSnakeTwins();
        System.out.println(n.getCount("BABBAB"));
        System.out.println(n.getCount("BBBBABBA"));
        System.out.println(n.getCount("BBBBABAAABAAABAAABABABBBAAABABAAABAB"));
        System.out.println(n.getCount("BBBBAABABAAABAABAABABBBABBBBBBAAAAAA"));
    }
}
