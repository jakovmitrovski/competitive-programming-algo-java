package aHARD500;

public class SumOfConsecutiveNumbers {
    public int largestN(int S) {

        int left = 1, right = 1, sum = 0;
        while(true){
            sum += right;
            right++;
            if (sum > S){
                right--;
                sum -= left;
                sum -= right;
                left++;
            }
            if (sum == S) return right-left;
        }
    }

   public static void main(String[] args) {
       SumOfConsecutiveNumbers s = new SumOfConsecutiveNumbers();
       //System.out.println(s.largestN(1000907226));
       System.out.println(s.largestN(56452));
   }
}
