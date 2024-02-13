package easy100;

public class ArraySnakes {
    public int sharedPositions(int head1, int tail1, int head2, int tail2) {

        int[] snakes = new int[101];

        if (head1 > tail1) {
            int tmp = head1;
            head1 = tail1;
            tail1 = tmp;
        }

        if (head2 > tail2) {
            int tmp = head2;
            head2 = tail2;
            tail2 = tmp;
        }

        for (int i = head1; i <= tail1; i++)
            snakes[i]++;
        for (int j = head2; j <= tail2; j++)
            snakes[j]++;

        int cnt = 0;
        for (int i = 0; i < 101; i++)
            if (snakes[i] == 2)
                cnt++;
        return cnt;
    }
}