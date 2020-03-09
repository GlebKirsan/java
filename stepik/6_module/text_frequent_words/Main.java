import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class Main {
//    public static String countMostFrequent(IntStream stream) {
//        Predicate<Character> isSpace = Character::isSpaceChar;
//        Predicate<Character> isLetterOrDigit = Character::isLetterOrDigit;
//        Predicate<Character> isSymbol = isSpace.or(isLetterOrDigit);
//        StringBuilder builder = new StringBuilder();
//        stream.mapToObj(item -> (char) item)
//                .map(item -> !isSymbol.test(item) ? ' ' : item)
//                .map(Character::toLowerCase)
//                .forEach(builder::appendCodePoint);
//
//        Map<String, Long> words = new TreeMap<>();
//        for (String word : builder.toString().split(" ")) {
//            Long oldValue = words.getOrDefault(word, 0L);
//            words.put(word, oldValue + 1);
//        }
//        Map<Long, ArrayList<String>> occurences = new TreeMap<>(Collections.reverseOrder());
//        for (Map.Entry<String, Long> entry : words.entrySet()) {
//            ArrayList<String> tmp = occurences.getOrDefault(entry.getValue(), new ArrayList<>());
//            tmp.add(entry.getKey());
//            occurences.put(entry.getValue(), tmp);
//        }
//        builder = new StringBuilder();
//        int i = 0;
//        finish:
//        for (Map.Entry<Long, ArrayList<String>> entry : occurences.entrySet()) {
//            for (String word : entry.getValue()) {
//                builder.append(word);
//                builder.append('\n');
//                if (++i > 10) {
//                    break finish;
//                }
//            }
//        }
//
//        return builder.toString();
//    }
//
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        StringBuilder builder = new StringBuilder();
//        while (in.hasNextLine()) {
//            builder.append(in.nextLine());
//        }
//        IntStream input = builder.toString().chars();
//        String result = countMostFrequent(input);
//        System.out.print(result);
//    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)
                .useDelimiter("[^\\p{L}\\p{Digit}]+");

        Map<String, Integer> freqMap = new HashMap<>();
        scanner.forEachRemaining(s -> freqMap.merge(s.toLowerCase(), 1, Integer::sum));

        freqMap.entrySet().stream()
                .sorted(descendingFrequencyOrder())
                .limit(10)
                .map(Map.Entry::getKey)
                .forEach(System.out::println);
    }

    private static Comparator<Map.Entry<String, Integer>> descendingFrequencyOrder() {
        return Comparator.<Map.Entry<String, Integer>>comparingInt(Map.Entry::getValue)
                .reversed()
                .thenComparing(Map.Entry::getKey);
    }
}
