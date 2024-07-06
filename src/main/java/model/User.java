package model;

import utils.FileManager;

import java.io.Serializable;
import java.util.HashMap;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String nickname;
    private String email;
    private SecurityQuestion securityQuestion;
    private String securityAnswer;
    private static HashMap<String, User> users = FileManager.loadUsers();

    public User(String username, String password, String nickname, String email, SecurityQuestion securityQuestion, String securityAnswer) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }

    // Getters and Setters

    public String getUsername() {
        return username;
    }

    public static void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public static User getUser(String username) {
        return users.get(username);
    }

    public static boolean usernameExists(String username) {
        return users.containsKey(username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(int failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }
}
