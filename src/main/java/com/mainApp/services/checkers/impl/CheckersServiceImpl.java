package com.mainApp.services.checkers.impl;

import com.mainApp.services.checkers.CheckersService;
import com.model.checkers.CheckersBoardInitializer;
import com.model.checkers.Game;
import com.model.checkers.Move;
import com.model.checkers.Player;
import com.model.checkers.evaluation.Evaluator;
import com.model.checkers.pieces.MoveAnalyzer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan
public class CheckersServiceImpl implements CheckersService {

    private Game game;

    public Game nextMove() {
        if(game == null)
            initializePosition();
        else if(!game.isGameFinished())
            makeNextMove();
        return game;
    }

    public Game restartGame() {
        initializePosition();
        return game;
    }

    public Game showGame() {
        return game;
    }

    private void initializePosition() {
        game = new Game();
        CheckersBoardInitializer.initializePieces(game);
    }

    private void makeNextMove() {
        if(game.hasNoMoreMoves()) {
            game.finishGame();
        }
        else if(game.isDrawByRepetition()) {
            game.finishGameByDraw();
        }
        else {
            Move bestMove = Evaluator.getBestMove(game);
            game = MoveAnalyzer.makeMove(game, bestMove);
        }
    }
}
