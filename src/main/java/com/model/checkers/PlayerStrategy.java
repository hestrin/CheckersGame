package com.model.checkers;

public enum PlayerStrategy {

    RANDOM(0),
    TWO_STEP_PREDICTION(2),
    THREE_STEP_PREDICTION(3),
    FOUR_STEP_PREDICTION(4),
    EIGHT_STEP_PREDICTION_WITH_CUTS(8, 40),
    TWO_STEP_PREDICTION_WITH_CUTS(2, 40);

    private int predictionSteps;

    private int cutPercent;     // 0-100

    PlayerStrategy(int aPredictionSteps, int aCutPercent) {
        predictionSteps = aPredictionSteps;
        cutPercent = aCutPercent;
    }

    PlayerStrategy(int aPredictionSteps) {
        predictionSteps = aPredictionSteps;
        cutPercent = 0;
    }

    public int getPredictionSteps() {
        return predictionSteps;
    }

    public int getCutPercent() {
        return cutPercent;
    }

    public void setCutPercent(int cutPercent) {
        this.cutPercent = cutPercent;
    }

    public void setPredictionSteps(int predictionSteps) {
        this.predictionSteps = predictionSteps;
    }
}
