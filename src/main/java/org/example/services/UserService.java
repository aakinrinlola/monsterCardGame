package org.example.services;

import org.example.utils.TokenGenerator;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private static UserService instance;
    private final Map<String, String> users = new HashMap<>(); // Benutzername -> Passwort
    private final Map<String, String> tokens = new HashMap<>(); // Benutzername -> Token

    // Singleton-Konstruktor
    private UserService() {}

    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    // Registrierung eines neuen Benutzers
    public boolean registerUser(String username, String password) {
        if (users.containsKey(username)) {
            System.out.println("Registration failed: Username already exists.");
            return false;
        }

        // Benutzer speichern (kein Hashing für Einfachheit, später erweiterbar)
        users.put(username, password);
        System.out.println("Registration successful for user: " + username);
        return true;
    }

    // Anmeldung eines Benutzers
    public String loginUser(String username, String password) {
        if (users.containsKey(username) && users.get(username).equals(password)) {
            // Token generieren mit TokenGenerator
            String token = TokenGenerator.generateToken(username);
            tokens.put(username, token);
            System.out.println("Login successful for user: " + username);
            return token;
        }

        System.out.println("Login failed: Invalid username or password.");
        return null;
    }

    // Token validieren
    public boolean validateToken(String username, String token) {
        return tokens.containsKey(username) && tokens.get(username).equals(token);
    }
}
