package easy200;

public class GatorProblems {

    public int ways(String binaryNumber) {
        int num = 0;
        int n = binaryNumber.length();
        for (int j = 1; j < n; j++)
            for (int k = j + 1; k < n; k++)
                for (int l = k + 1; l < n; l++)
                    if (binaryNumber.charAt(j) == '1' && binaryNumber.charAt(k) == '1' && binaryNumber.charAt(l) == '1')
                        num++;
        return num;

    }

    public static void main(String[] args) {
        GatorProblems g = new GatorProblems();
        System.out.println(g.ways("110011100111"));
        System.out.println(g.ways("110110"));
        System.out.println(g.ways("1011001000001111101000100000011110011111000111"));
        System.out.println(g.ways("1101011100000000111000101111110010110000111110001"));
        System.out.println(g.ways("111010101001101100000000110111100111"));
    }
}
