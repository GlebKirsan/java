import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();

        boolean onlyAPostiive = 0 < a && b <= 0 && c <= 0;
        boolean onlyBPostiive = a <= 0 && 0 < b && c <= 0;
        boolean onlyCPostiive = a <= 0 && b <= 0 && 0 < c;

        System.out.print(onlyAPostiive || onlyBPostiive || onlyCPostiive);
    }
}