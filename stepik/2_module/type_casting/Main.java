public class Main {
    public static char charExpression(int a) {
        return (char) ('\\' + a);
    }

    public static void main(String[] args) {
        System.out.println(charExpression(32));
        System.out.println(charExpression(29));
    }
}
