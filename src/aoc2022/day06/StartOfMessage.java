package aoc2022.day06;

import java.util.LinkedList;


public class StartOfMessage {

    private int characterMask = 0;
    private final LinkedList<Character> charBuffer = new LinkedList<>();

    public final static int DISTINCT_CHARS = 14;

    public boolean nextChar(char c) {
        charBuffer.addLast(c);
        addCharToMask(c);
        if (charBuffer.size() > DISTINCT_CHARS) {
            addCharToMask(charBuffer.removeFirst());
            return charCount() == DISTINCT_CHARS;
        }
        return false;
    }

    private void addCharToMask(char c) {
        characterMask ^= (1 << (c -'a'));
    }

    private int charCount() {
        int total = 0;
        int shiftMask = characterMask;
        for (int i = 0; i < 26; ++i) {
            total += (shiftMask & 1);
            shiftMask >>>= 1;
        }
        return total;
    }

}
