package easy200;

public class Brackets {

    public int longest(String brackets) {

        int op = 0;
        int good = 0;

        for (int i = 0; i < brackets.length(); i++) {
            if (op == 0 && brackets.charAt(i) == ']')
                continue;
            if (brackets.charAt(i) == '[')
                op++;
            else {
                good += 2;
                op--;
            }
        }

        return good;
    }

    public static void main(String[] args) {
        Brackets b = new Brackets();
        System.out.println(b.longest("[[]][][][][[]]"));
        System.out.println(b.longest(
                "[][][][[]][[[][[[]][[[]][[[[]][]][]]]]]]][[[[][]][][[[][[]]]]][[][]][[[]][]][[[]]][[][[]][[[[][][][]"));
    }
}
