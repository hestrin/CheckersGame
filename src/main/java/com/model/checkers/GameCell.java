package com.model.checkers;

import com.model.checkers.pieces.Piece;

public class GameCell {
    private int rank;
    private int file;
    private Piece piece;

    public GameCell() {
    }

    public GameCell(GameCell copy) {
        rank = copy.rank;
        file = copy.file;
        piece = copy.piece != null ? new Piece(copy.piece) : null;
    }

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
