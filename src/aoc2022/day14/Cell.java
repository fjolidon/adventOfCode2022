package aoc2022.day14;

public class Cell {

    private Content content = Content.AIR;

    public enum Content {
        AIR('.'), ROCK('#'), SAND('o'), SAND_SOURCE('+');

        private final char draw;

        Content(char draw) {
            this.draw = draw;
        }

        public String toString() {
            return String.valueOf(draw);
        }
    }


    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public String toString() {
        return content.toString();
    }
}
