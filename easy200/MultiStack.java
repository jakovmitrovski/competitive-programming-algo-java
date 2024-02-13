package easy200;

import java.util.*;

public class MultiStack {
    public String getResult(int numStacks, String[] commands) {

        ArrayList<Stack<Double>> stacks = new ArrayList<>(numStacks);

        Double res = 0.00;

        for (int i = 0; i < numStacks; i++)
            stacks.add(new Stack<Double>());

        for (int i = 0; i < commands.length; i++) {
            String[] cmd = commands[i].split(" ");
            if (cmd[0].equals("push")) {
                String[] nums = cmd[1].split(";");
                int ind = Integer.parseInt(nums[0]);
                double val = Double.parseDouble(nums[1]);
                stacks.get(ind).add(val);
            } else if (cmd[0].equals("add")) {
                String[] nums = cmd[1].split(";");
                int ind1 = Integer.parseInt(nums[0]), ind2 = Integer.parseInt(nums[1]);
                double a = (double) stacks.get(ind1).pop();
                double b = (double) stacks.get(ind2).pop();
                stacks.get(ind1).add(a + b);
            } else if (cmd[0].equals("sub")) {
                String[] nums = cmd[1].split(";");
                int ind1 = Integer.parseInt(nums[0]), ind2 = Integer.parseInt(nums[1]);
                double a = (double) stacks.get(ind1).pop();
                double b = (double) stacks.get(ind2).pop();
                stacks.get(ind1).add(a - b);

            } else if (cmd[0].equals("mul")) {
                String[] nums = cmd[1].split(";");
                int ind1 = Integer.parseInt(nums[0]), ind2 = Integer.parseInt(nums[1]);
                double a = (double) stacks.get(ind1).pop();
                double b = (double) stacks.get(ind2).pop();
                stacks.get(ind1).add(a * b);

            } else if (cmd[0].equals("div")) {
                String[] nums = cmd[1].split(";");
                int ind1 = Integer.parseInt(nums[0]), ind2 = Integer.parseInt(nums[1]);
                double a = (double) stacks.get(ind1).pop();
                double b = (double) stacks.get(ind2).pop();
                stacks.get(ind1).add(a / b);

            } else {
                int ind = Integer.parseInt(cmd[1]);
                res = (Double) stacks.get(ind).pop();
            }
        }

        res = (res * 100) / 100;

        return String.format("%.2f", res);
    }

    public static void main(String[] args) {
        MultiStack m = new MultiStack();
        System.out.println(m.getResult(2, new String[] { "push 1;95", "push 1;0", "push 0;48", "push 0;29", "push 0;37",
                "div 1;1", "add 1;0", "mul 1;0", "pop 0" }));
    }
}
