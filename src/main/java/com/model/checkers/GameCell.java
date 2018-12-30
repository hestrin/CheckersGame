package com.model.checkers;

import com.model.checkers.pieces.CheckersPiece;

public class GameCell {
    private int rank;
    private int file;
    private CheckersPiece piece;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }

    public boolean isOccupied() {
        return piece != null;
    }

    public boolean isOccupiedByWhitePiece() {
        return piece != null && Player.WHITE.getColor().equals(piece.getColor());
    }

    public boolean isOccupiedByBlackPiece() {
        return piece != null && Player.BLACK.getColor().equals(piece.getColor());
    }

    public boolean isUnoccupied() {
        return piece == null;
    }

    public CheckersPiece getPiece() {
        return piece;
    }

    public void setPiece(CheckersPiece piece) {
        this.piece = piece;
    }
}
