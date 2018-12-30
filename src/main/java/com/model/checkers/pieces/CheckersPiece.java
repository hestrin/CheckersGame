package com.model.checkers.pieces;

import com.model.checkers.Player;

public class CheckersPiece {

    private String color;
    private boolean promoted;
    private Player player;
    private PieceType type;

    public CheckersPiece(String aColor) {
        this.color = aColor;
    }

    public boolean isPromoted() {
        return promoted;
    }

    public void setPromoted(boolean promoted) {
        this.promoted = promoted;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


}
