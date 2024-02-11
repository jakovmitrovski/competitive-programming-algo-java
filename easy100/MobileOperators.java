package easy100;

public class MobileOperators {
    public String mostlyUsed(String[] numbers) {
        int[] res = new int[4];
        for (int i = 0; i < numbers.length; i++) {
            String tel = numbers[i];
            if (tel.charAt(2) == '0' || tel.charAt(2) == '1' || tel.charAt(2) == '2')
                res[0]++;
            else if (tel.charAt(2) == '5' || tel.charAt(2) == '6')
                res[1]++;
            else if (tel.charAt(2) == '7' || tel.charAt(2) == '8')
                res[2]++;
        }

        if (res[0] >= res[1] && res[0] >= res[2])
            return "Operator A";
        else if (res[1] >= res[0] && res[1] >= res[2])
            return "Operator B";
        else
            return "Operator C";
    }
}
