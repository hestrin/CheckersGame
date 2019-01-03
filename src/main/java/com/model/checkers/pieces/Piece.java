package com.model.checkers.pieces;

public class Piece {

    private String color;
    private boolean promoted;

    public Piece(Piece copy) {
        color = copy.color;
        promoted = copy.promoted;
    }

    public String getSign() {
        return color.equals("B") ? (isPromoted() ? "\u25A0" : "\u25CF") : (isPromoted() ? "\u25A1" : "\u25CB");
    }

    public void promote() {
        this.promoted = true;
    }

    public Piece(String aColor) {
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

}
