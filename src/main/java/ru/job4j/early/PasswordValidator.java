package ru.job4j.early;

import java.util.function.Predicate;

public class PasswordValidator {

    public static String validate(String password) {
        String message = " Password length must have 8 - 32 characters"
                .concat(", at least one uppercase and lowercase character")
                .concat(" at least one number")
                .concat(" at least one special character")
                .concat(" must not contain substrings case insensitive");
        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null." + message);
        }
        if (password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty." + message);
        }
        char[] passwordChars = password.toCharArray();
        int length = password.length();
        if (length < 8 || length > 32) {
            throw new IllegalArgumentException("Wrong password length!" + message);
        }
        if (!checkString(passwordChars, Character::isDigit)) {
            throw new IllegalArgumentException("At least one character should be a digit!" + message);
        }
        if (!checkString(passwordChars, Character::isUpperCase)) {
            throw new IllegalArgumentException("At least one character should be a letter in upper case!" + message);
        }
        if (!checkString(passwordChars, Character::isLowerCase)) {
            throw new IllegalArgumentException("At least one character should be a letter in lower case!" + message);
        }
        if (!checkString(passwordChars, character -> !Character.isLetterOrDigit(character))) {
            throw new IllegalArgumentException("At least one character should be a special symbol!" + message);
        }
        String[] simples = new String[]{"qwerty", "12345", "password", "admin", "user"};
        String pass = password.toLowerCase();
        for (String simple : simples) {
            if (pass.contains(simple)) {
                throw new IllegalArgumentException("Contains bad sequence of characters!" + message);
            }
        }
        return password;
    }

    private static boolean checkString(char[] password, Predicate<Character> predicate) {
        boolean result = false;
        for (char symbol : password) {
            if (predicate.test(symbol)) {
                result = true;
            }
        }
        return result;
    }
}
