package com.sparklesimply.snakeladdergame;

import java.util.HashMap;
import java.util.Map;

public class GameBoard {
    private static final int BOARD_SIZE = 100;
    private final Map<Integer, Integer> snakes;
    private final Map<Integer, Integer> ladders;

    public GameBoard() {
        snakes = new HashMap<>();
        ladders = new HashMap<>();
    }

    public void addSnakes(int head, int tail) {
        snakes.put(head, tail);
    }

    public void addLadders(int start, int end) {
        ladders.put(start, end);
    }

    public int getNextPosition(int currentPosition) {
        int nextPosition = currentPosition;

        // check if snake is present on next position
        if(snakes.containsKey(nextPosition)) {
            nextPosition = nextPosition - snakes.get(nextPosition);
        }

        // check if ladder is present on next position
        if(ladders.containsKey(nextPosition)) {
            nextPosition = nextPosition + ladders.get(nextPosition);
        }

        // check if there is another snake or ladder present on next position
        if((snakes.containsKey(nextPosition) || ladders.containsKey(nextPosition)) && currentPosition != nextPosition) {
            return getNextPosition(nextPosition);
        }

        return nextPosition;
    }

    public boolean isWinningGame(int position) {
        return position == BOARD_SIZE;
    }

    public boolean isValidMove(int position) {
        return position <= BOARD_SIZE;
    }
}