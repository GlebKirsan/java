import java.io.IOException;

public class Main {
    public static final byte CAR_RET = 13;
    public static final byte NEW_LINE = 10;

    public static void main(String[] args) throws IOException {
        int prev = System.in.read();
        while (prev >= 0) {
            int next = System.in.read();
            if (prev != CAR_RET || next != NEW_LINE) {
                System.out.write(prev);
            }
            prev = next;
        }
        System.out.flush();
    }
}