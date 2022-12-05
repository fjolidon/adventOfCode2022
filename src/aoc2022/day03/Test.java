package aoc2022.day03;

public class Test {

    public static void main(String[] args) {
        long a = 1176825438228L;
        long b = 26946625355968L;

        long c = 8589934592L;

        System.out.println(formatBinary(a));
        System.out.println(formatBinary(b));
        System.out.println(formatBinary(c));

        long d = 1099511627776L;

        System.out.println(formatBinary(d));

        System.out.println(Util.log(d));
    }

    public static String formatBinary(long value) {
        return String.format("%64s", Long.toBinaryString(value)).replace(' ', '0');
    }
}
