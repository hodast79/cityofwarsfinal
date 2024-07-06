package controller;

import model.Card;
import model.User;
import model.SecurityQuestion;
import utils.CaptchaGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

public class UserController {

    private User loggedInUser;

    public void registerUser() {
        if (loggedInUser != null) {
            System.out.println("A user is already logged in. Please log out before registering a new user.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username:");
        String username = scanner.nextLine();
        if (username.isEmpty()) {
            System.out.println("Error: Username cannot be empty.");
            return;
        }
        if (!isValidUsername(username)) {
            System.out.println("Error: Invalid username. Username should contain only letters, numbers, and underscores.");
            return;
        }
        if (User.usernameExists(username)) {
            System.out.println("Error: Username already exists.");
            return;
        }

        System.out.println("Enter password (or type 'random' for a random password):");
        String password = scanner.nextLine();
        if (password.isEmpty()) {
            System.out.println("Error: Password cannot be empty.");
            return;
        }
        String confirmPassword = null;
        if ("random".equalsIgnoreCase(password)) {
            password = generateRandomPassword();
            System.out.println("Your random password: " + password);
        } else {
            System.out.println("Confirm password:");
            confirmPassword = scanner.nextLine();
            if (!password.equals(confirmPassword)) {
                System.out.println("Error: Passwords do not match.");
                return;
            }
            if (!isValidPassword(password)) {
                System.out.println("Error: Weak password. Password must be at least 8 characters long, contain at least one lowercase letter, one uppercase letter, and one special character.");
                return;
            }
        }

        System.out.println("Enter nickname:");
        String nickname = scanner.nextLine();
        if (nickname.isEmpty()) {
            System.out.println("Error: Nickname cannot be empty.");
            return;
        }

        System.out.println("Enter email:");
        String email = scanner.nextLine();
        if (email.isEmpty()) {
            System.out.println("Error: Email cannot be empty.");
            return;
        }
        if (!isValidEmail(email)) {
            System.out.println("Error: Invalid email format.");
            return;
        }

        System.out.println("Choose a security question:");
        for (SecurityQuestion question : SecurityQuestion.values()) {
            System.out.println(question.ordinal() + 1 + ". " + question.getQuestion());
        }
        int questionChoice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        SecurityQuestion securityQuestion = SecurityQuestion.values()[questionChoice - 1];

        System.out.println("Answer the security question:");
        String securityAnswer = scanner.nextLine();
        if (securityAnswer.isEmpty()) {
            System.out.println("Error: Security answer cannot be empty.");
            return;
        }

        // CAPTCHA verification
        boolean captchaVerified = false;
        while (!captchaVerified) {
            // Generate a CAPTCHA code
            String captchaCode = CaptchaGenerator.generateCaptchaCode();
            System.out.println("Please enter the following CAPTCHA code:");
            CaptchaGenerator.printCaptchaCode(captchaCode);
            System.out.println("CAPTCHA Code:");
            String captchaInput = scanner.nextLine();

            if (captchaInput.equals(captchaCode)) {
                captchaVerified = true;
            } else {
                System.out.println("Incorrect CAPTCHA code. Please try again.");
            }
        }

        // Initialize new attributes
        List<Card> cardDeck = new ArrayList<>();
        int level = 1;
        int hp = 100;
        int xp = 0;
        int coins = 100;

        User user = new User(username, password, nickname, email, securityQuestion, securityAnswer, cardDeck, level, hp, xp, coins);
        User.addUser(user);

        System.out.println("User created successfully.");
    }

    private String generateRandomPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        return password.toString();
    }

    private boolean isValidUsername(String username) {
        return Pattern.matches("^[a-zA-Z0-9_]+$", username);
    }

