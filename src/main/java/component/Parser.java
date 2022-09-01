package component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Parser class that parses user input.
 */
public class Parser {
    /**
     * Parses input and returns Command enumeration.
     * @param input Input of user
     * @return Type of input
     */
    public static Command parse(String input) {
        List<String> inputList = Stream.of(input.split(" "))
                .map(e -> new String(e))
                .collect(Collectors.toList());
        String command = inputList.get(0);
        switch (command) {
        case "event":
            return Command.CREATE_EVENT;
        case "deadline":
            return Command.CREATE_DEADLINE;
        case "todo":
            return Command.CREATE_TODO;
        case "delete":
            return Command.DELETE;
        case "mark":
            return Command.MARK;
        case "unmark":
            return Command.UNMARK;
        case "list":
            return Command.LIST;
        case "find":
            return Command.FIND;
        case "bye":
            return Command.EXIT;
        default:
            return Command.UNKNOWN;
        }
    }

    /**
     * Returns index of the intended task from command input string
     * @param input String input
     * @return integer index of task
     */
    public static int getTaskIndex(String input) {
        return Integer.parseInt(input.substring(input.length() - 1)) - 1;
    }
    public static String getKeyword(String input) {
        return input.substring(6);
    }

    /**
     * Returns a Task object according to the code of the task.
     * "D" is for deadline, "T" is for todo, and "E" is for event.
     * @param input String input
     * @param code String code of task
     * @return parsed Task object
     */
    public static Task parseTask(String input, String code) {
        Task newTask = null;
        if (code == "T") {
            String description = input.substring(5);
            newTask = new ToDo(description);
            return newTask;
        }
        String separator = code == "E" ? "/at" : "/by";
        int indexOfDateTime = input.indexOf(separator);
        String stringDateTime = input.substring(indexOfDateTime + 4);
        LocalDateTime dateTime = Parser.processDateTime(stringDateTime);
        String description = null;
        if (code == "E") {
            description = input.substring(6, indexOfDateTime - 1);
            newTask = new Event(dateTime, description);
        } else if (code == "D") {
            description = input.substring(9, indexOfDateTime - 1);
            newTask = new Deadline(dateTime, description);
        }
        return newTask;
    }

    /**
     * Parses LocalDateTime object from a String formatted date time.
     * @param stringDateTime String formatted date and time
     * @return LocalDateTime parsed date time
     */
    public static LocalDateTime processDateTime(String stringDateTime) {
        LocalDateTime dateTime = LocalDateTime.now();
        String date = "None";
        String time = "None";
        if (stringDateTime.length() > 9) {
            date = stringDateTime.substring(0, stringDateTime.indexOf(" "));
            time = stringDateTime.substring(stringDateTime.indexOf(" ") + 1);
        } else if (stringDateTime.length() == 8) {
            date = stringDateTime;
        } else {
            time = stringDateTime;
        }
        try {
            if (!date.equals("None") && !time.equals("None")) {
                DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyyMMdd");
                DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HHmm");
                LocalDate localDate = LocalDate.parse(date, formatDate);
                LocalTime localTime = LocalTime.parse(time, formatTime);
                return LocalDateTime.of(localDate, localTime);
            } else if (!date.equals("None")) {
                DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyyMMdd");
                DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HHmm");
                LocalDate localDate = LocalDate.parse(date, formatDate);
                LocalTime localTime = LocalTime.parse("0000", formatTime);
                return LocalDateTime.of(localDate, localTime);
            } else {
                DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HHmm");
                LocalTime localTime = LocalTime.parse(time, formatTime);
                return LocalDateTime.of(LocalDate.now(), localTime);
            }
        } catch (DateTimeParseException exception) {
            System.out.println(exception);
            System.out.println("Please enter date and time in YYYYMMDD HHMM format");
        }
        return dateTime;
    }
}