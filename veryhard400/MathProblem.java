package veryhard400;

import java.util.ArrayList;
import java.util.Scanner;

public class MathProblem {
    static ArrayList<Integer> prosti = new ArrayList<>();

    static void primeFactors(int n, int[] brojaci) {
        int koren = (int) Math.sqrt(n);

        for (int i = 0; i < prosti.size(); i++) {
            if (prosti.get(i) > koren)
                break;

            while (n % prosti.get(i) == 0) {
                brojaci[prosti.get(i)]++;
                n /= prosti.get(i);
            }
        }

        if (n >= 2)
            brojaci[n]++;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        boolean[] sito = new boolean[101];

        for (int i = 2; i <= 100; i++)
            if (!sito[i])
                for (int j = i + i; j <= 100; j += i)
                    sito[j] = true;

        for (int i = 2; i <= 100; i++)
            if (!sito[i])
                prosti.add(i);

        while (t-- > 0) {
            int[] niza = new int[1001];
            int[] brojaci = new int[101];
            long rez = 1;
            int n = sc.nextInt();

            for (int i = 0; i < n; i++) {
                niza[i] = sc.nextInt();
                primeFactors(niza[i], brojaci);
            }

            for (int i = 2; i < 100; i++)
                if (brojaci[i] % 2 != 0) {
                    rez *= i;
                    rez %= 1000007;
                }

            System.out.println(rez);
        }

        sc.close();
    }
}
