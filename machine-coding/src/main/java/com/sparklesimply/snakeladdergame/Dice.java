package com.sparklesimply.snakeladdergame;

import java.util.Random;

public class Dice {
    private static final int MIN = 1;
    private static final int MAX = 6;
    private final Random random;

    public Dice() {
        random = new Random();
    }
    public int roll() {
        return random.nextInt(MAX) + MIN;
    }
}
