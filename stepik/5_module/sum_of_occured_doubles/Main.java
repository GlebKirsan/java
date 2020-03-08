import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double sum = 0;

        while (in.hasNext()) {
            if (in.hasNextDouble()){
                sum += in.nextDouble();
            } else {
                in.next();
            }
        }

        System.out.printf("%.6f", sum);
    }
}