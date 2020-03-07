import java.util.function.DoubleUnaryOperator;

public class Main {
    public static double integrate(DoubleUnaryOperator f, double a, double b) {
        final double step = 1e-6;
        double area = 0;
        double start = a;
        double finish = b;

        double current = start;
        while (current <= finish) {
            double currentA = current;
            double currentB = current + step;
            area += f.applyAsDouble(currentA) * (currentB - currentA);
            current += step;
        }
        return area;
    }

    public static void main(String[] args) {

    }
}