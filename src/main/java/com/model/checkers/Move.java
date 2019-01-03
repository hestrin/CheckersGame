package com.model.checkers;

import com.model.checkers.pieces.Piece;
import com.model.checkers.pieces.CapturedPiece;

import java.util.ArrayList;
import java.util.List;

public class Move {

    private Piece piece;
    private int iStart;
    private int jStart;
    private int iDelta;
    private int jDelta;
    private boolean isTaking;
    private boolean isKingMove;
    private Player player;
    List<CapturedPiece> capturedPieces;

    @Override
    public String toString() {
        return "i:" + iStart + " j:"+jStart + " iDelta:" + iDelta + " jDelta:" + jDelta + " CAPTURE:" + isTaking;
    }

    public Move(int aIStart, int aJStart, int aIDelta, int aJDelta, boolean aIsTaking, boolean aIsKingMove) {
        this.iStart = aIStart;
        this.jStart = aJStart;
        this.iDelta = aIDelta;
        this.jDelta = aJDelta;
        this.isTaking = aIsTaking;
        this.isKingMove = aIsKingMove;
        if(aIsTaking)
            this.capturedPieces = new ArrayList<>();
    }

    public Move(int aIStart, int aJStart, int aIDelta, int aJDelta) {
        this.iStart = aIStart;
        this.jStart = aJStart;
        this.iDelta = aIDelta;
        this.jDelta = aJDelta;
        this.isTaking = false;
        this.isKingMove = false;
    }

    public boolean isKingMove() {
        return isKingMove;
    }

    public void setKingMove(boolean kingMove) {
        isKingMove = kingMove;
    }

    public List<CapturedPiece> getCapturedPieces() {
        return capturedPieces;
    }

    public void setCapturedPieces(List<CapturedPiece> capturedPieces) {
        this.capturedPieces = capturedPieces;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
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
