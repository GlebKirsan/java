public class ComplexNumber {

    private final double re;
    private final double im;

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;

        if (other == null || getClass() != other.getClass())
            return false;

        ComplexNumber o = (ComplexNumber) other;
        return Double.compare(re, o.re) == 0 &&
                Double.compare(im, o.im) == 0;
    }

    @Override
    public int hashCode() {
        final int prime = 53;
        int reHash = Double.hashCode(re);
        int imHash = Double.hashCode(re);
        return reHash * prime + imHash;
    }
}
