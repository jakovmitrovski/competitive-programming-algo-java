package easy200;

import java.util.Arrays;

public class SmallestFitBox {
    public int minVolume(String object, String[] boxes) {

        String[] dims = object.split("x");
        int[] idims = new int[dims.length];
        for (int i = 0; i < dims.length; i++)
            idims[i] = Integer.parseInt(dims[i]);
        idims = Arrays.stream(idims).sorted().toArray();

        int minvolume = 1 << 30;
        for (int i = 0; i < boxes.length; i++) {
            String[] momdims = boxes[i].split("x");
            // for (String x : momdims) System.out.println(x);
            int[] imomdims = new int[momdims.length];
            for (int j = 0; j < momdims.length; j++)
                imomdims[j] = Integer.parseInt(momdims[j]);
            imomdims = Arrays.stream(imomdims).sorted().toArray();

            boolean flag = true;
            for (int j = 0; j < idims.length; j++)
                if (idims[j] > imomdims[j]) {
                    flag = false;
                    break;
                }
            if (flag) {
                int volume = imomdims[0] * imomdims[1] * imomdims[2];
                minvolume = Math.min(minvolume, volume);
            }

        }

        return minvolume == 1 << 30 ? -1 : minvolume;
    }

    public static void main(String[] args) {
        SmallestFitBox s = new SmallestFitBox();
        System.out.println(s.minVolume("68x316x350",
                new String[] { "594x176x646", "342x41x237", "87x599x706", "7x768x90" }));
    }
}
