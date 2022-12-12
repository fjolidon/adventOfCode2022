package aoc2022.day10;

import java.util.Arrays;


public class CRT {

    private final char[][] pixels;
    private int x = 0;
    private int y = 0;

    public CRT(int width, int height) {
        pixels = new char[height][width];
    }


    public void processCycle(int xValue) {
        if (y < pixels.length) {
            pixels[y][x] = (xValue >= x - 1 && xValue <= x + 1) ? '#' : '.';
            ++x;
            if (x >= pixels[y].length) {
                x = 0;
                ++y;
            }
        }
    }


    public String toString() {
        return Arrays.stream(pixels).reduce(
                "",
                (str, array) -> str += new String(array) + "\n",
                (s1, s2) -> s1 + s2);
    }

}
