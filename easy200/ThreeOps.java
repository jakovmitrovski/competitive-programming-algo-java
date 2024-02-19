package easy200;

import java.util.HashSet;
import java.util.Set;

public class ThreeOps {
    public int howManyResults(int[] numbers) {

        Set<Integer> set = new HashSet<>();
        Set<Integer> nowset = new HashSet<>();

        for (int i = 0; i < numbers.length; i++) {
            nowset.add(numbers[i]);
            set.add(numbers[i]);
        }

        for (int t = 0; t < 3; t++) {
            Set<Integer> nset = new HashSet<>();
            for (Integer x : nowset)
                for (int j = 0; j < numbers.length; j++) {
                    nset.add(x + numbers[j]);
                    nset.add(x - numbers[j]);
                    nset.add(x * numbers[j]);
                    if (numbers[j] != 0)
                        nset.add(x / numbers[j]);
                }
            nowset = nset;
            if (t == 2)
                return nset.size();
        }
        return 0;
    }

    public static void main(String[] args) {
        ThreeOps t = new ThreeOps();
        System.out.println(t.howManyResults(new int[] { 2 }));
    }
}
