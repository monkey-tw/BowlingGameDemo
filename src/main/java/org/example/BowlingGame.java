package org.example;

public class BowlingGame {
    private static final int MAXIMUM_PINS = 10;
    private static final int FRAME_COUNT = 10;

    private int[] rolls = new int[21];
    private int currentRollIndex = 0;

    public void roll(int pins) {
        if (pins < 0 || pins > MAXIMUM_PINS) {
            throw new IllegalArgumentException("Invalid number of pins knocked down: " + pins);
        }
        rolls[currentRollIndex++] = pins;
    }

    public int score() {
        int totalScore = 0;
        int currentFrameIndex = 0;
        for (int frame = 0; frame < FRAME_COUNT; frame++) {
            totalScore += calculateFrameScore(currentFrameIndex);
            currentFrameIndex += getFrameRollCount(currentFrameIndex);
        }
        return totalScore;
    }

    private int calculateFrameScore(int frameIndex) {
        if (isStrike(frameIndex)) {
            return MAXIMUM_PINS + calculateBonusScore(frameIndex);
        } else if (isSpare(frameIndex)) {
            return MAXIMUM_PINS + calculateBonusScore(frameIndex);
        } else {
            return sumOfBallsInFrame(frameIndex);
        }
    }

    private int calculateBonusScore(int frameIndex) {
        if (isStrike(frameIndex)) {
            return rolls[frameIndex + 1] + rolls[frameIndex + 2];
        } else {
            return rolls[frameIndex + 2];
        }
    }

    private boolean isStrike(int frameIndex) {
        return rolls[frameIndex] == MAXIMUM_PINS;
    }

    private boolean isSpare(int frameIndex) {
        return rolls[frameIndex] + rolls[frameIndex + 1] == MAXIMUM_PINS;
    }

    private int sumOfBallsInFrame(int frameIndex) {
        return rolls[frameIndex] + rolls[frameIndex + 1];
    }

    private int getFrameRollCount(int frameIndex) {
        if (isStrike(frameIndex)) {
            return 1;
        } else {
            return 2;
        }
    }
}