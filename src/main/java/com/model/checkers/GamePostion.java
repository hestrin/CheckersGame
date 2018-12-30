package com.model.checkers;

public class GamePostion {

    private GameCell[][] gameBoard;

    private Player lastMoved;
    private boolean gameFinished;
    private Player lastWinner;

    public GamePostion() {
        gameBoard = new GameCell[8][8];
        gameFinished = false;
        lastWinner = null;

        for(int i=0; i<8; i++)
            gameBoard[i] = new GameCell[8];

        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++) {
                gameBoard[i][j] = new GameCell();
            }
        }
        lastMoved = Player.BLACK;
    }

    public void switchLastMoved() {
        if(Player.BLACK.equals(lastMoved))
            lastMoved = Player.WHITE;
        else if(Player.WHITE.equals(lastMoved))
            lastMoved = Player.BLACK;
    }

    public GameCell[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameCell[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Player getLastMoved() {
        return lastMoved;
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

    public Player getLastWinner() {
        return lastWinner;
    }

    public void setLastWinner(Player lastWinner) {
        this.lastWinner = lastWinner;
    }
}
