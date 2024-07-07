package model;

import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Starting the game...");

        // Implement the game logic as per the instructions
        while (true) {
            // Player 1's turn
            playerTurn(player1, scanner);

            // Check if Player 2 is defeated
            if (player2.getHp() <= 0) {
                System.out.println(player1.getUsername() + " wins!");
                break;
            }

            // Player 2's turn
            playerTurn(player2, scanner);

            // Check if Player 1 is defeated
            if (player1.getHp() <= 0) {
                System.out.println(player2.getUsername() + " wins!");
                break;
            }
        }
    }

    private void playerTurn(Player player, Scanner scanner) {
        System.out.println(player.getUsername() + "'s turn. Select a card:");
        player.displayInfo();
        int cardIndex = scanner.nextInt();
        scanner.nextLine(); // consume newline
        Card card = player.getCardDeck().get(cardIndex);
        card.displayInfo();

        System.out.println("Choose action: 1) Place Card 2) Attack");
        int action = scanner.nextInt();
        scanner.nextLine(); // consume newline

        // Implement the action logic
        if (action == 1) {
            // Place card logic
            placeCard(player, card);
        } else if (action == 2) {
            // Attack logic
            attack(player, card);
        }
    }

    private void placeCard(Player player, Card card) {
        // Logic to place card on the board
        System.out.println(player.getUsername() + " placed card " + card.getName());
    }

    private void attack(Player player, Card card) {
        // Logic to attack opponent
        System.out.println(player.getUsername() + " attacks with card " + card.getName());
    }
}
