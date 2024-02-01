package easy200;

import java.util.HashSet;

public class NumberRotation {
    public int count(int N) {

        String val = Integer.toBinaryString(N);

        StringBuilder sb = new StringBuilder();
        sb.append(val);
        HashSet<String> set = new HashSet<>();

        int br = 0;

        do {
            set.add(sb.toString());
            if (br > 100000) return set.size();
            char last = sb.charAt(sb.length() - 1);
            sb.setLength(sb.length() - 1);
            sb.insert(0, last);
            br++;
        }while(true);
    }

   public static void main(String[] args) {
       System.out.println(new NumberRotation().count(3));
   }
}