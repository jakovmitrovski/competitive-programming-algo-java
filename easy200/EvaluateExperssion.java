package easy200;

import java.util.ArrayList;

public class EvaluateExpression {
    public static int evaluate(String expression) {
        return Integer.parseInt(eval(expression));
    }

    public static String eval(String expression) {
        String a = "";
        if (expression.indexOf("(") == -1)
            return resi(expression);

        int count = 1;
        int i;
        for (i = expression.indexOf("(") + 1; count > 0; i++) {
            if (expression.charAt(i) == '(')
                count++;
            if (expression.charAt(i) == ')')
                count--;
        }
        a = expression.substring(expression.indexOf("(") + 1, i - 1);
        return eval(expression.substring(0, expression.indexOf("(")) + eval(a) + expression.substring(i));

    }

    private static String resi(String a) {

        long broj = 0;
        ArrayList<Long> arr = new ArrayList<Long>();
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '*') {
                long br = arr.get(arr.size() - 1);
                i++;
                String broj1 = getBr(a, i);
                broj = Integer.parseInt(broj1);
                i += broj1.length() - 1;
                br *= broj;
                br %= 1000007;
                arr.remove(arr.size() - 1);
                arr.add(br);
            } else if (a.charAt(i) == '+') {

            } else {
                String broj1 = getBr(a, i);
                broj = Integer.parseInt(broj1);
                i += broj1.length() - 1;
                arr.add(broj);
            }
        }
        long sum = 0;
        for (int i = 0; i < arr.size(); i++) {
            sum += arr.get(i);
        }
        sum %= 1000007;
        return Long.toString(sum);
    }

    private static String getBr(String a, int q) {
        String br = "";
        for (int i = q; i < a.length(); i++) {
            if (Character.isDigit(a.charAt(i)))
                br += a.charAt(i);
            else
                break;
        }
        return br;
    }
}
