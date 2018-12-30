package com.mainApp.services.checkers.impl;

import com.mainApp.services.checkers.CheckersService;
import com.model.board.BoardHelper;
import com.model.checkers.*;
import com.model.checkers.pieces.CheckersPiece;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@ComponentScan
public class CheckersServiceImpl implements CheckersService {

    private GamePostion currentPostion;

    public String applyPostion(String board) {
        initializePositionIfNeeded();
        makeNextMove(currentPostion, board);
        return applyPositionTo(board);
    }

    private String applyPositionTo(String board) {
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++) {
                if(currentPostion.getGameBoard()[i][j].isOccupied())
                    board = placePieceOnTheBoard(board, i, j);
                }
        }
        return board;
    }

    private String placePieceOnTheBoard(String board, int i, int j) {
        int index = BoardHelper.findCellIndex(i,j);
        return board.substring(0, index) +
                (currentPostion.getGameBoard()[i][j].getPiece().getColor().equals("B") ?
                        ( currentPostion.getGameBoard()[i][j].getPiece().isPromoted() ? "\u25A0" : "\u25CF") :
                        ( currentPostion.getGameBoard()[i][j].getPiece().isPromoted() ? "\u25A1" : "\u25CB"))
                + board.substring(index+1, board.length());
    }

    private void initializePositionIfNeeded() {
        if(currentPostion == null || currentPostion.isGameFinished())
            initializePosition();
    }

    private void initializePosition() {
        currentPostion = new GamePostion();
        CheckersBoardInitializer.initializePieces(currentPostion);
    }

    private void makeNextMove(GamePostion currentPostion, String board) {
        Player nextPlayerToMove = currentPostion.getLastMoved().equals(Player.BLACK) ? Player.WHITE : Player.BLACK;
        List<CheckersMove> allAllowedMoves = getListOfAllAvailabeMoves(nextPlayerToMove);
        if(allAllowedMoves != null && allAllowedMoves.size() > 0){
            if(Player.WHITE.equals(nextPlayerToMove))
                sortAllowedMoves(allAllowedMoves);
            makeMove(allAllowedMoves.get(0));
        }
        else {
            this.currentPostion.setGameFinished(true);
            this.currentPostion.setLastWinner(currentPostion.getLastMoved());
            board = ((Player.WHITE.equals(currentPostion.getLastWinner()) ? "WHITE" : "BLACK") +  " WINS");
            System.out.println(board);
        }
    }

    private void sortAllowedMoves(List<CheckersMove> allAllowedMoves) {
        allAllowedMoves.sort(new Comparator<CheckersMove>() {
            @Override
            public int compare(CheckersMove o1, CheckersMove o2) {
                return o1.isTaking() ? -1 : (o2.isTaking() ? 1 : 0);
            }
        });
    }

    private void makeMove(CheckersMove move) {
        CheckersPiece piece = currentPostion.getGameBoard()[move.getiStart()][move.getjStart()].getPiece();
        currentPostion.getGameBoard()[move.getiStart()+move.getiDelta()][move.getjStart()+move.getjDelta()].
                setPiece(piece);
        currentPostion.getGameBoard()[move.getiStart()][move.getjStart()].setPiece(null);
        if(move.isTaking())
            currentPostion.getGameBoard()[move.getiStart()+move.getiDelta()/2][move.getjStart()+move.getjDelta()/2].setPiece(null);
        if(pawnReachedLastLine(move, piece))
            promotePiece(piece);
        currentPostion.switchLastMoved();
    }

    private void promotePiece(CheckersPiece piece) {
        piece.setPromoted(true);
    }

    private boolean pawnReachedLastLine(CheckersMove move, CheckersPiece piece) {
        return (move.getiStart()+move.getiDelta() == 0 || move.getiStart()+move.getiDelta() == 7) && !piece.isPromoted();
    }

    private List<CheckersMove> getListOfAllAvailabeMoves(Player nextPlayerToMove) {
        if(nextPlayerToMove.equals(Player.WHITE))
            return getWhiteMoves();
        else if(nextPlayerToMove.equals(Player.BLACK))
            return getBlackMoves();
        return null;
    }

    private List<CheckersMove> getWhiteMoves() {
        List<CheckersMove> whiteCheckersMoves = new ArrayList<CheckersMove>();
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++) {
                if(currentPostion.getGameBoard()[i][j].isOccupiedByWhitePiece()) {
                    if(canWhitePieceMoveLeft(i + 1, j - 1)) {
                        CheckersMove cm = createMove(i, j, 1, -1);
                        whiteCheckersMoves.add(cm);
                    }
                    if(canWhitePieceMoveLeft(i + 1, j+1)) {
                        CheckersMove cm = createMove(i, j, 1, 1);
                        whiteCheckersMoves.add(cm);
                    }
                    if(0 <= i+1 && i+1 <= 7 && 0 <= i+2 && i+2 <= 7 &&
                            0 <= j-1 && j-1 <= 7 && 0 <= j-2 && j-2 <= 7 &&
                            currentPostion.getGameBoard()[i+1][j-1].isOccupiedByBlackPiece() &&
                            currentPostion.getGameBoard()[i+2][j-2].isUnoccupied()
                            ) {
                        CheckersMove cm = new CheckersMove();
                        cm.setiStart(i);
                        cm.setjStart(j);
                        cm.setiDelta(2);
                        cm.setjDelta(-2);
                        cm.setTaking(true);
                        whiteCheckersMoves.add(cm);
                    }
                    if(0 <= i+1 && i+1 <= 7 && 0 <= i+2 && i+2 <= 7 &&
                            0 <= j+1 && j+1 <= 7 && 0 <= j+2 && j+2 <= 7 &&
                            currentPostion.getGameBoard()[i+1][j+1].isOccupiedByBlackPiece() &&
                            currentPostion.getGameBoard()[i+2][j+2].isUnoccupied()
                            ) {
                        CheckersMove cm = new CheckersMove();
                        cm.setiStart(i);
                        cm.setjStart(j);
                        cm.setiDelta(2);
                        cm.setjDelta(2);
                        cm.setTaking(true);
                        whiteCheckersMoves.add(cm);
                    }
                }
            }
        }
        return whiteCheckersMoves;
    }

    private CheckersMove createMove(int i, int j, int i2, int i3) {
        CheckersMove cm = new CheckersMove();
        cm.setiStart(i);
        cm.setjStart(j);
        cm.setiDelta(i2);
        cm.setjDelta(i3);
        cm.setTaking(false);
        return cm;
    }

    private boolean canWhitePieceMoveLeft(int i2, int i3) {
        return 0 <= i2 && i2 <= 7 && 0 <= i3 && i3 <= 7 &&
                !currentPostion.getGameBoard()[i2][i3].isOccupied();
    }

    private boolean canWhitePieceTakeOnLeft(int i2, int i3) {
        return 0 <= i2 && i2 <= 7 && 0 <= i3 && i3 <= 7 &&
                !currentPostion.getGameBoard()[i2][i3].isOccupied();
    }

    private List<CheckersMove> getBlackMoves() {
        List<CheckersMove> blackCheckersMoves = new ArrayList<CheckersMove>();
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++) {
                if(currentPostion.getGameBoard()[i][j].isOccupiedByBlackPiece()) {
                    if(canWhitePieceMoveLeft(i-1, j - 1)) {
                        CheckersMove cm = createMove(i, j, -1, -1);
                        blackCheckersMoves.add(cm);
                    }
                    if(canWhitePieceMoveLeft(i-1, j+1)) {
                        CheckersMove cm = createMove(i, j, -1, 1);
                        blackCheckersMoves.add(cm);
                    }
                    if(0 <= i-1 && i-1 <= 7 && 0 <= i-2 && i-2 <= 7 &&
                            0 <= j-1 && j-1 <= 7 && 0 <= j-2 && j-2 <= 7 &&
                            currentPostion.getGameBoard()[i-1][j-1].isOccupiedByWhitePiece() &&
                            currentPostion.getGameBoard()[i-2][j-2].isUnoccupied()
                            ) {
                        CheckersMove cm = new CheckersMove();
                        cm.setiStart(i);
                        cm.setjStart(j);
                        cm.setiDelta(-2);
                        cm.setjDelta(-2);
                        cm.setTaking(true);
                        blackCheckersMoves.add(cm);
                    }
                    if(0 <= i-1 && i-1 <= 7 && 0 <= i-2 && i-2 <= 7 &&
                            0 <= j+1 && j+1 <= 7 && 0 <= j+2 && j+2 <= 7 &&
                            currentPostion.getGameBoard()[i-1][j+1].isOccupiedByWhitePiece() &&
                            currentPostion.getGameBoard()[i-2][j+2].isUnoccupied()
                            ) {
                        CheckersMove cm = new CheckersMove();
                        cm.setiStart(i);
                        cm.setjStart(j);
                        cm.setiDelta(-2);
                        cm.setjDelta(2);
                        cm.setTaking(true);
                        blackCheckersMoves.add(cm);
                    }
                }
            }
        }
        return blackCheckersMoves;
    }
}
