package easiest100;

public class CapsLock {
    public int fewerPushes(String message) {

        int res = 0;
        boolean lower = true;

        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) >= 'a' && message.charAt(i) <= 'z') {
                if (lower)
                    continue;
                else {
                    lower = true;
                    res++;
                }
            } else if (message.charAt(i) >= 'A' && message.charAt(i) <= 'Z') {
                if (!lower)
                    continue;
                else {
                    res++;
                    lower = false;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        CapsLock c = new CapsLock();
        System.out.println(c.fewerPushes("dmhquPdNMMTToXGUp"));
    }
}
