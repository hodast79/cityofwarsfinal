package controller;

import model.Game;
import model.GameMode;
import model.Player;

public class GameController {
    private Game game;

    public GameController(Player player1, Player player2, GameMode gameMode) {
        this.game = new Game(player1, player2, gameMode);
    }

    public void startGame() {
        game.startGame();
    }
}
