package anya;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static String parseCommand(String userInput) {
        return userInput.split(" ")[0];
    }

    // Non task related
    public static int parseCommandIndex(String userInput) {
        return Integer.parseInt(userInput.split(" ")[1]);
    }

    // anya.task.Task related
    public static String parseTaskName(String userInput) {
        if (userInput.contains(" /by ") || userInput.contains(" /at ")) {
            String inputTask = userInput.split(" ", 2)[1];
            String[] details = inputTask.split(" /");
            return details[0];
        }
        return userInput.split(" ", 2)[1];
    }

    public static LocalDateTime parseDateTime(String userInput) {
        String[] details = userInput.split(" /by ", 2);
        String dateTimeStr = details[1];
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr,
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        return dateTime;
    }

    public static String parseEventDetails(String userInput) {
        String[] details = userInput.split(" /at ", 2);
        String eventDetails = details[1];
        return eventDetails;
    }

    public static String parseKeyword(String userInput) {
        String keyword = userInput.split(" ", 2)[1];
        return keyword;
    }
}
