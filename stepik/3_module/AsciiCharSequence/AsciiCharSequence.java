import java.lang.CharSequence;

public class AsciiCharSequence implements CharSequence {

    private final byte[] bytes;

    public AsciiCharSequence(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return new String(bytes);
    }

    @Override
    public AsciiCharSequence subSequence(int start, int finish) {
        int n = finish - start;
        byte[] sequence = new byte[n];
        System.arraycopy(bytes, start, sequence, 0, n);
        return new AsciiCharSequence(sequence);
    }

    @Override
    public char charAt(int index) {
        return (char) bytes[index];
    }

    @Override
    public int length() {
        return bytes.length;
    }
}