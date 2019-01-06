package com.model.checkers.evaluation;

import com.model.checkers.Move;

public class MoveEvaluation {
    private Move move;
    private float evaluation;

    public MoveEvaluation(Move aMove, float aEvaluation)
    {
        this.move = aMove;
        this.evaluation = aEvaluation;
    }

    @Override
    public String toString() {
        return " move: " + move.toString() + " evaluation: " + evaluation;
    }


    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public float getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(float evaluation) {
        this.evaluation = evaluation;
    }
}
