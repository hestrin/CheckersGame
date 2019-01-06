package com.mainApp.services.checkers.impl;

import com.mainApp.services.checkers.CheckersService;
import com.model.checkers.CheckersBoardInitializer;
import com.model.checkers.Game;
import com.model.checkers.Move;
import com.model.checkers.evaluation.Evaluator;
import com.model.checkers.pieces.MoveAnalyzer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan
public class CheckersServiceImpl implements CheckersService {

    private Game position;

    public Game nextMove() {
        if(position == null)
            initializePosition();
        else if(!position.isGameFinished())
            makeNextMove(position);
        return position;
    }

    public Game restartGame() {
        initializePosition();
        return position;
    }

    public Game showGame() {
        return position;
    }

    private void initializePosition() {
        position = new Game();
        CheckersBoardInitializer.initializePieces(position);
    }

    private void makeNextMove(Game game) {
        List<Move> allowedMoves = game.getAllowedMoves();
        if(allowedMoves != null && allowedMoves.size() > 0) {
            Move bestMove = Evaluator.getBestMove(game);
            this.position = MoveAnalyzer.makeMove(this.position, bestMove);
        }
        else {

            this.position.setGameFinished(true);
            this.position.setWinner(game.getLastMoved());
        }
    }
}
