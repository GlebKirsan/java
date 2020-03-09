import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int cups = scanner.nextInt();
        boolean isWeekend = scanner.nextBoolean();
        int bottom = 10;
        int top = 20;
        if (isWeekend) {
            bottom += 5;
            top += 5;
        }
        System.out.print(bottom <= cups && cups <= top);
    }
}