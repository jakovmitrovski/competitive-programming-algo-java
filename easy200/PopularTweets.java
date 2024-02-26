package easy200;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PopularTweets {
    public String mostPopular(String[] tweets) {

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < tweets.length; i++) {
            Set<String> set = new HashSet<>();

            String[] words = tweets[i].split(" ");
            for (int j = 0; j < words.length; j++)
                if (words[j].startsWith("#")) {
                    String now = words[j].substring(1).toLowerCase();
                    set.add(now);

                }

            for (String now : set) {
                if (map.containsKey(now)) {
                    int x = map.get(now);
                    map.put(now, x + 1);
                } else {
                    map.put(now, 1);
                }
            }
        }
        int mx = 0;
        String res = "";
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (mx < entry.getValue()) {
                res = entry.getKey();
                mx = entry.getValue();
            } else if (mx == entry.getValue() && res.compareTo(entry.getKey()) >= 1) {
                res = entry.getKey();
            }
        }

        return "#" + res;
    }

    public static void main(String[] args) {
        PopularTweets p = new PopularTweets();
        System.out.println(p.mostPopular(new String[] { "The quick brown #fox jumps over the lazy #dog",
                "#Fox with the best shows on #tv", "#Dog finds a #fox and barks at another #dog" }));
    }
}
