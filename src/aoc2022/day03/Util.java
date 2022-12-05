package aoc2022.day03;

public class Util {

    // how many steps is used when computing the log
    // the amount of step required for 64bits integers is 6 (because 2^6 = 64)
    private final static int LOG_MAX_STEPS = 6;
    private final static int LOG_ARRAY_SIZE = LOG_MAX_STEPS - 1;
    private final static long[] LOG_MASKS = new long[LOG_ARRAY_SIZE];
    private final static int[] LOG_SHIFTS = new int[LOG_ARRAY_SIZE];

    static {
        int shift = 2;
        long mask = 0x4;

        for (int i = 0; i < LOG_ARRAY_SIZE; ++i) {
            LOG_SHIFTS[i] = shift;
            LOG_MASKS[i] = mask;
            mask <<= shift;
            shift *= 2;
        }

        System.out.println();
    }

    /**
     * Implementation of integer binary logarithm, basically the reverse operation of multiplying by a power of 2 using
     * the left shift operator.
     * @param mask the integer we want to find the logarithmic value of
     * @return the logarithmic value of the input
     */
    public static int log(long mask) {
        int result = 0;

        for (int i = LOG_ARRAY_SIZE - 1; i >= 0 ; --i) {
            if (mask >= LOG_MASKS[i] || (mask < 0)) {
                mask >>>= LOG_SHIFTS[i];
                result += LOG_SHIFTS[i];
            }
        }
        return result + ((int)mask >>> 1);
    }
}
