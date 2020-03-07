public class Main {
    public static double sqrt(double x) {
        if (x < 0) {
            throw new IllegalArgumentException("Expected non-negative number, got " + x);
        }
        return Math.sqrt(x);
    }

    public static void main(String[] args) {
        System.out.println(sqrt(-4));
    }
}