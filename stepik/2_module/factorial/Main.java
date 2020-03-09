import java.math.BigInteger;
import java.util.stream.IntStream;
import static java.math.BigInteger.ONE;

public class Main {
    public static BigInteger factorial(int value) {
        BigInteger result = ONE;
        for (BigInteger i = ONE; i.compareTo(BigInteger.valueOf(value)) != 1; i = i.add(ONE)) {
            result = result.multiply(i);
        }

        return result;
    }

    public static BigInteger factorialStream(int value) {
        return IntStream.rangeClosed(1, value)
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger.ONE, BigInteger::multiply);
    }

    public static void main(String[] args) {
        System.out.println(factorial(1));
        System.out.println(factorial(3));
        System.out.println(factorial(5));
    }
}
