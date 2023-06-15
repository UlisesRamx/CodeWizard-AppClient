package com.example.codewizard.segurity;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {

    // Función para generar el hash SHA-512 de una cadena
    public static String generateSHA512Hash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] digest = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Función para comparar una contraseña con su hash almacenado
    public static boolean comparePassword(String enteredPassword, String storedHash) {
        String enteredPasswordHash = generateSHA512Hash(enteredPassword);
        return enteredPasswordHash.equals(storedHash);
    }
}



