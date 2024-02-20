package easy200;

public class Calculator {

    int op(int a, int b, char operator) {
        if (operator == '-') {
            return a - b;
        } else if (operator == '+') {
            return a + b;
        } else if (operator == '*') {
            return a * b;
        } else {
            return a / b;
        }
    }

    public int getResult(String input) {

        int current = 0, display = 0, result = 0, mem = 0;
        char operation = '+';

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length() - 1; i++)
            sb.append(input.charAt(i));
        sb.append("*1");
        sb.append(input.charAt(input.length() - 1));
        input = sb.toString();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) >= '0' && input.charAt(i) <= '9') {

                if (i > 0 && input.charAt(i - 1) != 'R')
                    current = 0;

                while (input.charAt(i) >= '0' && input.charAt(i) <= '9') {
                    current = current * 10 + (input.charAt(i) - '0');
                    i++;
                }
                i--;
                display = current;
            } else if (input.charAt(i) == 'M') {
                mem = display;
            } else if (input.charAt(i) == 'R') {
                current = mem;
                display = current;
            } else if (input.charAt(i) == '=') {

                // result = op(result, current, operation);

                return result;
            } else {
                // System.out.println(current + " " + result + " " + operation);
                result = op(result, current, operation);
                display = result;
                operation = input.charAt(i);
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Calculator c = new Calculator();
        System.out.println(c.getResult("2+3*M2+R="));
        System.out.println(c.getResult("2+M3+R="));
        System.out.println(c.getResult("2+3*M2M+3R="));
        System.out.println(c.getResult("110*96M/R110="));
    }
}
