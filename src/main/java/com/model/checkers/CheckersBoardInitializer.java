package com.model.checkers;

import com.model.checkers.pieces.CheckersPiece;

public class CheckersBoardInitializer {
    public static void initializePieces(GamePostion position) {
        position.setLastMoved(Player.BLACK);
        initializeWhitePieces(position);
        initializeBlackPieces(position);
    }

    private static void initializeWhitePieces(GamePostion position) {
        position.getGameBoard()[0][0].setPiece(new CheckersPiece(Player.WHITE.getColor()));
        position.getGameBoard()[0][2].setPiece(new CheckersPiece(Player.WHITE.getColor()));
        position.getGameBoard()[0][4].setPiece(new CheckersPiece(Player.WHITE.getColor()));
        position.getGameBoard()[0][6].setPiece(new CheckersPiece(Player.WHITE.getColor()));
        position.getGameBoard()[1][1].setPiece(new CheckersPiece(Player.WHITE.getColor()));
        position.getGameBoard()[1][3].setPiece(new CheckersPiece(Player.WHITE.getColor()));
        position.getGameBoard()[1][5].setPiece(new CheckersPiece(Player.WHITE.getColor()));
        position.getGameBoard()[1][7].setPiece(new CheckersPiece(Player.WHITE.getColor()));
        position.getGameBoard()[2][0].setPiece(new CheckersPiece(Player.WHITE.getColor()));
        position.getGameBoard()[2][2].setPiece(new CheckersPiece(Player.WHITE.getColor()));
        position.getGameBoard()[2][4].setPiece(new CheckersPiece(Player.WHITE.getColor()));
        position.getGameBoard()[2][6].setPiece(new CheckersPiece(Player.WHITE.getColor()));
    }

    private static void initializeBlackPieces(GamePostion position) {
        position.getGameBoard()[7][1].setPiece(new CheckersPiece(Player.BLACK.getColor()));
        position.getGameBoard()[7][3].setPiece(new CheckersPiece(Player.BLACK.getColor()));
        position.getGameBoard()[7][5].setPiece(new CheckersPiece(Player.BLACK.getColor()));
        position.getGameBoard()[7][7].setPiece(new CheckersPiece(Player.BLACK.getColor()));
        position.getGameBoard()[6][0].setPiece(new CheckersPiece(Player.BLACK.getColor()));
        position.getGameBoard()[6][2].setPiece(new CheckersPiece(Player.BLACK.getColor()));
        position.getGameBoard()[6][4].setPiece(new CheckersPiece(Player.BLACK.getColor()));
        position.getGameBoard()[6][6].setPiece(new CheckersPiece(Player.BLACK.getColor()));
        position.getGameBoard()[5][1].setPiece(new CheckersPiece(Player.BLACK.getColor()));
        position.getGameBoard()[5][3].setPiece(new CheckersPiece(Player.BLACK.getColor()));
        position.getGameBoard()[5][5].setPiece(new CheckersPiece(Player.BLACK.getColor()));
        position.getGameBoard()[5][7].setPiece(new CheckersPiece(Player.BLACK.getColor()));
    }

}
