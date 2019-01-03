package com.model.checkers.evaluation;

import com.model.checkers.GamePosition;
import com.model.checkers.Move;
import com.model.checkers.Player;
import com.model.checkers.pieces.GameAnalyzer;

public class Evaluator {

    private static final float MAX_PIECES_COUNT= 12f;

    public static float evaluate(GamePosition postion, Move move, Player player) {
        GamePosition copyPosition = new GamePosition(postion);
        copyPosition = GameAnalyzer.makeMove(copyPosition, move);
        float nextPositionValue = asses(copyPosition);

        return nextPositionValue;
//        + (float)(Math.random()*0.1f - 0.05f);
    }

    public static float asses(GamePosition postion) {
        int whitePieces = 0;
        int blackPieces = 0;
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                if(postion.getGameBoard()[i][j].isOccupiedBy(Player.WHITE)) {
                    whitePieces++;
                    if(postion.getGameBoard()[i][j].getPiece().isPromoted())
                        whitePieces++;
                }
                else if(postion.getGameBoard()[i][j].isOccupiedBy(Player.BLACK))
                {
                    blackPieces++;
                    if(postion.getGameBoard()[i][j].getPiece().isPromoted())
                        blackPieces++;
                }
            }
        }
        return whitePieces-blackPieces;
    }
}
