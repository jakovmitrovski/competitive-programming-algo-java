package easy200;

public	class SMSOutcome {
    
    public String get(char c){
        StringBuilder sb = new StringBuilder();
        if (c == 'a') {
            sb.append("2");
            return sb.toString();
        }
        if (c == 'b') {
            sb.append("22");
            return sb.toString();
        }
        if (c == 'c') {
            sb.append("222");
            return sb.toString();
        }
        if (c == 'd') {
            sb.append("3");
            return sb.toString();
        }
        if (c == 'e') {
            sb.append("33");
            return sb.toString();
        }
        if (c == 'f') {
            sb.append("333");
            return sb.toString();
        }
        if (c == 'g') {
            sb.append("4");
            return sb.toString();
        }
        if (c == 'h') {
            sb.append("44");
            return sb.toString();
        }
        if (c == 'i') {
            sb.append("444");
            return sb.toString();
        }
        if (c == 'j') {
            sb.append("5");
            return sb.toString();
        }
        if (c == 'k') {
            sb.append("55");
            return sb.toString();
        }
        if (c == 'l') {
            sb.append("555");
            return sb.toString();
        }
        if (c == 'm') {
            sb.append("6");
            return sb.toString();
        }
        if (c == 'n') {
            sb.append("66");
            return sb.toString();
        }
        if (c == 'o') {
            sb.append("666");
            return sb.toString();
        }
        if (c == 'p') {
            sb.append("7");
            return sb.toString();
        }
        if (c == 'q') {
            sb.append("77");
            return sb.toString();
        }if (c == 'r') {
            sb.append("777");
            return sb.toString();
        }if (c == 's') {
            sb.append("7777");
            return sb.toString();
        }if (c == 't') {
            sb.append("8");
            return sb.toString();
        }if (c == 'u') {
            sb.append("88");
            return sb.toString();
        }if (c == 'v') {
            sb.append("888");
            return sb.toString();
        }if (c == 'w') {
            sb.append("9");
            return sb.toString();
        }if (c == 'x') {
            sb.append("99");
            return sb.toString();
        }if (c == 'y') {
            sb.append("999");
            return sb.toString();
        }if (c == 'z') {
            sb.append("9999");
            return sb.toString();
        }
        if (c == ' ') {
            sb.append("0");
            return sb.toString();
        }
        return sb.toString();
            
    }
    
    public String getSequence(String sentence) {
        StringBuilder sb = new StringBuilder();
        for (char c : sentence.toCharArray()) sb.append(get(c));
        return sb.toString();
    }
}
