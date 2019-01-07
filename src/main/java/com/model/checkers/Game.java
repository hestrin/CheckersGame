package com.model.checkers;

import com.mainApp.services.checkers.impl.BoardServiceImpl;
import com.model.checkers.pieces.MoveAnalyzer;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static final String ALL_FILES = "abcdefgh";

    private Square[][] board;
    private Player lastMoved;
    private boolean gameFinished;
    private Player winner;
    private List<Move> history;

    public String drawGame() {
        return (new BoardServiceImpl()).draw(this);
    }

    public int getWhitePiecesCount() {
        return 1;
    }


    public Game(Game toCopy) {
        lastMoved = toCopy.lastMoved;
        winner = toCopy.winner;
        gameFinished = toCopy.gameFinished;
        copyHistory(toCopy);
        board = new Square[8][8];
        initializeGameBoard(board);
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++) {
                board[i][j] = new Square(toCopy.board[i][j]);
            }
        }
    }

    private void copyHistory(Game toCopy) {
        toCopy.history = new ArrayList<>();
        toCopy.history.forEach(hm->history.add(new Move(hm)));
    }

    public Game() {
        board = new Square[8][8];
        gameFinished = false;
        winner = null;
        initializeGameBoard(board);
        lastMoved = Player.BLACK;
        history = new ArrayList<>();
    }

    public List<Move> getAllowedMoves() {
        Player player = getPlayerOnTheMove();
        List<Move> moves = new ArrayList<>();
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++) {
                if(getBoard()[i][j].isOccupiedBy(player)) {
                    if(getBoard()[i][j].getPiece().isPromoted()){
                        moves.addAll(MoveAnalyzer.getKingMoves(this, i,j, player));
                    }
                    else
                        moves.addAll(MoveAnalyzer.getPawnMoves(this, player, i, j));
                }
            }
        }
        return moves;

    }

    public void addToHistory(Move move) {
        if(history == null)
            history = new ArrayList<>();
        history.add(move);
    }

    public Square getSquareAt(char file, int rank) {
        if(getBoard()  == null)
            return null;
        return getBoard()[toIndexI(rank)][toIndexJ(file)];
    }

    private int toIndexI(int rank) {
        return rank-1;
    }

    private int toIndexJ(char file) {
        return ALL_FILES.indexOf(file);
    }

    private void initializeGameBoard(Square[][] gameBoard) {
        for(int i=0; i<8; i++)
            gameBoard[i] = new Square[8];

        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++) {
                gameBoard[i][j] = new Square();
            }
        }
    }

    public void completeMove() {
        if(Player.BLACK.equals(lastMoved))
            lastMoved = Player.WHITE;
        else if(Player.WHITE.equals(lastMoved))
            lastMoved = Player.BLACK;
    }

    public Square[][] getBoard() {
        return board;
    }

    public Player getLastMoved() {
        return lastMoved;
    }

    public Player getPlayerOnTheMove() {
        return lastMoved.getOpponent();
    }

    public void setLastMoved(Player lastMoved) {
        this.lastMoved = lastMoved;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
