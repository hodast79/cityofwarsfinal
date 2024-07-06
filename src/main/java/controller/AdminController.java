package controller;

import model.Admin;
import model.Card;

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

        System.out.println("Enter card attack value:");
        int attack = scanner.nextInt();

        System.out.println("Enter card defense value:");
        int defense = scanner.nextInt();

        System.out.println("Enter card duration:");
        int duration = scanner.nextInt();

        System.out.println("Enter player damage:");
        int playerDamage = scanner.nextInt();

        System.out.println("Enter upgrade level:");
        int upgradeLevel = scanner.nextInt();

        System.out.println("Enter upgrade cost:");
        int upgradeCost = scanner.nextInt();

        Card card = new Card(name, attack, defense, duration, playerDamage, upgradeLevel, upgradeCost);
        admin.addCard(card);
    }

    public void editCard() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the name of the card to edit:");
        String cardName = scanner.nextLine();

        System.out.println("Enter new card name:");
        String newName = scanner.nextLine();

        System.out.println("Enter new card attack value:");
        int attack = scanner.nextInt();

        System.out.println("Enter new card defense value:");
        int defense = scanner.nextInt();

        System.out.println("Enter new card duration:");
        int duration = scanner.nextInt();

        System.out.println("Enter new player damage:");
        int playerDamage = scanner.nextInt();

        System.out.println("Enter new upgrade level:");
        int upgradeLevel = scanner.nextInt();

        System.out.println("Enter new upgrade cost:");
        int upgradeCost = scanner.nextInt();

        Card updatedCard = new Card(newName, attack, defense, duration, playerDamage, upgradeLevel, upgradeCost);
        admin.editCard(cardName, updatedCard);
    }

    public void deleteCard() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the name of the card to delete:");
        String cardName = scanner.nextLine();

        admin.deleteCard(cardName);
    }

    public void viewAllPlayers() {
        admin.viewAllPlayers().forEach(player -> {
            System.out.println("Player: " + player.getUsername() + ", Level: " + player.getLevel());
        });
    }
}
