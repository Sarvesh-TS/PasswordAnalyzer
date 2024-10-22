import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Checks for minimum length=12, at least 3 uppercase, at least 2 special characters, at least numbers, check for common passwords.

public class PasswordStrengthChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        String strength = checkPasswordStrength(password);
        System.out.println("Password Strength: " + strength);

        scanner.close();
    }

    public static String checkPasswordStrength(String password) {
        // Check length
        if (password.length() < 12) {
            return "Weak";
        }

        // Check for at least 3 uppercase characters
        int uppercaseCount = 0;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                uppercaseCount++;
            }
        }

        // Check for at least 2 special characters
        int specialCount = 0;
        Pattern specialPattern = Pattern.compile("[@#$%^&+=!]");
        Matcher specialMatcher = specialPattern.matcher(password);
        while (specialMatcher.find()) {
            specialCount++;
        }

        // Check for at least 3 numbers
        int digitCount = 0;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                digitCount++;
            }
        }

        // Check if the password is not a dictionary word or a common name
        String[] dictionary = {"password", "admin", "123456", "qwerty"};
        for (String word : dictionary) {
            if (password.toLowerCase().contains(word)) {
                return "Weak";
            }
        }

        // Check for strength
        if (uppercaseCount >= 3 && specialCount >= 2 && digitCount >= 3) {
            return "Strong";
        } else if (uppercaseCount == 1 && specialCount == 1) {
            return "Medium";
        } else {
            return "Weak";
        }
    }
}
