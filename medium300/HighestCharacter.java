package medium300;

import java.util.*;

public class HighestCharacter {

    class Element implements Comparable<Element> {
        String sorted, original;

        public Element(String sorted, String original) {
            this.sorted = sorted;
            this.original = original;
        }

        public String getSorted() {
            return sorted;
        }

        public String getOriginal() {
            return original;
        }

        @Override
        public int compareTo(Element o) {
            if (this.sorted.equals(o.sorted)) {
                if (this.original.equals(o.original)) {
                    return 1;
                } else {
                    return this.original.compareTo(o.original);
                }
            } else {
                return -this.sorted.compareTo(o.sorted);
            }
        }
    }

    public String highest(String[] strings) {

        List<Element> list = new ArrayList<>();

        for (int i = 0; i < strings.length; i++) {
            char[] nz = strings[i].toCharArray();
            Arrays.sort(nz);
            int n = nz.length;
            char t;
            for (int j = 0; j < n / 2; j++) {
                t = nz[j];
                nz[j] = nz[n - j - 1];
                nz[n - j - 1] = t;
            }
            list.add(new Element(new String(nz), strings[i]));
        }
        Collections.sort(list);
        // list.sort(Comparator.comparing(Element::getSorted).thenComparing(Element::getOriginal).reversed());
        // for (Element e : list) System.out.println(e.original + " " + e.getSorted());
        return list.get(0).original;
    }

    // public static void main(String[] args) {
    // HighestCharacter h = new HighestCharacter();
    // System.out.println(h.highest(new String[]{"ab", "ba"}));
    // System.out.println(h.highest(new String[]{"a", "b", "d", "abxyz",
    // "abcxyz"}));
    //
    // }
}
