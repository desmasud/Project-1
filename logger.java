import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Logger <log_file>");
            System.exit(1);
        }

        String logFile = args[0];
        Scanner scanner = new Scanner(System.in);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
            while (true) {
                String logMessage = scanner.nextLine();
                if (logMessage.equals("QUIT")) {
                    break;
                }
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                String[] parts = logMessage.split(" ", 2);
                if (parts.length < 2) {
                    System.out.println("Invalid log message format. Please use '<action> <message>' format.");
                    continue;
                }
                String action = parts[0];
                String message = parts[1];
                writer.write(String.format("%s [%s] %s%n", timestamp, action, message));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}