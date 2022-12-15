package aoc2022.day12;

public class StatePartA extends State<StatePartA> {


    private final int distanceFromGoal;


    private StatePartA(Position position, StatePartA previousState, Map map) {
        super(position, previousState, map);
        this.distanceFromGoal = position.getDistance(map.getGoal());
    }

    public StatePartA(Position position, Map map) {
        this(position, null, map);
    }


    @Override
    public int getDistanceFromGoal() {
        return distanceFromGoal;
    }

    @Override
    public boolean isGoal() {
        return distanceFromGoal == 0;
    }

    @Override
    protected boolean isNeighborValid(Position position) {
        return getMap().getElevation(getPosition()) + 1 >= getMap().getElevation(position);
    }

    @Override
    protected StatePartA newNeighbor(Position position) {
        return new StatePartA(position, this, getMap());
    }


}
