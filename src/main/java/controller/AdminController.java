package controller;

import model.*;

import java.util.Scanner;


import model.Admin;
import model.Card;
import model.Player;

public class AdminController {
    private Admin admin;

    public AdminController(Admin admin) {
        this.admin = admin;
    }

    public void addCard() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter card name:");
        String name = scanner.nextLine();
        System.out.println("Enter card description:");
        String description = scanner.nextLine();
        System.out.println("Enter defense/attack value (10-100):");
        int defenseAttack = scanner.nextInt();
        System.out.println("Enter duration (1-5):");
        int duration = scanner.nextInt();
        System.out.println("Enter player damage (10-50):");
        int playerDamage = scanner.nextInt();
        System.out.println("Enter max level (5-10):");
        int maxLevel = scanner.nextInt();
        System.out.println("Enter upgrade cost:");
        int upgradeCost = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.println("Enter card type (1 for normal, 2 for special):");
        int type = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Card newCard = null;
        if (type == 1) {
            newCard = new NormalCard(name, description, defenseAttack, duration, playerDamage, maxLevel, upgradeCost);
        } else if (type == 2) {
            System.out.println("Enter special card ability type (e.g., Green, HPBoost, etc.):");
            String abilityType = scanner.nextLine();
            newCard = createSpecialCard(name, description, defenseAttack, duration, playerDamage, maxLevel, upgradeCost, abilityType);
        }

        if (newCard != null) {
            admin.addCard(newCard);
            System.out.println("Card added successfully.");
        } else {
            System.out.println("Invalid card type or ability type.");
        }
    }

    public void editCard() {
        Scanner scanner = new Scanner(System.in);
        admin.viewAllCards();
        System.out.println("Enter the name of the card to edit:");
        String cardName = scanner.nextLine();

        Card cardToEdit = admin.getCards().stream()
                .filter(card -> card.getName().equals(cardName))
                .findFirst()
                .orElse(null);

        if (cardToEdit == null) {
            System.out.println("Card not found.");
            return;
        }

        System.out.println("Current card name: " + cardToEdit.getName());
        System.out.println("Enter new card name:");
        String newName = scanner.nextLine();
        if (newName.isEmpty()) {
            System.out.println("Error: Card name cannot be empty.");
            return;
        }

        System.out.println("Current card description: " + cardToEdit.getDescription());
        System.out.println("Enter new card description:");
        String newDescription = scanner.nextLine();

        System.out.println("Current defense/attack value: " + cardToEdit.getDefenseAttack());
        System.out.println("Enter new defense/attack value (10-100):");
        int newDefenseAttack = scanner.nextInt();

        System.out.println("Current duration: " + cardToEdit.getDuration());
        System.out.println("Enter new duration (1-5):");
        int newDuration = scanner.nextInt();

        System.out.println("Current player damage: " + cardToEdit.getPlayerDamage());
        System.out.println("Enter new player damage (10-50):");
        int newPlayerDamage = scanner.nextInt();

        System.out.println("Current max level: " + cardToEdit.getMaxLevel());
        System.out.println("Enter new max level (5-10):");
        int newMaxLevel = scanner.nextInt();

        System.out.println("Current upgrade cost: " + cardToEdit.getUpgradeCost());
        System.out.println("Enter new upgrade cost:");
        int newUpgradeCost = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        cardToEdit.setName(newName);
        cardToEdit.setDescription(newDescription);
        cardToEdit.setDefenseAttack(newDefenseAttack);
        cardToEdit.setDuration(newDuration);
        cardToEdit.setPlayerDamage(newPlayerDamage);
        cardToEdit.setMaxLevel(newMaxLevel);
        cardToEdit.setUpgradeCost(newUpgradeCost);

        admin.editCard(cardToEdit);
        System.out.println("Card successfully edited.");
    }

    public void deleteCard() {
        Scanner scanner = new Scanner(System.in);
        admin.viewAllCards();
        System.out.println("Enter the name of the card to delete:");
        String cardName = scanner.nextLine();

        admin.deleteCard(cardName);
        System.out.println("Card deleted successfully.");
    }

    public void viewAllPlayers() {
        System.out.println("List of all players:");
        for (Player player : admin.getPlayers()) {
            System.out.println("Username: " + player.getUsername() + ", Level: " + player.getLevel() + ", Coins: " + player.getCoins());
        }
    }

    // Helper method to create special cards
    private Card createSpecialCard(String name, String description, int defenseAttack, int duration, int playerDamage, int maxLevel, int upgradeCost, String abilityType) {
        switch (abilityType.toLowerCase()) {
            case "green":
                return new GreenCard(name, description, defenseAttack, duration, playerDamage, maxLevel, upgradeCost);
            case "hpboost":
                return new HPBoostCard(name, description, defenseAttack, duration, playerDamage, maxLevel, upgradeCost);
            // Add other special card types here
            default:
                System.out.println("Invalid ability type for special card.");
                return null;
        }
    }
}
