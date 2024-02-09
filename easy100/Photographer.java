package easiest100;

public class Photographer {
    public String getResolution(String[] pics) {
        
        double max = 0;
        
        for (int i=0; i<pics.length; i++){
            String []in = pics[i].split("x");
            int a = Integer.parseInt(in[0]), b = Integer.parseInt(in[1]);
            max = Math.max(max, (a*b)/1000000.0);
        }
        
        return String.format("%.1f", max);
    }
}
