package controller;

import model.*;

import java.util.Scanner;

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
        System.out.println("Enter the index of the card to edit:");
        int index = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (index < 0 || index >= admin.getCards().size()) {
            System.out.println("Invalid card index.");
            return;
        }

        Card cardToEdit = admin.getCards().get(index);

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

        System.out.println("Card successfully edited.");
    }

    private SpecialCard createSpecialCard(String name, String description, int defenseAttack, int duration, int playerDamage, int maxLevel, int upgradeCost, String abilityType) {
        switch (abilityType) {
            case "Green":
                return new GreenCard(name, description, defenseAttack, duration, playerDamage, maxLevel, upgradeCost);
            case "HPBoost":
                return new HPBoostCard(name, description, defenseAttack, duration, playerDamage, maxLevel, upgradeCost);
            case "PowerUp":
                return new PowerUpCard(name, description, defenseAttack, duration, playerDamage, maxLevel, upgradeCost);
            case "ChangeLocation":
                return new ChangeLocationCard(name, description, defenseAttack, duration, playerDamage, maxLevel, upgradeCost);
            case "Repair":
                return new RepairCard(name, description, defenseAttack, duration, playerDamage, maxLevel, upgradeCost);
            case "Weakening":
                return new WeakeningCard(name, description, defenseAttack, duration, playerDamage, maxLevel, upgradeCost);
            case "RemoveCardFromHand":
                return new RemoveCardFromHand(name, description, defenseAttack, duration, playerDamage, maxLevel, upgradeCost);
            case "ReduceOpponentCard":
                return new ReduceOpponentCard(name, description, defenseAttack, duration, playerDamage, maxLevel, upgradeCost);
            case "Copy":
                return new CopyCard(name, description, defenseAttack, duration, playerDamage, maxLevel, upgradeCost);
            case "Hidden":
                return new HiddenCard(name, description, defenseAttack, duration, playerDamage, maxLevel, upgradeCost);
            default:
                return null;
        }
    }
}
