package easiest100;

import java.util.ArrayList;

public class AlternativePermutation {
    public String getAlternative(String permutation) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> l2 = new ArrayList<>();

        String[] input = permutation.split(" ");
        for (int i = 0; i < input.length; i++) {
            list.add(Integer.parseInt(input[i]));
            l2.add(Integer.parseInt(input[i]));
        }

        list.sort(null);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1)
                sb.append(l2.indexOf(list.get(i))).append(" ");
            else
                sb.append(l2.indexOf(list.get(i)));
        }

        return sb.toString();
    }
}