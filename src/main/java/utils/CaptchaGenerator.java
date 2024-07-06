package utils;

import java.util.Random;

public class CaptchaGenerator {
    private static final String ASCII_DIGITS[] = {
            " _  | ||_|", // 0
            "     |  | ", // 1
            " _  _||_  ", // 2
            " _  _| _| ", // 3
            "   |_|  | ", // 4
            " _ |_  _| ", // 5
            " _ |_ |_| ", // 6
            " _   |  | ", // 7
            " _ |_||_| ", // 8
            " _ |_| _| "  // 9
    };

    public static String generateCaptchaCode() {
        Random random = new Random();
        int length = 5 + random.nextInt(3); // Generate number between 5 and 7
        StringBuilder captcha = new StringBuilder();

        for (int i = 0; i < length; i++) {
            captcha.append(random.nextInt(10));
        }

        return captcha.toString();
    }

    public static void printCaptchaCode(String captcha) {
        for (int row = 0; row < 3; row++) {
            for (int i = 0; i < captcha.length(); i++) {
                System.out.print(ASCII_DIGITS[captcha.charAt(i) - '0'].substring(row * 3, (row + 1) * 3));
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static String generateMathCaptcha() {
        Random random = new Random();
        int num1 = random.nextInt(10) + 1; // 1 to 10
        int num2 = random.nextInt(10) + 1; // 1 to 10
        char operator = random.nextBoolean() ? '+' : '-';
        String equation = num1 + " " + operator + " " + num2 + " = ?";
        return equation;
    }

    public static int solveMathCaptcha(String equation) {
        String[] parts = equation.split(" ");
        int num1 = Integer.parseInt(parts[0]);
        char operator = parts[1].charAt(0);
        int num2 = Integer.parseInt(parts[2]);

        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            default:
                throw new IllegalArgumentException("Unknown operator");
        }
    }
}
