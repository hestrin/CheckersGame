package com.model.board;

import java.util.ArrayList;
import java.util.List;

public enum Direction {
    LEFT_UP(1,-1), LEFT_DOWN(-1,-1), RIGHT_UP(1,1), RIGHT_DOWN(-1,1);

    private int iDelta;
    private int jDelta;

    Direction(int aIDelta, int aJDelta) {
        this.iDelta = aIDelta;
        this.jDelta = aJDelta;
    }

    public Direction getOppositeDirection() {
        switch (this){
            case LEFT_UP:
                return RIGHT_DOWN;
            case LEFT_DOWN:
                return RIGHT_UP;
            case RIGHT_UP:
                return LEFT_DOWN;
            case RIGHT_DOWN:
                return LEFT_UP;
        }
        return LEFT_UP;
    }

    public static List<Direction> directionsWithout(Direction forbiddenDirection) {
        List<Direction> directions = new ArrayList<>();
        for(Direction d : Direction.values())
        {
            if(forbiddenDirection == null || !d.equals(forbiddenDirection))
                directions.add(d);
        }
        return directions;
    }

    public int getiDelta() {
        return iDelta;
    }

    public void setiDelta(int iDelta) {
        this.iDelta = iDelta;
    }

    public int getjDelta() {
        return jDelta;
    }

    public void setjDelta(int jDelta) {
        this.jDelta = jDelta;
    }
}
