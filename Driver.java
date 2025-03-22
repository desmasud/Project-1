import java.io.*;
import java.util.*;

public class Driver {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Driver <log_file>");
            System.exit(1);
        }

        String logFile = args[0];
        List<String> history = new ArrayList<>();

        Process loggerProcess = null;
        Process encryptionProcess = null;
        PrintWriter loggerWriter = null;
        PrintWriter encryptionWriter = null;
        BufferedReader encryptionReader = null;
        Scanner scanner = null;

        try {
            // Start Logger and Encryption processes
            loggerProcess = Runtime.getRuntime().exec(new String[]{"java", "-cp", ".", "Logger", logFile});
            encryptionProcess = Runtime.getRuntime().exec(new String[]{"java", "-cp", ".", "Encryption"});

            // Check if processes started correctly
            if (loggerProcess == null || encryptionProcess == null) {
                System.out.println("Failed to start Logger or Encryption process.");
                System.exit(1);
            }

            // Streams for communication
            loggerWriter = new PrintWriter(loggerProcess.getOutputStream(), true);
            encryptionWriter = new PrintWriter(encryptionProcess.getOutputStream(), true);
            encryptionReader = new BufferedReader(new InputStreamReader(encryptionProcess.getInputStream()));

            scanner = new Scanner(System.in);
            loggerWriter.println("START Driver started");

            while (true) {
                System.out.println("\nCommands: password, encrypt, decrypt, history, quit");
                System.out.print("Enter command: ");
                String command = scanner.nextLine().trim().toLowerCase();

                loggerWriter.println("COMMAND " + command);

                switch (command) {
                    case "password":
                        handlePassword(scanner, encryptionWriter, loggerWriter);
                        break;

                    case "encrypt":
                        handleTextOperation(scanner, encryptionWriter, encryptionReader, history, "ENCRYPT", loggerWriter);
                        break;

                    case "decrypt":
                        handleTextOperation(scanner, encryptionWriter, encryptionReader, history, "DECRYPT", loggerWriter);
                        break;

                    case "history":
                        showHistory(history);
                        break;

                    case "quit":
                        loggerWriter.println("EXIT Driver exiting");
                        encryptionWriter.println("QUIT");
                        loggerWriter.println("QUIT");

                        // Close streams and processes
                        return;

                    default:
                        System.out.println("Invalid command.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (loggerWriter != null) loggerWriter.close();
            if (encryptionWriter != null) encryptionWriter.close();
            if (encryptionReader != null) try { encryptionReader.close(); } catch (IOException e) { e.printStackTrace(); }
            if (scanner != null) scanner.close();
            if (loggerProcess != null) try { loggerProcess.waitFor(); } catch (InterruptedException e) { e.printStackTrace(); }
            if (encryptionProcess != null) try { encryptionProcess.waitFor(); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("Driver exited.");
        }
    }

    private static void handlePassword(Scanner scanner, PrintWriter encryptionWriter, PrintWriter loggerWriter) {
        System.out.print("Enter new password: ");
        String password = scanner.nextLine().trim();
        if (!password.matches("[a-zA-Z]+")) {
            System.out.println("Invalid input. Only letters allowed.");
            return;
        }
        encryptionWriter.println("PASS " + password);
        loggerWriter.println("PASS " + password);
        System.out.println("Password set.");
    }

    private static void handleTextOperation(Scanner scanner, PrintWriter encryptionWriter, BufferedReader encryptionReader, List<String> history, String operation, PrintWriter loggerWriter) throws IOException {
        System.out.println("Enter text to " + operation.toLowerCase() + " or choose from history:");
        for (int i = 0; i < history.size(); i++) {
            System.out.println((i + 1) + ". " + history.get(i));
        }
        System.out.print("Enter choice or new text: ");
        String input = scanner.nextLine().trim();

        // Select from history if a number is entered
        if (input.matches("\\d+") && Integer.parseInt(input) > 0 && Integer.parseInt(input) <= history.size()) {
            input = history.get(Integer.parseInt(input) - 1);
        }

        if (!input.matches("[a-zA-Z]+")) {
            System.out.println("Invalid input. Only letters allowed.");
            return;
        }

        encryptionWriter.println(operation + " " + input);
        String result = encryptionReader.readLine();

        if (result.startsWith("RESULT")) {
            result = result.substring(7); // Remove "RESULT " prefix
            System.out.println("Result: " + result);
            history.add(result);
            loggerWriter.println(operation + " " + result);
        } else {
            System.out.println(result); // Print error message
        }
    }

    private static void showHistory(List<String> history) {
        System.out.println("\nHistory:");
        for (int i = 0; i < history.size(); i++) {
            System.out.println((i + 1) + ". " + history.get(i));
        }
    }
}