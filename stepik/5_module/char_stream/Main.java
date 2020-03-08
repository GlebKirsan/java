import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Main {
    public static String readAsString(InputStream inputStream, Charset charset) throws IOException {
        InputStreamReader stream = new InputStreamReader(inputStream, charset);
        StringBuilder sb = new StringBuilder();
        int read = stream.read();
        while (read >= 0) {
            sb.append((char) read);
            read = stream.read();
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        byte[] bytes = {48, 49, 50, 51};
        InputStream input = new ByteArrayInputStream(bytes);
        try {
            System.out.print(readAsString(input, StandardCharsets.US_ASCII));
        } catch (IOException e) {
        }

    }
}