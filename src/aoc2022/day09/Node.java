package aoc2022.day09;

public class Node {
    private int x = 0;
    private int y = 0;


    public void move(Direction direction){
        x += direction.getX();
        y += direction.getY();
    }

    public void moveToward(Node otherNode) {

        if (getDistX(otherNode) > 1 || getDistY(otherNode) > 1) { // need to move
            if (otherNode.x > x) {
                ++x;
            } else if (otherNode.x < x) {
                --x;
            }
            if (otherNode.y > y) {
                ++y;
            } else if (otherNode.y < y) {
                --y;
            }
        }
    }

    private int getDistX(Node otherNode) {
        return Math.abs(otherNode.x - x);
    }

    private int getDistY(Node otherNode) {
        return Math.abs(otherNode.y - y);
    }

    public Point getCurrentPosition() {
        return new Point(x, y);
    }


}
