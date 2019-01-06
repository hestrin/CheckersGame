package com.model.checkers;

import com.model.checkers.pieces.Piece;

public class Square {
//    private int rank;
//    private int file;
    private Piece piece;

    public Square() {}

    public Square(Square copy) {
//        rank = copy.rank;
//        file = copy.file;
        piece = copy.piece != null ? new Piece(copy.piece) : null;
    }

    public boolean isOccupied() {
        return piece != null;
    }

    public boolean isOccupiedBy(Player playerColor) {
        return piece != null && playerColor.getColor().equals(piece.getColor());
    }

    public boolean isUnoccupied() {
        return piece == null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
