import java.util.stream.IntStream;

public class Main {
    public static IntStream pseudoRandomStream(int seed) {
        return IntStream.iterate(seed, n -> (n * n) % 10000 / 10)
                .limit(100);
    }

    public static void main(String[] args) {
        System.out.print(pseudoRandomStream(13));
    }
}
