package com.model.checkers;

public enum Player {
    WHITE("W", 1), BLACK("B", -1), NONE("N", 0);

    private String color;
    private int direction;

    Player(String aColor, int aDirection) {
        this.direction = aDirection;
        this.color =  aColor;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
