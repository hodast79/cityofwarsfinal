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
        boolean isAdmin = false;

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
                case 3:
                    userController.forgotPassword();
                    break;
                case 4:
                    isAdmin = adminLogin();
                    loggedIn = isAdmin;
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
                    break;
            }
        }

        // Display main game menu after successful login
        while (running) {
            if (isAdmin) {
                adminMenu(adminController);
            } else {
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
                    default:
                        System.out.println("Invalid choice!");
                        break;
                }
            }
        }
    }

    private static void displayLoginSignupMenu() {
        System.out.println("1. Log in");
        System.out.println("2. Sign up");
        System.out.println("3. Forgot my password");
        System.out.println("4. Admin log in");
    }

    private static boolean adminLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter admin username:");
        String username = scanner.nextLine();
        System.out.println("Enter admin password:");
        String password = scanner.nextLine();

        // Replace these with your actual admin credentials check
        if ("admin".equals(username) && "pass".equals(password)) {
            System.out.println("Admin logged in successfully!");
            return true;
        } else {
            System.out.println("Invalid admin credentials!");
            return false;
        }
    }

    private static void adminMenu(AdminController adminController) {
        Scanner scanner = new Scanner(System.in);
        boolean adminRunning = true;

        while (adminRunning) {
            System.out.println("1. Add Card");
            System.out.println("2. Edit Card");
            System.out.println("3. Delete Card");
            System.out.println("4. View All Players");
            System.out.println("5. Log out");

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
                case 5:
                    adminRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }
}
