package banking;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static final byte EXIT = 0;
    private static final byte CREATE_ACCOUNT = 1;
    private static final byte BALANCE = 1;
    private static final byte LOG_INTO_ACCOUNT = 2;
    private static final byte LOG_OUT = 2;
    private static final Random rand = new Random();

    private static final HashMap<String, Double> balance = new HashMap<>();
    private static final HashMap<String, Short> cards = new HashMap<>();

    private static void CreateAccount() {
        final String IIN = "400000";
        final String identifier = String.valueOf(ThreadLocalRandom.current().nextLong(
                1_000_000_000L
        ));
        final String checksum = String.valueOf(rand.nextInt(10));
        final Short pin = (short) rand.nextInt(1_000);
        final String curdNumber = String.format("%s%s%s", IIN, identifier, checksum);
        cards.put(curdNumber, pin);
        balance.put(curdNumber, 0.0);
        System.out.println("Your card have been created");
        System.out.println("Your card number:");
        System.out.println(curdNumber);
        System.out.println("Your card PIN:");
        System.out.format("%04d\n", pin);
    }

    private static boolean LogIntoAccount(String cardNumber) {
        System.out.println("Enter your PIN:");
        Scanner scanner = new Scanner(System.in);

        short pin = scanner.nextShort();
        if (cards.containsKey(cardNumber)) {
            return cards.get(cardNumber) == pin;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        exit:
        while (true) {
            System.out.println("1. Create account");
            System.out.println("2. Log into account");
            System.out.println("0. Exit");

            int op = scanner.nextInt();
            if (op == CREATE_ACCOUNT) {
                CreateAccount();
            } else if (op == LOG_INTO_ACCOUNT) {
                System.out.println("Enter your card number:");
                String number = scanner.next();
                if (LogIntoAccount(number)) {
                    System.out.println("You have successfully logged in!");
                    while (true) {
                        System.out.println("1. Balance");
                        System.out.println("2. Log out");
                        System.out.println("0. Exit");

                        op = scanner.nextInt();

                        if (op == EXIT) {
                            break exit;
                        } else if (op == BALANCE) {
                            System.out.format("Balance: %f\n", balance.get(number));
                        } else if (op == LOG_OUT) {
                            System.out.println("You have successfully logged out!");
                            break;
                        }
                    }
                } else {
                    System.out.println("Wrong card number or PIN!");
                }
            } else if (op == EXIT) {
                break;
            }
        }
        System.out.println("Bye!");
    }
}
