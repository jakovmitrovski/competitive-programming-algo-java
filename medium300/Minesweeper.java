package medium300;

public class Minesweeper {
    public String solve(String minefield, int n) {
        StringBuilder sb = new StringBuilder();
        sb.append(minefield);
        for (int i=0; i<n; i++)
            for (int j=0; j<n; j++){
                int res = 0;
                if(sb.charAt(i*n+j) == 'x') continue;
                if (i > 0 && sb.charAt((i-1)*n+j) == 'x') res++;
                if (j > 0 && sb.charAt(i*n+j-1) == 'x') res++;
                if (i < n-1 && sb.charAt((i+1)*n+j) == 'x') res++;
                if (j < n-1 && sb.charAt(i*n+j+1) == 'x') res++;
                if (j > 0 && i > 0 && sb.charAt((i-1)*n+j-1) == 'x') res++;
                if (j > 0 && i < n-1 && sb.charAt((i+1)*n+j-1) == 'x') res++;
                if (i > 0 && j < n-1 && sb.charAt((i-1)*n+j+1) == 'x') res++;
                if (i < n-1 && j < n-1 && sb.charAt((i+1)*n+j+1) == 'x') res++;

                sb.setCharAt(i*n+j, String.valueOf(res).charAt(0));
            }

        return sb.toString();
    }

    public static void main(String[] args) {
        Minesweeper minesweeper = new Minesweeper();
        String minefield = "..x......................x........x...........x.x......x........";
        int n = 8;
        String result = minesweeper.solve(minefield, n);
        System.out.println(result);
    }
}