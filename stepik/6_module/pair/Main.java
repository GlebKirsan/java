import java.util.Objects;

public class Main {
    public static class Pair<T, K> {

        private T first;
        private K second;

        private Pair() {

        }

        private Pair(T first, K second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public K getSecond() {
            return second;
        }

        public static <T, K> Pair<T, K> of(T first, K second) {
            return new Pair<>(first, second);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }

            if (getClass() != other.getClass()) {
                return false;
            }

            Pair<?, ?> that = (Pair<?, ?>) other;
            return Objects.equals(first, that.first) && Objects.equals(second, that.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }

    public static void main(String[] args) {
        Pair<Integer, String> pair = Pair.of(1, "hello");
        Integer i = pair.getFirst(); // 1
        String s = pair.getSecond(); // "hello"

        Pair<Integer, String> pair2 = Pair.of(1, "hello");
        boolean mustBeTrue = pair.equals(pair2); // true!
        boolean mustAlsoBeTrue = pair.hashCode() == pair2.hashCode(); // true!
    }
}
