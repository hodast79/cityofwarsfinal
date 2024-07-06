package controller;

import model.User;
import model.SecurityQuestion;

import java.util.Random;
import java.util.Scanner;

public class UserController {

    public void registerUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username:");
        String username = scanner.nextLine();

        if (User.usernameExists(username)) {
            System.out.println("Username already exists!");
            return;
        }

        System.out.println("Enter password (or type 'random' for a random password):");
        String password = scanner.nextLine();
        String confirmPassword = null;

        if ("random".equalsIgnoreCase(password)) {
            password = generateRandomPassword();
            System.out.println("Your random password: " + password);
        } else {
            System.out.println("Confirm password:");
            confirmPassword = scanner.nextLine();

            if (!password.equals(confirmPassword)) {
                System.out.println("Passwords do not match!");
                return;
            }
        }

        System.out.println("Enter nickname:");
        String nickname = scanner.nextLine();

        System.out.println("Enter email:");
        String email = scanner.nextLine();

        System.out.println("Choose a security question:");
        for (SecurityQuestion question : SecurityQuestion.values()) {
            System.out.println(question.ordinal() + 1 + ". " + question.getQuestion());
        }
        int questionChoice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        SecurityQuestion securityQuestion = SecurityQuestion.values()[questionChoice - 1];

        System.out.println("Answer the security question:");
        String securityAnswer = scanner.nextLine();

        User user = new User(username, password, nickname, email, securityQuestion, securityAnswer);
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

    public boolean loginUser() {
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
            return true;
        } else {
            user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);
            System.out.println("Password and Username don’t match! Try again in " + 5 * user.getFailedLoginAttempts() + " seconds.");
            return false;
        }
    }

    // Implement methods for profile management like changePassword, changeEmail, etc.
}
