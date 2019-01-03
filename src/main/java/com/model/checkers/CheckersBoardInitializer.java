package com.model.checkers;

import com.model.checkers.pieces.Piece;

public class CheckersBoardInitializer {
    public static void initializePieces(GamePosition position) {
        position.setLastMoved(Player.BLACK);
        initializeWhitePieces(position);
        initializeBlackPieces(position);
    }

    private static void initializeWhitePieces(GamePosition position) {
//        position.getGameBoard()[3][3].setPiece(new Piece(Player.WHITE.getColor()));
//        position.getGameBoard()[3][3].getPiece().setPromoted(true);

        position.getGameBoard()[0][0].setPiece(new Piece(Player.WHITE.getColor()));
        position.getGameBoard()[0][2].setPiece(new Piece(Player.WHITE.getColor()));
        position.getGameBoard()[0][4].setPiece(new Piece(Player.WHITE.getColor()));
        position.getGameBoard()[0][6].setPiece(new Piece(Player.WHITE.getColor()));
        position.getGameBoard()[1][1].setPiece(new Piece(Player.WHITE.getColor()));
        position.getGameBoard()[1][3].setPiece(new Piece(Player.WHITE.getColor()));
        position.getGameBoard()[1][5].setPiece(new Piece(Player.WHITE.getColor()));
        position.getGameBoard()[1][7].setPiece(new Piece(Player.WHITE.getColor()));
        position.getGameBoard()[2][0].setPiece(new Piece(Player.WHITE.getColor()));
        position.getGameBoard()[2][2].setPiece(new Piece(Player.WHITE.getColor()));
        position.getGameBoard()[2][4].setPiece(new Piece(Player.WHITE.getColor()));
        position.getGameBoard()[2][6].setPiece(new Piece(Player.WHITE.getColor()));
    }

    private static void initializeBlackPieces(GamePosition position) {
//        position.getGameBoard()[4][4].setPiece(new Piece(Player.BLACK.getColor()));
//        position.getGameBoard()[7][7].setPiece(new Piece(Player.BLACK.getColor()));
//        position.getGameBoard()[1][5].setPiece(new Piece(Player.BLACK.getColor()));

        position.getGameBoard()[7][1].setPiece(new Piece(Player.BLACK.getColor()));
        position.getGameBoard()[7][3].setPiece(new Piece(Player.BLACK.getColor()));
        position.getGameBoard()[7][5].setPiece(new Piece(Player.BLACK.getColor()));
        position.getGameBoard()[7][7].setPiece(new Piece(Player.BLACK.getColor()));
        position.getGameBoard()[6][0].setPiece(new Piece(Player.BLACK.getColor()));
        position.getGameBoard()[6][2].setPiece(new Piece(Player.BLACK.getColor()));
        position.getGameBoard()[6][4].setPiece(new Piece(Player.BLACK.getColor()));
        position.getGameBoard()[6][6].setPiece(new Piece(Player.BLACK.getColor()));
        position.getGameBoard()[5][1].setPiece(new Piece(Player.BLACK.getColor()));
        position.getGameBoard()[5][3].setPiece(new Piece(Player.BLACK.getColor()));
        position.getGameBoard()[5][5].setPiece(new Piece(Player.BLACK.getColor()));
        position.getGameBoard()[5][7].setPiece(new Piece(Player.BLACK.getColor()));
    }

}
