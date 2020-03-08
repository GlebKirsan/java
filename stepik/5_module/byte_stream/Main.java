import java.io.*;

public class Main {
    public static int checkSumOfStream(InputStream inputStream) throws IOException {
        int controlSum = 0;

        int read = inputStream.read();
        while (read > 0) {
            controlSum = Integer.rotateLeft(controlSum, 1) ^ read;
            read = inputStream.read();
        }

        return controlSum;
    }

    public static void main(String[] args) {
        InputStream input = new ByteArrayInputStream(new byte[]{0x33, 0x45, 0x01});
        try {
            System.out.println(checkSumOfStream(input));
        } catch (IOException e) {

        }
    }
}
