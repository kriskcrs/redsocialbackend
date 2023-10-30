package com.desarrolloweb.redsocial.Tools;

import java.security.SecureRandom;

public class PasswordGenerator {

    String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    String DIGITS = "0123456789";
    String message = "La suma de los requisitos no coincide con la longitud deseada.";


    public  String generatePassword(int length, int uppercaseCount, int lowercaseCount, int digitCount) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        int totalCharacters = uppercaseCount + lowercaseCount + digitCount;
        if (totalCharacters != length) {
            throw new IllegalArgumentException(message);
        }
        for (int i = 0; i < uppercaseCount; i++) {
            password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        }
        for (int i = 0; i < lowercaseCount; i++) {
            password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        }
        for (int i = 0; i < digitCount; i++) {
            password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        }
        for (int i = password.length() - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = password.charAt(index);
            password.setCharAt(index, password.charAt(i));
            password.setCharAt(i, temp);
        }
        return password.toString();
    }


}
