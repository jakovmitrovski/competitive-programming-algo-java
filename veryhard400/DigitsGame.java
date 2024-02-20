package veryhard400;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DigitsGame {
    List<String> permutations;
    int[] dig = new int[10];
    char[] momres;

    public String getX(String a, String b) {

        for (int i = 0; i < 10; i++)
            dig[i] = 0;

        for (int i = 0; i < b.length(); i++) {
            dig[b.charAt(i) - '0']++;
        }
        momres = new char[a.length()];
        permutations = new ArrayList<>();
        permuteX(a.toCharArray(), 0, false);
        Collections.sort(permutations);
        return permutations.size() > 0 ? permutations.get(0) : "0";
    }

    public void permuteX(char[] a, int ind, boolean isbigger) {
        if (ind == a.length) {
            permutations.add(new String(momres));
        } else {
            if (isbigger) {
                for (int i = 0; i <= 9; i++)
                    if (dig[i] > 0) {
                        dig[i]--;
                        momres[ind] = (char) (i + '0');
                        permuteX(a, ind + 1, true);
                        dig[i]++;
                        break;
                    }
            } else {
                int curr = a[ind] - '0';
                if (dig[curr] > 0) {
                    momres[ind] = a[ind];
                    dig[curr]--;
                    permuteX(a, ind + 1, false);
                    dig[curr]++;
                }
                for (int i = curr + 1; i <= 9; i++)
                    if (dig[i] > 0) {
                        momres[ind] = (char) (i + '0');
                        dig[i]--;
                        permuteX(a, ind + 1, true);
                        dig[i]++;
                        break;
                    }
            }
        }
    }

    String getY(String a, String b) {

        for (int i = 0; i < 10; i++)
            dig[i] = 0;

        for (int i = 0; i < b.length(); i++) {
            dig[b.charAt(i) - '0']++;
        }
        momres = new char[a.length()];
        permutations = new ArrayList<>();
        permuteY(a.toCharArray(), 0, false);
        Collections.sort(permutations);
        return permutations.size() > 0 ? permutations.get(permutations.size() - 1) : "0";
    }

    void permuteY(char[] a, int ind, boolean smaller) {
        if (ind == a.length && smaller) {
            permutations.add(new String(momres));
        } else if (ind < a.length) {

            if (smaller) {
                for (int i = 9; i >= 0; i--) {
                    if (dig[i] > 0) {
                        dig[i]--;
                        momres[ind] = (char) (i + '0');
                        permuteY(a, ind + 1, true);
                        dig[i]++;
                        break;
                    }
                }
            } else {
                int curr = a[ind] - '0';
                if (dig[curr] > 0) {
                    dig[curr]--;
                    momres[ind] = a[ind];
                    permuteY(a, ind + 1, false);
                    dig[curr]++;
                }

                for (int i = curr - 1; i >= 0; i--)
                    if (dig[i] > 0) {
                        dig[i]--;
                        momres[ind] = (char) (i + '0');
                        permuteY(a, ind + 1, true);
                        dig[i]++;
                        break;
                    }
            }
        }
    }

    public String getNumbers(String A, String B) {
        String x = getX(A, B);
        String y = getY(A, B);
        return x + "," + y;
    }

    public static void main(String[] args) {
        DigitsGame d = new DigitsGame();
        System.out.println(d.getNumbers("3", "3"));
        System.out.println(d.getNumbers("1055179524580365422741541605930652807911167910",
                "7467062182279479849538439527516890985354955342"));
    }
}