    private boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        boolean hasLower = false, hasUpper = false, hasSpecial = false;
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) hasLower = true;
            if (Character.isUpperCase(c)) hasUpper = true;
            if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        return hasLower && hasUpper && hasSpecial;
    }

    private boolean isValidEmail(String email) {
        return Pattern.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$", email);
    }

    public boolean loginUser() {
        if (loggedInUser != null) {
            System.out.println("A user is already logged in. Please log out before logging in as a different user.");
            return true;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username:");
        String username = scanner.nextLine();

        User user = User.getUser(username);
        if (user == null) {
            System.out.println("Username doesn't exist!");
            return false;
        }

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        if (user.getPassword().equals(password)) {
            System.out.println("User logged in successfully!");
            user.setFailedLoginAttempts(0);
            loggedInUser = user;
            return true;
        } else {
            int failedAttempts = user.getFailedLoginAttempts() + 1;
            user.setFailedLoginAttempts(failedAttempts);
            long delay = 5 * failedAttempts;

            System.out.println("Password and Username don’t match! Try again in " + delay + " seconds.");
            try {
                TimeUnit.SECONDS.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
            return false;
        }
    }

    public void forgotPassword() {
        if (loggedInUser != null) {
            System.out.println("A user is already logged in. Please log out before attempting to reset a password.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username:");
        String username = scanner.nextLine();

        User user = User.getUser(username);
        if (user == null) {
            System.out.println("Username doesn't exist!");
            return;
        }

        System.out.println("Answer the security question: " + user.getSecurityQuestion().getQuestion());
        String answer = scanner.nextLine();

        if (user.getSecurityAnswer().equals(answer)) {
            System.out.println("Enter new password:");
            String newPassword = scanner.nextLine();
            if (!isValidPassword(newPassword)) {
                System.out.println("Error: Weak password. Password must be at least 8 characters long, contain at least one lowercase letter, one uppercase letter, and one special character.");
                return;
            }
            user.setPassword(newPassword);
            User.addUser(user); // Update user information
            System.out.println("Password changed successfully!");
        } else {
            System.out.println("Incorrect answer to the security question.");
        }
    }

    public void logoutUser() {
        if (loggedInUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }
        loggedInUser = null;
        System.out.println("User logged out successfully.");
    }
    public void viewUserProfile() {
        if (loggedInUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }
        System.out.println("Username: " + loggedInUser.getUsername());
        System.out.println("Nickname: " + loggedInUser.getNickname());
        System.out.println("Email: " + loggedInUser.getEmail());
        System.out.println("Level: " + loggedInUser.getLevel());
        System.out.println("HP: " + loggedInUser.getHp());
        System.out.println("XP: " + loggedInUser.getXp());
        System.out.println("Coins: " + loggedInUser.getCoins());
    }

    public void changeUsername() {
        if (loggedInUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new username:");
        String newUsername = scanner.nextLine();
        if (newUsername.isEmpty()) {
            System.out.println("Username cannot be empty.");
            return;
        }
        if (!isValidUsername(newUsername)) {
            System.out.println("Invalid username. Username should contain only letters, numbers, and underscores.");
            return;
        }
        if (User.usernameExists(newUsername)) {
            System.out.println("Username already exists.");
            return;
        }
        loggedInUser.setUsername(newUsername);
        User.updateUser(loggedInUser); // Save changes to the user
        System.out.println("Username changed successfully.");
    }

    public void changeNickname() {
        if (loggedInUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new nickname:");
        String newNickname = scanner.nextLine();
        if (newNickname.isEmpty()) {
            System.out.println("Nickname cannot be empty.");
            return;
        }
        loggedInUser.setNickname(newNickname);
        User.updateUser(loggedInUser); // Save changes to the user
        System.out.println("Nickname changed successfully.");
    }

    public void changePassword() {
        if (loggedInUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter current password:");
        String currentPassword = scanner.nextLine();
        if (!loggedInUser.getPassword().equals(currentPassword)) {
            System.out.println("Current password is incorrect!");
            return;
        }
        System.out.println("Enter new password:");
        String newPassword = scanner.nextLine();
        if (newPassword.equals(currentPassword)) {
            System.out.println("Please enter a new password!");
            return;
        }
        if (!isValidPassword(newPassword)) {
            System.out.println("Weak password. Password must be at least 8 characters long, contain at least one lowercase letter, one uppercase letter, and one special character.");
            return;
        }
        System.out.println("Confirm new password:");
        String confirmPassword = scanner.nextLine();
        if (!newPassword.equals(confirmPassword)) {
            System.out.println("Passwords do not match.");
            return;
        }
        loggedInUser.setPassword(newPassword);
        User.updateUser(loggedInUser); // Save changes to the user
        System.out.println("Password changed successfully.");
    }

    public void changeEmail() {
        if (loggedInUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new email:");
        String newEmail = scanner.nextLine();
        if (newEmail.isEmpty()) {
            System.out.println("Email cannot be empty.");
            return;
        }
        if (!isValidEmail(newEmail)) {
            System.out.println("Invalid email format.");
            return;
        }
        loggedInUser.setEmail(newEmail);
        User.updateUser(loggedInUser); // Save changes to the user
        System.out.println("Email changed successfully.");
    }





    // Implement methods for profile management like changePassword, changeEmail, etc.
}
