package com.model.checkers;

public enum Player {
    WHITE("W", 1, PlayerStrategy.TWO_STEP_PREDICTION_WITH_CUTS),
    BLACK("B", -1, PlayerStrategy.EIGHT_STEP_PREDICTION_WITH_CUTS),
    NONE("N", 0);

    private String color;
    private int direction;
    private PlayerStrategy strategy;

    Player(String aColor, int aDirection) {
        this.direction = aDirection;
        this.color =  aColor;
    }

    Player(String aColor, int aDirection, PlayerStrategy aStrategy) {
        this.direction = aDirection;
        this.color =  aColor;
        this.strategy = aStrategy;
    }

    public Player getOpponent() {
        if(this.equals(Player.BLACK))
            return Player.WHITE;
        else if(this.equals(Player.WHITE))
            return Player.BLACK;
        return Player.NONE;
    }

    public PlayerStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(PlayerStrategy strategy) {
        this.strategy = strategy;
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
