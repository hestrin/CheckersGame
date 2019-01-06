package com.model.checkers;

import com.model.checkers.pieces.Piece;

public class CheckersBoardInitializer {
    public static void initializePieces(Game position) {
        position.setLastMoved(Player.BLACK);
        initializeWhitePieces(position);
        initializeBlackPieces(position);
    }

    private static void initializeWhitePieces(Game position) {
//        position.getSquareAt('d',2).setPiece(new Piece(Player.WHITE.getColor()));
//        position.getSquareAt('h',4).setPiece(new Piece(Player.WHITE.getColor()));
//        position.getSquareAt('a',7).setPiece(new Piece(Player.WHITE.getColor()));
//        position.getSquareAt('a',3).setPiece(new Piece(Player.WHITE.getColor()));
//        position.getSquareAt('a',1).setPiece(new Piece(Player.WHITE.getColor()));
//        position.getSquareAt('a',7).setPiece(new Piece(Player.WHITE.getColor()));
//        position.getSquareAt('b',4).setPiece(new Piece(Player.WHITE.getColor()));
//        position.getSquareAt('e',1).setPiece(new Piece(Player.WHITE.getColor()));
//        position.getSquareAt('g',1).setPiece(new Piece(Player.WHITE.getColor()));

//        position.getSquareAt('a',1).setPiece(new Piece(Player.WHITE.getColor()));
//        position.getSquareAt('c',1).setPiece(new Piece(Player.WHITE.getColor()));
//        position.getSquareAt('e',1).setPiece(new Piece(Player.WHITE.getColor()));
//        position.getSquareAt('g',1).setPiece(new Piece(Player.WHITE.getColor()));
//        position.getSquareAt('b',2).setPiece(new Piece(Player.WHITE.getColor()));
//        position.getSquareAt('d',2).setPiece(new Piece(Player.WHITE.getColor()));
//        position.getSquareAt('f',2).setPiece(new Piece(Player.WHITE.getColor()));
//        position.getSquareAt('h',2).setPiece(new Piece(Player.WHITE.getColor()));
//        position.getSquareAt('a',3).setPiece(new Piece(Player.WHITE.getColor()));
//        position.getSquareAt('c',3).setPiece(new Piece(Player.WHITE.getColor()));
//        position.getSquareAt('e',3).setPiece(new Piece(Player.WHITE.getColor()));
        position.getSquareAt('g',7).setPiece(new Piece(Player.WHITE.getColor()));
    }

    private static void initializeBlackPieces(Game position) {
//        position.getSquareAt('e',3).setPiece(new Piece(Player.BLACK.getColor()));
//        position.getSquareAt('e',5).setPiece(new Piece(Player.BLACK.getColor()));
//        position.getSquareAt('g',5).setPiece(new Piece(Player.BLACK.getColor()));
//        position.getSquareAt('e',7).setPiece(new Piece(Player.BLACK.getColor()));
//        position.getSquareAt('g',7).setPiece(new Piece(Player.BLACK.getColor()));

//        position.getSquareAt('b',8).setPiece(new Piece(Player.BLACK.getColor()));
//        position.getSquareAt('d',8).setPiece(new Piece(Player.BLACK.getColor()));
//        position.getSquareAt('f',8).setPiece(new Piece(Player.BLACK.getColor()));
//        position.getSquareAt('h',8).setPiece(new Piece(Player.BLACK.getColor()));
//        position.getSquareAt('a',7).setPiece(new Piece(Player.BLACK.getColor()));
//        position.getSquareAt('c',7).setPiece(new Piece(Player.BLACK.getColor()));
//        position.getSquareAt('e',7).setPiece(new Piece(Player.BLACK.getColor()));
//        position.getSquareAt('g',7).setPiece(new Piece(Player.BLACK.getColor()));
//        position.getSquareAt('b',6).setPiece(new Piece(Player.BLACK.getColor()));
//        position.getSquareAt('d',6).setPiece(new Piece(Player.BLACK.getColor()));
//        position.getSquareAt('f',6).setPiece(new Piece(Player.BLACK.getColor()));
        position.getSquareAt('f',2).setPiece(new Piece(Player.BLACK.getColor()));
    }
}
