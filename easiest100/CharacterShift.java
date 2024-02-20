package easiest100;

public class CharacterShift {
    public String shift(String word, int n) {

        StringBuilder sb = new StringBuilder();

        for (char x : word.toCharArray()) {
            int br = x - 'a';
            br += n;
            br %= 26;
            sb.append((char) (br + 'a'));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        CharacterShift c = new CharacterShift();
        System.out.println(c.shift("kyaedjapotavsrt", 8));
        System.out.println(c.shift("cyipqvskgc", 20));
    }
}
