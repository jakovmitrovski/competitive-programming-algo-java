package easiest100;

public class MorseCode {
    public String decode(String morseCode) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < morseCode.length(); i += 5) {
            if (morseCode.substring(i, i + 5).equals("-----"))
                sb.append("0");
            if (morseCode.substring(i, i + 5).equals(".----"))
                sb.append("1");
            if (morseCode.substring(i, i + 5).equals("..---"))
                sb.append("2");
            if (morseCode.substring(i, i + 5).equals("...--"))
                sb.append("3");
            if (morseCode.substring(i, i + 5).equals("....-"))
                sb.append("4");
            if (morseCode.substring(i, i + 5).equals("....."))
                sb.append("5");
            if (morseCode.substring(i, i + 5).equals("-...."))
                sb.append("6");
            if (morseCode.substring(i, i + 5).equals("--..."))
                sb.append("7");
            if (morseCode.substring(i, i + 5).equals("---.."))
                sb.append("8");
            if (morseCode.substring(i, i + 5).equals("----."))
                sb.append("9");
        }

        return sb.toString();
    }
}
