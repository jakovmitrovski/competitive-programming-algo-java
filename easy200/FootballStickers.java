package easy200;

import java.util.HashSet;
import java.util.Set;

public class FootballStickers {
    public int getStartPoint(int[] stickers, int friends) {
        int n = stickers.length;
        while(n%friends != 0) n--;
        int best = -1<<20;
        int res = 0;

        for (int i=0; i<n; i++){
            Set<Integer> set = new HashSet<>();
            for (int j=i; j<n; j+=friends){
                set.add(stickers[j]);
            }

            if (best < set.size()){
                best = set.size();
                res = i;
            }
            //System.out.println(set.size() + " " + best + " " + i + " " + res);
        }

        return res;
    }

   public static void main(String[] args) {
       FootballStickers f = new FootballStickers();
       System.out.println(f.getStartPoint(new int[]{11, 4, 7, 18, 1, 16, 19, 9, 5, 19, 11, 0, 1, 16, 19, 3, 7, 5, 20, 15, 15, 10, 18, 1, 4, 19, 11, 9, 15, 9, 15, 0, 20, 20, 12, 4, 4, 4, 20, 8, 14, 14, 7, 19, 1, 5, 4, 8, 14, 2},
               4));
   }
}
