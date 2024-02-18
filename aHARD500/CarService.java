package aHARD500;

import java.util.Arrays;

public class CarService {

    public int howMany(int[] arrivalTimes, int[] waiting, int[] numberOfParts, int K) {

        int i;
        int N = arrivalTimes.length;
        Car cars[] = new Car[N];
        for (i = 0; i < N; i++) {
            cars[i] = new Car(arrivalTimes[i], arrivalTimes[i] + waiting[i], numberOfParts[i]);
        }
        Arrays.sort(cars);

        int t;
        int res = 0;
        int taken = 0;

        for (t = 0; t <= 1000; t++) {

            taken = 0;
            for (i = 0; i < N; i++) {
                if ((cars[i].arrives <= t) && (t < cars[i].leaves) && (cars[i].numOfParts > 0) && (taken < K)) {
                    cars[i].numOfParts--;
                    taken++;
                }
            }

            res += taken;
        }

        return res;
    }

    class Car implements Comparable<Car> {
        int arrives;
        int leaves;
        int numOfParts;

        Car(int arrives, int leaves, int numOfParts) {
            this.arrives = arrives;
            this.leaves = leaves;
            this.numOfParts = numOfParts;
        }

        @Override
        public int compareTo(Car arg0) {
            if (leaves < arg0.leaves) {
                return -1;
            } else if (leaves > arg0.leaves) {
                return 1;
            }
            return 0;
        }
    }
}
