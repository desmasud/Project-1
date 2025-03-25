import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Encryption {
    private String passkey;

    public Encryption() {
        this.passkey = "";
    }

    public void setPasskey(String passkey) {
        this.passkey = passkey.toUpperCase();
    }

    public String encrypt(String message) {
        if (passkey.isEmpty()) {
            return "ERROR passoword is not set";
        }
        return "RESULT " + processMessage(message, true);
    }

    public String decrypt(String message) {
        if (passkey.isEmpty()) {
            return "ERROR password is not set";
        }
        return "RESULT " + processMessage(message, false);
    }

    private String processMessage(String message, boolean encrypt) {
        StringBuilder result = new StringBuilder();
        message = message.toUpperCase();
        int keyIndex = 0;
        int keyLength = passkey.length();

        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                int shift = passkey.charAt(keyIndex % keyLength) - 'A';
                char base = 'A';
                char processedChar = (char) ((c - base + (encrypt ? shift : -shift + 26)) % 26 + base);
                result.append(processedChar);
                keyIndex++;
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public String handleCommand(String command) {
        String[] parts = command.split(" ", 2);
        if (parts.length == 0) {
            return "ERROR Invalid command.";
        }

        switch (parts[0].toUpperCase()) {
            case "PASSKEY":
                if (parts.length < 2) {
                    return "Password required.";
                }
                setPasskey(parts[1]);
                return "RESULT";
            case "ENCRYPT":
                if (parts.length < 2) {
                    return "ERROR Password not set";
                }
                return encrypt(parts[1]);
            case "DECRYPT":
                if (parts.length < 2) {
                    return "ERROR password not set";
                }
                return decrypt(parts[1]);
            case "QUIT":
                return "Exiting the program. ";
            default:
                return "ERROR Unknown command.";
        }
    }

    public static void main(String[] args) {
        Encryption encryption = new Encryption();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            String response = encryption.handleCommand(input);
            System.out.println(response);
            if (input.equalsIgnoreCase("QUIT")) {
                break;
            }
        }
        scanner.close();
    }
}
