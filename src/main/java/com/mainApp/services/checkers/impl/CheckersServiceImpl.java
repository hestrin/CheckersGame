package com.mainApp.services.checkers.impl;

import com.mainApp.services.checkers.CheckersService;
import com.model.board.Direction;
import com.model.checkers.CheckersBoardInitializer;
import com.model.checkers.GamePosition;
import com.model.checkers.Move;
import com.model.checkers.Player;
import com.model.checkers.evaluation.Evaluator;
import com.model.checkers.evaluation.MoveEvaluation;
import com.model.checkers.pieces.CapturedPiece;
import com.model.checkers.pieces.GameAnalyzer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@ComponentScan
public class CheckersServiceImpl implements CheckersService {

    private GamePosition position;

    public GamePosition nextMove() {
        if(position == null)
            initializePosition();
        else if(!position.isGameFinished())
            makeNextMove(position);
        return position;
    }

    public GamePosition restartGame() {
        initializePosition();
        return position;
    }

    public GamePosition showGame() {
        return position;
    }

    private void initializePosition() {
        position = new GamePosition();
        CheckersBoardInitializer.initializePieces(position);
    }

    private void makeNextMove(GamePosition position) {
        Player nextPlayer = position.getLastMoved().getOpponent();
        List<Move> allAllowedMoves = GameAnalyzer.getListOfAllAvailableMoves(position, nextPlayer);
        if(allAllowedMoves != null && allAllowedMoves.size() > 0) {
//            sortAllowedMoves(allAllowedMoves);
            List<MoveEvaluation> eMoves = new ArrayList<>();
            allAllowedMoves.forEach(move -> eMoves.add(new MoveEvaluation(move, Evaluator.evaluate(this.position, move, nextPlayer))));
            eMoves.sort((o1, o2) -> o1.getEvaluation() >= o2.getEvaluation() ? nextPlayer.getDirection()*-1 : nextPlayer.getDirection());
            eMoves.forEach(e -> System.out.println("Player: " + nextPlayer + " Move: " + e.getMove() + " evaluation: " + e.getEvaluation()));
            this.position = GameAnalyzer.makeMove(this.position, eMoves.get(0).getMove());
        }
        else {
            this.position.setGameFinished(true);
            this.position.setWinner(position.getLastMoved());
        }
    }

//    private void sortAllowedMoves(List<Move> allAllowedMoves) {
//        allAllowedMoves.sort((o1, o2) -> o1.isTaking() ?
//                (o2.isTaking() ? ((o1.getCapturedPieces().size() > o2.getCapturedPieces().size()) ? -1 : 1) : -1) : 1
//        );
//    }

}
