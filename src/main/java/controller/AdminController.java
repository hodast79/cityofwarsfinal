package controller;

import model.*;

import java.util.List;
import java.util.Scanner;

import utils.FileManager;
import model.Admin;
import model.Card;
import model.Player;

public class AdminController {
    private Admin admin;

    public AdminController(Admin admin) {
        this.admin = admin;
    }
    public boolean loginAdmin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter admin username:");
        String username = scanner.nextLine();
        System.out.println("Enter admin password:");
        String password = scanner.nextLine();

        // Hardcoded admin credentials for simplicity
        if (username.equals("admin") && password.equals("adminpass")) {
            System.out.println("Admin logged in successfully!");
            return true;
        } else {
            System.out.println("Invalid admin credentials.");
            return false;
        }
    }

    public void addCard() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter card name:");
        String name = scanner.nextLine();
        System.out.println("Enter card description:");
        String description = scanner.nextLine();
        System.out.println("Enter card character(type):");
        String character = scanner.nextLine();
        boolean validCharacter = false;

        while(!validCharacter) {
            validCharacter = character.equals("Warrior") || character.equals("Mage") || character.equals("Archer") || character.equals("Assassin");
            if (!validCharacter) {
                System.out.println("Enter a valid card character(type):");
                character = scanner.nextLine();
            }
        }

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
        Card newCard = new Card (name,defenseAttack,character,duration, playerDamage, upgradeCost,maxLevel ,description);
        if (newCard != null) {
            admin.addCard(newCard);
            System.out.println("Card added successfully.");
        } else {
            System.out.println("Invalid card type or ability type.");
        }
    }



    public void editCard() {
        Scanner scanner = new Scanner(System.in);

        List<Card> cards = admin.viewAllCards();


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
        if (!newDescription.trim().isEmpty()) {
            cardToEdit.setDescription(newDescription);
        }

        System.out.println("Current defense/attack value: " + cardToEdit.getDefenseAttack());
        System.out.println("Enter new defense/attack value (10-100):");
        String newDefenseAttack = scanner.nextLine();
        if (!newDefenseAttack.trim().isEmpty()) {
            cardToEdit.setDefenseAttack(Integer.parseInt(newDefenseAttack));
        }

        System.out.println("Current duration: " + cardToEdit.getDuration());
        System.out.println("Enter new duration (1-5):");
        String newDuration = scanner.nextLine();
        if (!newDuration.trim().isEmpty()) {
            cardToEdit.setDuration(Integer.parseInt(newDuration));
        }


        System.out.println("Current player damage: " + cardToEdit.getPlayerDamage());
        System.out.println("Enter new player damage (10-50):");
        String newPlayerDamage = scanner.nextLine();
        if (!newPlayerDamage.trim().isEmpty()) {
            cardToEdit.setPlayerDamage(Integer.parseInt(newPlayerDamage));
        }

        System.out.println("Current max level: " + cardToEdit.getMaxLevel());
        System.out.println("Enter new max level (5-10):");
        String newLevel = scanner.nextLine();
        String newMaxLevel = scanner.nextLine();
        if (!newMaxLevel.trim().isEmpty()) {
            cardToEdit.setMaxLevel(Integer.parseInt(newMaxLevel));
        }

        System.out.println("Current upgrade cost: " + cardToEdit.getUpgradeCost());
        System.out.println("Enter new upgrade cost:");
        String newUpgradeCost = scanner.nextLine();
        if (!newUpgradeCost.trim().isEmpty()) {
            cardToEdit.setUpgradeCost(Integer.parseInt(newUpgradeCost));
        }



        FileManager.saveCards(cards);
        System.out.println("Card successfully edited.");
    }


    public void deleteCard() {
        Scanner scanner = new Scanner(System.in);
        List<Card> cards = admin.viewAllCards();
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




}