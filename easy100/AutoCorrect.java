package easy100;

public class AutoCorrect {

    boolean isVowel(char c) {
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'u' || c == 'o' || c == 'y';
    }

    public String correct(String text) {

        String[] words = text.split(" ");

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (isVowel(word.charAt(0)) && isVowel(word.charAt(word.length() - 1))) {
                word = word.toLowerCase();
            } else if (isVowel(word.charAt(0)) && !isVowel(word.charAt(word.length() - 1))) {
                word = word.toUpperCase();
            } else if (!isVowel(word.charAt(0)) && isVowel(word.charAt(word.length() - 1))) {
                StringBuilder s = new StringBuilder();
                s.append(Character.toUpperCase(word.charAt(0)));
                s.append(word.substring(1).toLowerCase());
                word = s.toString();
            }
            int num = 0;
            for (int j = 0; j < word.length(); j++) {
                if (isVowel(word.charAt(j)))
                    num++;
                if (num <= 2 || !isVowel(word.charAt(j)))
                    sb.append(word.charAt(j));
            }
            if (i < words.length - 1)
                sb.append(" ");
        }

        return sb.toString();
    }

}