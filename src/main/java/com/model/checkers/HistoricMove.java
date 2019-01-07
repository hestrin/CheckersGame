package com.model.checkers;

public class HistoricMove extends Move {

    private int promotedWhitePiecesCount;
    private int promotedBlackPiecesCount;
    private int whitePiecesCount;
    private int blackPiecesCount;

    public HistoricMove(Move move, Game game) {
        this.whitePiecesCount = game.getWhitePiecesCount();
    }

    public int getPromotedWhitePiecesCount() {
        return promotedWhitePiecesCount;
    }

    public void setPromotedWhitePiecesCount(int promotedWhitePiecesCount) {
        this.promotedWhitePiecesCount = promotedWhitePiecesCount;
    }

    public int getPromotedBlackPiecesCount() {
        return promotedBlackPiecesCount;
    }

    public void setPromotedBlackPiecesCount(int promotedBlackPiecesCount) {
        this.promotedBlackPiecesCount = promotedBlackPiecesCount;
    }

    public int getWhitePiecesCount() {
        return whitePiecesCount;
    }

    public void setWhitePiecesCount(int whitePiecesCount) {
        this.whitePiecesCount = whitePiecesCount;
    }

    public int getBlackPiecesCount() {
        return blackPiecesCount;
    }

    public void setBlackPiecesCount(int blackPiecesCount) {
        this.blackPiecesCount = blackPiecesCount;
    }
}
