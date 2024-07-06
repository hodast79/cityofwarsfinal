package model;

public class Game {
    private Player player1;
    private Player player2;
    private GameMode gameMode;

    public Game(Player player1, Player player2, GameMode gameMode) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameMode = gameMode;
    }

    public void startGame() {
        // Implementation of game logic based on game mode
    }
}
