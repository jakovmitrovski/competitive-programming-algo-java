package easiest100;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ArrangeLetters {
    public String arrange(String sentence) {

        String[] words = sentence.split(" ");

        for (int i = 0; i < words.length; i++) {
            char[] word = words[i].toCharArray();

            for (int j = 0; j < word.length; j++)
                for (int k = j + 1; k < word.length; k++) {
                    if ((word[j] - 'A') > (word[k] - 'A')) {
                        char tmp = word[j];
                        word[j] = word[k];
                        word[k] = tmp;
                    }
                }
            words[i] = new String(word);
        }

        return Arrays.stream(words).sorted().collect(Collectors.joining(" "));
    }

    public static void main(String[] args) {
        ArrangeLetters a = new ArrangeLetters();
        System.out.println(a.arrange("xbjailkwehfapxbssoUwsckikm yUmdcbb Pq Nay T Zozmypzep"));
    }
}