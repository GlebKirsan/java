package phonebook;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static String measuredTime(long time) {
        int ms = (int) time % 1000;
        int sec = (int) time / 1000;
        int min = sec / 60;
        sec %= 60;

        return String.format("%d min. %d sec. %d ms.", min, sec, ms);
    }

    static class PhoneNumber {

        String number;
        String name;
        String surname;

        public PhoneNumber(String number, String name, String surname) {
            this.number = number;
            this.name = name;
            this.surname = surname;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getFullName() {
            if (getSurname().isEmpty()) {
                return getName();
            } else {
                return getName() + " " + getSurname();
            }
        }

    }

    public static int findEntries(List<String> find, List<PhoneNumber> numbers) throws IOException {
        int count = 0;
        for (String number : find) {
            for (PhoneNumber phoneNumber : numbers) {
                if (number.equals(phoneNumber.getFullName())) {
                    ++count;
                    break;
                }
            }
        }

        return count;
    }

    public static List<PhoneNumber> readNumbers() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Dgehi\\Downloads\\directory.txt"));
        int size = lines.size();
        List<PhoneNumber> numbers = new ArrayList<>(size);

        for (String line : lines) {
            String[] parts = line.split(" ");
            PhoneNumber phoneNumber;
            if (parts.length == 2) {
                phoneNumber = new PhoneNumber(parts[0], parts[1], "");
            } else {
                phoneNumber = new PhoneNumber(parts[0], parts[1], parts[2]);
            }
            numbers.add(phoneNumber);
        }
        return numbers;
    }

    public static void main(String[] args) throws IOException {
        List<PhoneNumber> numbers = readNumbers();

        long start = System.currentTimeMillis();
        System.out.println("Start searching...");
        List<String> find = Files.readAllLines(Paths.get("C:\\Users\\Dgehi\\Downloads\\find.txt"));
        int found = findEntries(find, numbers);
        long diffTime = System.currentTimeMillis() - start;
        int totalToFind = find.size();
        String time = measuredTime(diffTime);
        System.out.printf("Found %d / %d entries. Time taken: %s", found, totalToFind, time);
    }
}
