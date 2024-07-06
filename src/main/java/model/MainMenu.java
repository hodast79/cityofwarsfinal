package model;

public class MainMenu {

    public void displayMenu() {
        System.out.println("1. Start Game");
        System.out.println("2. View Cards");
        System.out.println("3. View Battle History");
        System.out.println("4. Store");
        System.out.println("5. Profile");
        System.out.println("6. Logout");
    }

    public void handleMenuSelection(int choice) {
        switch (choice) {
            case 1:
                // Start Game
                break;
            case 2:
                // View Cards
                break;
            case 3:
                // View Battle History
                break;
            case 4:
                // Store
                break;
            case 5:
                // Profile
                break;
            case 6:
                // Logout
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }
}
