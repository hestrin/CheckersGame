package com.model.checkers;

import com.model.checkers.pieces.Piece;
import com.model.checkers.pieces.CapturedPiece;

import java.util.ArrayList;
import java.util.List;

public class Move {

    private static final String files = "abcdefgh";

    private Piece piece;
    private int iStart;
    private int jStart;
    private int iDelta;
    private int jDelta;
    private boolean isTaking;
    private boolean isKingMove;
    List<CapturedPiece> capturedPieces;


    @Override
    public String toString() {
        return " from " + toFile(jStart) + toRank(iStart) + " ---> " + toFile(jStart + jDelta) + toRank(iStart+iDelta)  +
                (isTaking ? " +:" : "");
    }

    private String toFile(int j) {
        return files.substring(j, j+1);
    }

    private String toRank(int i) {
        return Integer.toString(i+1);
    }

    public Move(Move toCopy) {
        piece = new Piece(toCopy.piece);
        iStart = toCopy.iStart;
        jStart = toCopy.jStart;
        iDelta = toCopy.iDelta;
        jDelta = toCopy.jDelta;
        isTaking = toCopy.isTaking;
        isKingMove = toCopy.isKingMove;
    }

    public Move() {

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

    public List<CapturedPiece> getCapturedPieces() {
        return capturedPieces;
    }

    public int getiDelta() {
        return iDelta;
    }

    public int getjDelta() {
        return jDelta;
    }

    public boolean isTaking() {
        return isTaking;
    }

    public int getiStart() {
        return iStart;
    }

    public int getjStart() {
        return jStart;
    }
}
