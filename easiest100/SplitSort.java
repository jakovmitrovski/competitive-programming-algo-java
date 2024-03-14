package easiest100;

public class SplitSort {
    public String sort(int[] array, int x) {

        for (int i = 0; i < array.length; i++)
            for (int j = i + 1; j < array.length; j++)
                if (array[i] > x && array[j] > x && array[i] < array[j]) {
                    int tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }

        for (int i = 0; i < array.length; i++)
            for (int j = i + 1; j < array.length; j++)
                if (array[i] < x && array[j] < x && array[i] > array[j]) {
                    int tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < array.length - 1; i++)
            sb.append(array[i]).append(",");
        sb.append(array[array.length - 1]);

        return sb.toString();
    }
}
