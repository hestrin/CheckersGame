package com.model.checkers.evaluation;

import com.model.checkers.Game;
import com.model.checkers.Move;
import com.model.checkers.Player;
import com.model.checkers.PlayerStrategy;
import com.model.checkers.pieces.MoveAnalyzer;

import java.util.ArrayList;
import java.util.List;

public class Evaluator {

    private static final float MAX_EVAL = 25f;

    public static Move getBestMove(Game game) {
        List<MoveEvaluation> evaluations = evaluatePosition(game);
        sortEvaluationsForPlayer(evaluations, game.getPlayerOnTheMove());
        return evaluations.get(0).getMove();
    }

    public static List<MoveEvaluation> evaluatePosition(Game game) {
        if(game.getPlayerOnTheMove().equals(Player.BLACK)) {
            return evaluate(game, MoveAnalyzer.getAllowedMoves(game),
                    game.getPlayerOnTheMove().getStrategy().getPredictionSteps(),
                    game.getPlayerOnTheMove().getStrategy());
        }
        else {
            return evaluate(game, MoveAnalyzer.getAllowedMoves(game),
                    game.getPlayerOnTheMove().getStrategy().getPredictionSteps(),
                    game.getPlayerOnTheMove().getStrategy());
        }
    }

    public static List<MoveEvaluation> evaluate(Game game, List<Move> moves, int step, PlayerStrategy strategy) {
        if(step > 1) {
            List<MoveEvaluation> evaluations = new ArrayList<>();
            for (Move move : moves) {
                final Game copy = MoveAnalyzer.makeMove(new Game(game), move);
                List<Move> responses = MoveAnalyzer.getAllowedMoves(copy);
                if (responses != null && !responses.isEmpty()) {
                    MoveEvaluation bestResponse = getBestMoveEvaluationForPlayer(
                            evaluate(copy, limitResponses(copy, responses, strategy),
                                    step - 1, strategy), copy.getPlayerOnTheMove());
                    evaluations.add(new MoveEvaluation(move, bestResponse.getEvaluation()));
                } else {
                    evaluations.add(new MoveEvaluation(move, game.getPlayerOnTheMove().getDirection() * MAX_EVAL));
                }
            }
            return evaluations;
        }
        else if(step == 1){
            List<MoveEvaluation> evaluations = new ArrayList<>();
            for(Move move : moves) {
                final Game copy = MoveAnalyzer.makeMove(new Game(game), move);
                evaluations.add(new MoveEvaluation(move, Evaluator.asses(copy)));
            }
            return evaluations;
        }
        else
            return null;
    }

    private static List<Move> limitResponses(Game game, List<Move> responses, PlayerStrategy strategy) {
        if(strategy.getCutPercent() == 0)
            return responses;

        List<Move> limitedResponses = new ArrayList<>();

        List<MoveEvaluation> evaluations = new ArrayList<>();
        for(Move move : responses) {
            final Game copy = MoveAnalyzer.makeMove(new Game(game), move);
            evaluations.add(new MoveEvaluation(move, Evaluator.asses(copy)));
        }
        sortEvaluationsForPlayer(evaluations, game.getPlayerOnTheMove());

        for(int i=0; i<Math.floor(strategy.getCutPercent()/100*evaluations.size())+1; i++)
            limitedResponses.add(evaluations.get(i).getMove());

        return limitedResponses;
    }

    public static MoveEvaluation getBestMoveEvaluationForPlayer(List<MoveEvaluation> evaluations, Player player) {
        sortEvaluationsForPlayer(evaluations, player);
        return evaluations.get(0);
    }

    private static void sortEvaluationsForPlayer(List<MoveEvaluation> evaluations, Player player) {
        evaluations.sort((o1, o2) -> o1.getEvaluation() >= o2.getEvaluation() ? player.getDirection()*-1 :
                player.getDirection());
    }

    public static float asses(Game position) {
        int whitePieces = 0;
        int blackPieces = 0;
        int whiteAdancements = 0;
        int blackAdancements = 0;

        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                if(position.getBoard()[i][j].isOccupiedBy(Player.WHITE)) {
                    whitePieces++;
                    if(position.getBoard()[i][j].getPiece().isPromoted())
                        whitePieces++;
                    else
                        whiteAdancements += i;
                }
                else if(position.getBoard()[i][j].isOccupiedBy(Player.BLACK))
                {
                    blackPieces++;
                    if(position.getBoard()[i][j].getPiece().isPromoted())
                        blackPieces++;
                    else
                        blackAdancements += 7-i;
                }
            }
        }
        return (whitePieces-blackPieces) + 0.01f *(whiteAdancements-blackAdancements);
    }
}
