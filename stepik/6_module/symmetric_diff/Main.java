import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static <T> Set<T> symmetricDifference(Set<? extends T> set1, Set<? extends T> set2) {
        Set<T> difference = new HashSet<T>();
        set1.forEach((k) -> {
            if (!set2.contains(k)) {
                difference.add(k);
            }
        });

        set2.forEach((k) -> {
            if (!set1.contains(k)) {
                difference.add(k);
            }
        });
        return difference;
    }

    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<>(Arrays.asList(0, 1, 2));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(1, 3));

        Set<Integer> difference = symmetricDifference(set1, set2);
        System.out.print(difference);
    }
}
