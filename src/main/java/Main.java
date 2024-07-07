import controller.AdminController;
import controller.GameController;
import controller.UserController;
import model.Admin;
import model.MainMenu;
import model.Player;
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
        boolean isAdmin = false;
        while (running) {
            boolean loggedIn = false;

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
            if (isAdmin) {
                adminMenu(adminController);
            } else {
                Player player1 = new Player(userController.getLoggedInUser());
                mainMenu.displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline
                switch (choice) {
                    case 1:
                        Player player2 = loginSecondPlayer(userController);
                        GameController gameController = new GameController(player1, player2);
                        gameController.startGame();
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
                        displayProfileMenu(userController);
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
    }
    private static void displayProfileMenu(UserController userController) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Profile Menu:");
            System.out.println("1. Show information");
            System.out.println("2. Change username");
            System.out.println("3. Change nickname");
            System.out.println("4. Change password");
            System.out.println("5. Change email");
            System.out.println("6. Back to main menu");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    userController.viewUserProfile();
                    break;
                case 2:
                    userController.changeUsername();
                    break;
                case 3:
                    userController.changeNickname();
                    break;
                case 4:
                    userController.changePassword();
                    break;
                case 5:
                    userController.changeEmail();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
                    break;
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

    private static Player loginSecondPlayer(UserController userController) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("man injam");
        boolean loggedIn = false;
        userController.setLoggedInUser(null);
        while (!loggedIn) {
            System.out.println("Second player, please log in:");
            loggedIn = userController.loginUser();
        }
        return new Player(userController.getLoggedInUser());
        ///
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
