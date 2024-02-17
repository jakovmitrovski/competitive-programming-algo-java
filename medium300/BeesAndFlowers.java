package medium300;

public class BeesAndFlowers {
    int mini = 1 << 30;
    long[] flw = new long[26];
    int n;

    int ret(long sum, int num, int ind, int target, int mod) {
        // System.out.println(sum);
        if (sum % mod == target) {
            mini = Math.min(num, mini);
        }
        if (ind == n)
            return 0;
        else {
            return ret(sum, num, ind + 1, target, mod) + ret(sum + flw[ind], num + 1, ind + 1, target, mod);
        }
    }

    public int minimumFlowers(String[] flowers, String honeyQuality) {
        mini = 1 << 30;
        n = flowers.length;
        for (int i = 0; i < n; i++)
            flw[i] = Long.parseLong(flowers[i]);
        ret(0, 0, 0, Integer.parseInt(honeyQuality), 123456);

        return mini == 1 << 30 ? -1 : mini;
    }

    public static void main(String[] args) {
        BeesAndFlowers b = new BeesAndFlowers();
        System.out
                .println(b.minimumFlowers(
                        new String[] { "88881640955", "63330", "53158732", "8", "8641937", "9484621", "737229034497",
                                "90626502", "5552809", "8460038530025", "71915584252", "2990967", "9058394", "78262",
                                "4930372217", "3390401880081", "3964245", "872", "8701510622", "3206128799" },
                        "109227"));
        System.out.println(b.minimumFlowers(
                new String[] { "9574", "57243", "8229", "9490", "5", "50042", "9215", "7969", "978", "9303" },
                "54758"));
    }
}
