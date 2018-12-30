package com.model.checkers;

import com.model.checkers.pieces.CheckersPiece;

public class CheckersMove {

    private CheckersPiece piece;
    private GameCell start;
    private GameCell end;
    private int iStart;
    private int jStart;
    private int iDelta;
    private int jDelta;
    private boolean isTaking;
    private Player player;


    public CheckersPiece getPiece() {
        return piece;
    }

    public void setPiece(CheckersPiece piece) {
        this.piece = piece;
    }

    public GameCell getStart() {
        return start;
    }

    public void setStart(GameCell start) {
        this.start = start;
    }

    public GameCell getEnd() {
        return end;
    }

    public void setEnd(GameCell end) {
        this.end = end;
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

    public boolean isTaking() {
        return isTaking;
    }

    public void setTaking(boolean taking) {
        isTaking = taking;
    }

    public int getiStart() {
        return iStart;
    }

    public void setiStart(int iStart) {
        this.iStart = iStart;
    }

    public int getjStart() {
        return jStart;
    }

    public void setjStart(int jStart) {
        this.jStart = jStart;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
