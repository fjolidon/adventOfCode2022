package aoc2022.day03;

public class RuckSackLinear extends Rucksack {

    private final long contentMaskFirst;
    private final long contentMaskSecond;
    private final long contentMaskFull;

    public RuckSackLinear(String input) {
        super(input);

        contentMaskFirst = computeMask(getFirstCompartment());
        contentMaskSecond = computeMask(getSecondCompartment());
        contentMaskFull = contentMaskFirst | contentMaskSecond;

    }

    private static long computeMask(String input) {
        long mask = 0;
        for (int i = 0; i<input.length(); ++i) {
            char c = input.charAt(i);

            int index = new Item(c).getPriority();
            mask |= 1L << index;
        }
        return mask;
    }

    @Override
    public Item getCommonItem() {
        return new Item(Util.binaryLog(getCommonContentMask(contentMaskFirst, contentMaskSecond, contentMaskFull)));
    }

    public long getMask() {
        return contentMaskFull;
    }

    public static long getCommonContentMask(long a, long b) {
        return getCommonContentMask(a, b, a|b);
    }
    public static long getCommonContentMask(long a, long b, long both) {
        return a ^ b ^ both;
    }
}
