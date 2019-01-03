package com.model.checkers.evaluation;

import com.model.checkers.Move;

public class MoveEvaluation {
    private Move move;
    private float evaluation; // should be between -1 and 1

    public MoveEvaluation(Move aMove, float aEvaluation)
    {
        this.move = aMove;
        this.evaluation = aEvaluation;
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
