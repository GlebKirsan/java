import java.util.Arrays;

public class Main {
    public static int[] mergeArrays(int[] a1, int[] a2){
        int[] result = new int[a1.length + a2.length];
        int pA1 = 0;
        int pA2 = 0;

        while(pA1 < a1.length && pA2 < a2.length){
            if (a1[pA1] < a2[pA2]) {
                result[pA1 + pA2] = a1[pA1];
                pA1 += 1;
            } else {
                result[pA1 + pA2] = a2[pA2];
                pA2 += 1;
            }
        }

        while (pA1 < a1.length) {
            result[pA1 + pA2] = a1[pA1++];
        }

        while (pA2 < a2.length) {
            result[pA1 + pA2] = a2[pA2++];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a1 = {0, 2, 2};
        int[] a2 = {1, 3};
        int[] merged = mergeArrays(a1, a2);
        System.out.println(Arrays.toString(merged));
    }
}
