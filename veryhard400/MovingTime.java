package veryhard400;

public class MovingTime {
    public String maximizeRouterSpeed(String a, String b) {

        double x = Double.parseDouble(a), y = Double.parseDouble(b);
        double res = 1e9, ret = 0;
        for (double z = 0.0001; z <= 100.0000; z += 0.0001) {
            double s = (x + y + z) / 2;
            double mul = (s - x) * (s - y) * (s - z);
            double R = (x * y * z) / (4 * Math.sqrt(s * mul));
            double r = Math.sqrt((mul / s));

            if (x + y < z || y + z < x || z + x < y)
                continue;

            if (R * R * Math.PI - r * r * Math.PI - res < 0.0) {
                res = R * R * Math.PI - r * r * Math.PI;
                ret = z;
            }
        }
        // System.out.println(res);
        ret = Math.round(ret * 100) / 100.0;

        return String.format("%.2f", ret);
    }

    public static void main(String[] args) {
        MovingTime m = new MovingTime();
        System.out.println(m.maximizeRouterSpeed("1.8491", "3.5678"));
        System.out.println(m.maximizeRouterSpeed("4.5598", "1.5292"));
    }
}
