package easy200;

import java.util.HashSet;
import java.util.Set;

public class UniversalAntiVenom {
    String[] venom;
    Set<Character> sett;
    int rez = Integer.MAX_VALUE;

    void rek(Set<Character> set, String s) {
        if (s.length() == 5) {
            int br = 0;
            for (int i = 0; i < s.length(); i++)
                if (s.charAt(i) == '1') {
                    br++;
                    for (int j = 0; j < venom[i].length(); j++)
                        set.add(venom[i].charAt(j));
                }

            if (set.size() == sett.size())
                rez = Math.min(rez, br);
            set.clear();
        } else {
            rek(set, s + "0");
            rek(set, s + "1");
        }
    }

    public int getLeast(String[] venomType) {

        venom = venomType;
        sett = new HashSet<>();

        for (int i = 0; i < venomType.length; i++)
            for (int j = 0; j < venomType[i].length(); j++)
                sett.add(venomType[i].charAt(j));

        rek(new HashSet<>(), "");

        return rez;
    }
}