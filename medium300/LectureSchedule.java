package medium300;

import java.util.Arrays;

public class LectureSchedule {

    public int maxLectures(int[] start, int[] end) {

        Arrays.sort(start);
        Arrays.sort(end);

        int last = 0;
        int st = 0, en = 0;
        int res = 0;

        while (st < start.length && en < start.length) {

            if (end[en] <= start[st]) {
                en++;
                continue;
            }

            if (start[st] < last) {
                st++;
                continue;
            }
            last = end[en];
            res++;
            st++;
            en++;

        }

        return res;
    }

    public static void main(String[] args) {
        LectureSchedule l = new LectureSchedule();
        System.out.println(l.maxLectures(new int[] { 2, 20, 35, 44 }, new int[] { 39, 41, 46, 49 }));
        System.out.println(
                l.maxLectures(new int[] { 2, 5, 16, 20, 46, 48, 50 }, new int[] { 21, 33, 39, 40, 48, 49, 50 }));
        System.out.println(
                l.maxLectures(new int[] { 21, 23, 28, 34, 40, 41, 49 }, new int[] { 32, 33, 41, 47, 48, 49, 50 }));
        System.out.println(
                l.maxLectures(new int[] { 53, 58, 84, 163, 329, 335, 475, 486, 595, 727, 938, 962, 968, 981, 998 },
                        new int[] { 144, 456, 513, 520, 592, 715, 781, 821, 844, 963, 970, 976, 983, 997, 999 }));
    }
}
