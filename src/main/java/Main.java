import controller.AdminController;
import controller.UserController;
import model.Admin;
import model.MainMenu;
import model.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserController userController = new UserController();
        Admin admin = new Admin();
        AdminController adminController = new AdminController(admin);
        MainMenu mainMenu = new MainMenu();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        boolean loggedIn = false;

        // Display login/signup menu
        while (!loggedIn) {
            displayLoginSignupMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            switch (choice) {
                case 1:
                    loggedIn = userController.loginUser();
                    break;
                case 2:
                    userController.registerUser();
                    break;
                default:
                    System.out.println("Invalid choice! Please select either 1 or 2.");
                    break;
            }
        }

        // Display main game menu after successful login
        while (running) {
            mainMenu.displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            switch (choice) {
                case 1:
                    // Start Game logic
                    break;
                case 2:
                    // View Cards logic
                    break;
                case 3:
                    // View Battle History logic
                    break;
                case 4:
                    // Store logic
                    break;
                case 5:
                    // Profile logic
                    break;
                case 6:
                    running = false;
                    break;
                case 7:
                    // Admin functions
                    System.out.println("1. Add Card");
                    System.out.println("2. Edit Card");
                    System.out.println("3. Delete Card");
                    System.out.println("4. View All Players");
                    int adminChoice = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    switch (adminChoice) {
                        case 1:
                            adminController.addCard();
                            break;
                        case 2:
                            adminController.editCard();
                            break;
                        case 3:
                            adminController.deleteCard();
                            break;
                        case 4:
                            adminController.viewAllPlayers();
                            break;
                        default:
                            System.out.println("Invalid choice!");
                            break;
                    }
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    private static void displayLoginSignupMenu() {
        System.out.println("1. Log in");
        System.out.println("2. Sign up");
    }
}
