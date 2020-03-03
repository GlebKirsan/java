public class Main {
    public static boolean booleanExpression(boolean a, boolean b, boolean c, boolean d) {
        return (a ^ b) && (c ^ d) || (a ^ c) && (b ^ d);
    }

    public static void main(String[] args) {
        boolean[] bools = new boolean[] { true, false };
        for (boolean a : bools) {
            for (boolean b : bools) {
                for (boolean c : bools) {
                    for (boolean d : bools) {
                        String a_str = String.format("a %b ", a);
                        String b_str = String.format("b %b ", b);
                        String c_str = String.format("c %b ", c);
                        String d_str = String.format("d %b = ", d);
                        System.out.print(a_str + b_str + c_str + d_str);
                        System.out.println(booleanExpression(a, b, c, d));
                    }
                }
            }

        }
    }
}