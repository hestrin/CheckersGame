package com.model.checkers.evaluation;

import com.model.checkers.Game;
import com.model.checkers.Move;
import com.model.checkers.Player;
import com.model.checkers.pieces.MoveAnalyzer;

import java.util.ArrayList;
import java.util.List;

public class Evaluator {

    private static final float MAX_EVAL = 25f;

    public static Move getBestMove(Game game) {
        List<MoveEvaluation> evaluations = game.getPlayerOnTheMove().equals(Player.BLACK) ?
                sixStepEvaluation(game, MoveAnalyzer.getAllowedMoves(game)) :
                fourStepEvaluation(game, MoveAnalyzer.getAllowedMoves(game));
        sortEvaluationsForPlayer(evaluations, game.getPlayerOnTheMove());
        return evaluations.get(0).getMove();
    }

    public static List<MoveEvaluation> sixStepEvaluation(Game position, List<Move> moves) {
        List<MoveEvaluation> evaluations = new ArrayList<>();
        for(Move move : moves) {
//            System.out.println( " STEP 6 :" + move.toString());
            final Game copy = MoveAnalyzer.makeMove(new Game(position), move);
            List<Move> responses = MoveAnalyzer.getAllowedMoves(copy);
            if(responses != null && !responses.isEmpty()) {
                MoveEvaluation bestResponse = getBestMoveEvaluationForPlayer(
                        fiveStepEvaluation(copy, responses), copy.getPlayerOnTheMove());
                evaluations.add(new MoveEvaluation(move, bestResponse.getEvaluation()));
            }
            else
            {
                evaluations.add(new MoveEvaluation(move, position.getPlayerOnTheMove().getDirection()*MAX_EVAL));
            }
        }
        return evaluations;
    }

    public static List<MoveEvaluation> fiveStepEvaluation(Game position, List<Move> moves) {
        List<MoveEvaluation> evaluations = new ArrayList<>();
        for(Move move : moves) {
//            System.out.println( " STEP 5 :" + move.toString());
            final Game copy = MoveAnalyzer.makeMove(new Game(position), move);
            List<Move> responses = MoveAnalyzer.getAllowedMoves(copy);
            if(responses != null && !responses.isEmpty()) {
                MoveEvaluation bestResponse = getBestMoveEvaluationForPlayer(
                        fourStepEvaluation(copy, responses), copy.getPlayerOnTheMove());
                evaluations.add(new MoveEvaluation(move, bestResponse.getEvaluation()));
            }
            else
            {
                evaluations.add(new MoveEvaluation(move, position.getPlayerOnTheMove().getDirection()*MAX_EVAL));
            }
        }
        return evaluations;
    }

    public static List<MoveEvaluation> fourStepEvaluation(Game position, List<Move> moves) {
        List<MoveEvaluation> evaluations = new ArrayList<>();
        for(Move move : moves) {
//            System.out.println( " STEP 4 :" + move.toString());
            final Game copy = MoveAnalyzer.makeMove(new Game(position), move);
            List<Move> responses = MoveAnalyzer.getAllowedMoves(copy);
            if(responses != null && !responses.isEmpty()) {
                MoveEvaluation bestResponse = getBestMoveEvaluationForPlayer(
                        threeStepEvaluation(copy, responses), copy.getPlayerOnTheMove());
                evaluations.add(new MoveEvaluation(move, bestResponse.getEvaluation()));
            }
            else
            {
                evaluations.add(new MoveEvaluation(move, position.getPlayerOnTheMove().getDirection()*MAX_EVAL));
            }
        }
        return evaluations;
    }

    public static List<MoveEvaluation> threeStepEvaluation(Game position, List<Move> moves) {
        List<MoveEvaluation> evaluations = new ArrayList<>();
        for(Move move : moves) {
//            System.out.println( " STEP 3 :" + move.toString());
            final Game copy = MoveAnalyzer.makeMove(new Game(position), move);
            List<Move> responses = MoveAnalyzer.getAllowedMoves(copy);
            if(responses != null && !responses.isEmpty()) {
                MoveEvaluation bestResponse = getBestMoveEvaluationForPlayer(
                        twoStepEvaluation(copy, responses), copy.getPlayerOnTheMove());
                evaluations.add(new MoveEvaluation(move, bestResponse.getEvaluation()));
            }
            else
            {
                evaluations.add(new MoveEvaluation(move, position.getPlayerOnTheMove().getDirection()*MAX_EVAL));
            }
        }
        return evaluations;
    }

    public static List<MoveEvaluation> twoStepEvaluation(Game position, List<Move> moves) {
        List<MoveEvaluation> evaluations = new ArrayList<>();
        for(Move move : moves) {
//            System.out.println( " STEP 2 :" + move.toString());
            final Game copy = MoveAnalyzer.makeMove(new Game(position), move);
            List<Move> responses = MoveAnalyzer.getAllowedMoves(copy);
            if(responses != null && !responses.isEmpty()) {
                MoveEvaluation bestResponse = getBestMoveEvaluationForPlayer(
                        oneStepEvaluation(copy, responses), copy.getPlayerOnTheMove());
                evaluations.add(new MoveEvaluation(move, bestResponse.getEvaluation()));
            }
            else
            {
                evaluations.add(new MoveEvaluation(move, position.getPlayerOnTheMove().getDirection()*MAX_EVAL));
            }
        }
        return evaluations;
    }

    public static List<MoveEvaluation> oneStepEvaluation(Game game, List<Move> moves) {
        List<MoveEvaluation> evaluations = new ArrayList<>();
        for(Move move : moves) {
            final Game copy = MoveAnalyzer.makeMove(new Game(game), move);
            evaluations.add(new MoveEvaluation(move, Evaluator.asses(copy)));
        }
        return evaluations;
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
