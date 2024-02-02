package medium300;

public class TextSimulator {
    public String simulate(String input) {

        char curr = 'a';
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<input.length(); i++){
            if (input.charAt(i) == '+'){
                i++;
                int toAdd = 0;
                if (i < input.length()-1 && Character.isDigit(input.charAt(i+1))){
                    toAdd = Integer.parseInt(input.substring(i, i+2));
                    i++;
                }else{
                    toAdd = Integer.parseInt(input.substring(i, i+1));
                }
                curr = (char)(curr + toAdd);
                sb.append(curr);
            }else if (input.charAt(i) == '-'){
                i++;
                int toAdd = 0;
                if (i < input.length()-1 && Character.isDigit(input.charAt(i+1))){
                    toAdd = Integer.parseInt(input.substring(i, i+2));
                    i++;
                }else{
                    toAdd = Integer.parseInt(input.substring(i, i+1));
                }
                curr = (char)(curr - toAdd);
                sb.append(curr);
            }else if (input.charAt(i) == '='){
                if (Character.isLowerCase(curr)) curr = 'a';
                else curr = 'A';
            }else if (input.charAt(i) == '^'){
                if (Character.isLowerCase(curr)) curr = Character.toUpperCase(curr);
                else curr = Character.toLowerCase(curr);
            }else if (input.charAt(i) == '*'){
                sb.append(curr);
            }
        }

        return sb.toString();
    }

   public static void main(String[] args) {
       TextSimulator t = new TextSimulator();
       System.out.println(t.simulate("^+23^-23+15^+9^-11="));
   }
}
