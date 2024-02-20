package easiest100;

public class PointsInSphere {
    public int howMany(String points, String sphere) {

        String[] spherePts = sphere.split(",");
        int[] spts = new int[spherePts.length];
        for (int i = 0; i < spherePts.length; i++)
            spts[i] = Integer.parseInt(spherePts[i]);

        String[] input = points.split(";");

        int res = 0;

        for (int i = 0; i < input.length; i++) {
            String[] coordinates = input[i].split(",");
            int[] coords = new int[coordinates.length];
            for (int j = 0; j < coordinates.length; j++)
                coords[j] = Integer.parseInt(coordinates[j]);

            if (((coords[0] - spts[0]) * (coords[0] - spts[0])
                    + (coords[1] - spts[1]) * (coords[1] - spts[1])
                    + (coords[2] - spts[2]) * (coords[2] - spts[2])) <= (spts[3] * spts[3]))
                res++;
        }

        return res;
    }
}
