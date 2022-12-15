package aoc2022.day12;

import java.nio.charset.StandardCharsets;


public class StatePartB extends State<StatePartB> {

    private final static byte GOAL = "a".getBytes(StandardCharsets.UTF_8)[0];

    private StatePartB(Position position, StatePartB previousState, Map map) {
        super(position, previousState, map);
    }

    public StatePartB(Position position, Map map) {
        super(position, null, map);
    }

    @Override
    protected boolean isNeighborValid(Position position) {
        return getMap().getElevation(getPosition()) <= getMap().getElevation(position) + 1;
    }

    @Override
    protected StatePartB newNeighbor(Position position) {
        return new StatePartB(position, this, getMap());
    }

    @Override
    public int getDistanceFromGoal() {
        return 0;
    }

    @Override
    public boolean isGoal() {
        return getMap().getElevation(getPosition()) == GOAL;
    }
}
