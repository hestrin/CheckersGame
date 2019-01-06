package com.model.checkers;

public enum PlayerStrategy {

    RANDOM(0), TWO_STEP_PREDICTION(2), THREE_STEP_PREDICTION(3), FOUR_STEP_PREDICTION(4);

    private int predictionSteps;

    PlayerStrategy(int aPredictionSteps) {
        predictionSteps = aPredictionSteps;
    }
}
