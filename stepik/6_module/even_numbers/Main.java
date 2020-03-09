import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in  = new Scanner(System.in);

        List<Integer> memory = new LinkedList<>();
        int i = 1;
        while (in.hasNextInt()) {
            ++i;
            if (i % 2 == 0) {
                in.nextInt();
                continue;
            }
            memory.add(in.nextInt());
        }
        Collections.reverse(memory);
        for (int value : memory) {
            System.out.print(value + " ");
        }


    }
}
