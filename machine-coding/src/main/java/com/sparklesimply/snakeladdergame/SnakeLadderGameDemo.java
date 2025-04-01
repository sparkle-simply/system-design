package com.sparklesimply.snakeladdergame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SnakeLadderGameDemo {

    public static void execute() {
        GameBoard board = new GameBoard();
        List<Player> players = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter number of snakes");
            int numSnakes = scanner.nextInt();

            System.out.println("Enter position for snakes");
            for(int i=0; i<numSnakes; i++) {
                int head = scanner.nextInt();
                int tail = scanner.nextInt();
                board.addSnakes(head, tail);
            }

            System.out.println("Enter number of ladders");
            int numLadders = scanner.nextInt();

            System.out.println("Enter position for ladders");
            for(int i=0; i<numLadders; i++) {
                int start = scanner.nextInt();
                int end = scanner.nextInt();
                board.addLadders(start, end);
            }

            System.out.println("Enter number of players");
            int numPlayers = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter names for players");
            for(int i=0; i<numPlayers; i++) {
                String name = scanner.nextLine();
                players.add(new Player(name));
            }

            GameService gameService = new GameService(board, players);
            gameService.startGame();

        } catch (Exception e) {
            System.out.println("Error reading inputs");
        } finally {

        }





    }
}
