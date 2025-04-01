package com.sparklesimply.snakeladdergame;

import java.util.List;

public class GameService {
    GameBoard board;
    List<Player> players;
    Dice dice;

    public GameService(GameBoard board, List<Player> players) {
        this.board = board;
        this.players = players;
        this.dice = new Dice();
    }

    public void startGame() {
        boolean isGameEnded = false;
        int currentPlayerIndex = 0;

        while(!isGameEnded) {
            Player currentPlayer = this.players.get(currentPlayerIndex);
            int currentPosition = currentPlayer.getPosition();
            int diceValue = this.dice.roll();
            int newPosition = currentPosition + diceValue;

            if(this.board.isValidMove(newPosition)) {
                int finalPosition = this.board.getNextPosition(newPosition);
                currentPlayer.setPosition(finalPosition);

                System.out.println(currentPlayer.getName() + " rolled a " + diceValue + " and moved from "+ currentPosition + " to "+ newPosition);

                if(this.board.isWinningGame(finalPosition)) {
                    isGameEnded = true;
                    System.out.println(currentPlayer.getName() + " wins the game!");
                }
            } else {
                System.out.println(currentPlayer.getName() + " rolled a " + diceValue + " and moved from "+ currentPosition + " to "+ currentPosition);
            }

            currentPlayerIndex = (currentPlayerIndex + 1) % this.players.size();
        }
    }
}
