public class Main {
    public static void main(String[] args) {
        ComplexNumber a = new ComplexNumber(1, 1);
        ComplexNumber b = new ComplexNumber(1, 1);

        System.out.println(a.equals(b)); //  must return true
        System.out.println(a.hashCode() + " " + b.hashCode()); // must be equal to b.hashCode()
    }
}
