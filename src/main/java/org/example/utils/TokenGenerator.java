package org.example.utils;

import java.util.UUID;

public class TokenGenerator {
    public static String generateToken(String username) {
        // Generiere einen Token, der den Benutzernamen und eine UUID kombiniert
        return username + "_" + UUID.randomUUID().toString();
    }
}
