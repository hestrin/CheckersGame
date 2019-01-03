package com.model.checkers;

public class GamePosition {

    private GameCell[][] gameBoard;

    private Player lastMoved;
    private boolean gameFinished;
    private Player winner;

    public GamePosition(GamePosition copy) {
        lastMoved = copy.lastMoved;
        winner = copy.winner;
        gameFinished = copy.gameFinished;
        gameBoard = new GameCell[8][8];
        initializeGameBoard(gameBoard);
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++) {
                gameBoard[i][j] = new GameCell(copy.gameBoard[i][j]);
            }
        }
    }

    public GamePosition() {
        gameBoard = new GameCell[8][8];
        gameFinished = false;
        winner = null;
        initializeGameBoard(gameBoard);
        lastMoved = Player.BLACK;
    }

    private void initializeGameBoard(GameCell[][] gameBoard) {
        for(int i=0; i<8; i++)
            gameBoard[i] = new GameCell[8];

        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++) {
                gameBoard[i][j] = new GameCell();
            }
        }
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

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
