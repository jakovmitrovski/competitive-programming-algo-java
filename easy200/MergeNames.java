package easy200;

import java.util.*;

public class MergeNames {
    public String merge(String[] names) {

        List<String> mom = new ArrayList<>(Arrays.asList(names));

        while (mom.size() != 1) {

            mom.sort(Comparator.comparing(x -> x));

            List<String> nmom = new ArrayList<>();

            int left = 0, right = mom.size() - 1;
            while (left < right) {

                nmom.add(mom.get(right) + mom.get(left));

                left++;
                right--;
            }

            if (left == right)
                nmom.add(mom.get(left));

            mom = new ArrayList<>(nmom);
        }

        return mom.get(0);
    }

    public static void main(String[] args) {
        MergeNames m = new MergeNames();
        System.out.println(m.merge(new String[] { "a", "b", "c", "d", "e" }));
    }
}
