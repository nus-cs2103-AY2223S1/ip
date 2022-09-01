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
    public static String parseTaskName(String userInput) throws AnyaException {
        try {
            if (userInput.contains(" /by ") || userInput.contains(" /at ")) {
                String inputTask = userInput.split(" ", 2)[1];
                String[] details = inputTask.split(" /");
                return details[0];
            }
            return userInput.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AnyaException("The task name cannot be empty.");
        }
    }

    public static LocalDateTime parseDateTime(String userInput) throws AnyaException {
        String[] details = userInput.split(" /by ", 2);
        try {
            String dateTimeStr = details[1];
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr,
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            return dateTime;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AnyaException("deadline command missing /by");
        }
    }

    public static String parseEventDetails(String userInput) throws AnyaException {
        String[] details = userInput.split(" /at ", 2);
        try {
            String eventDetails = details[1];
            return eventDetails;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AnyaException("event command missing /at");
        }
    }

    public static String parseKeyword(String userInput) {
        String keyword = userInput.split(" ", 2)[1];
        return keyword;
    }
}
