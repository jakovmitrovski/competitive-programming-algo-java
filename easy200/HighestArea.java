package easy200;

public class HighestArea {

    public double dist(int x1, int y1, int x2, int y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public double area(String[] coords) {
        int x1 = Integer.parseInt(coords[0]), y1 = Integer.parseInt(coords[1]), x2 = Integer.parseInt(coords[2]),
                y2 = Integer.parseInt(coords[3]), x3 = Integer.parseInt(coords[4]), y3 = Integer.parseInt(coords[5]);

        double a = dist(x1, y1, x2, y2), b = dist(x1, y1, x3, y3), c = dist(x2, y2, x3, y3);
        double s = (a + b + c) / 2.0;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    public int highest(String triangles) {
        String[] input = triangles.split(";");
        int br = 0, ret = 0;
        double max = 0.0;
        for (String triangle : input) {
            String[] coords = triangle.split(",");
            double are = area(coords);
            if (area(coords) > max) {
                ret = br;
                max = are;
            }
            br++;
        }
        return ret;
    }
}
