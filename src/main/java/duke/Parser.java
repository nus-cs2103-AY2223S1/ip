package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parser class deals with making sense of the user command.
 */
public class Parser {
    /**
     * Parses the command typed by the user.
     *
     * @param str the string input by the user
     * @param tasks the TaskList
     * @throws DukeException if command is invalid
     */
    public static void parseCommand(String str, TaskList tasks) throws DukeException {
        String[] splitStr = str.split(" ", 2);
        switch (splitStr[0]) {
            case "list":
                tasks.printTaskList();
                break;
            case "mark":
                tasks.mark(splitStr);
                break;
            case "unmark":
                tasks.unmark(splitStr);
                break;
            case "todo":
                tasks.addTask(splitStr, Duke.TaskType.TODO);
                break;
            case "deadline":
                tasks.addTask(splitStr, Duke.TaskType.DEADLINE);
                break;
            case "event":
                tasks.addTask(splitStr, Duke.TaskType.EVENT);
                break;
            case "delete":
                tasks.deleteTask(splitStr);
                break;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means!");
        }
    }

    /**
     * Parses a string into type LocalDateTime.
     *
     * @param str a string that represents date and time
     * @return a LocalDateTime representing the date and time
     * @throws DukeException if the given input is not in the specified format
     */
    public static LocalDateTime parseDateTime(String str) throws DukeException {
        String[] dateTimeFormat = {
                "yyyy/MM/dd HHmm",
                "yyyy-MM-dd HHmm",
                "dd/MM/yyyy HHmm",
                "d/MM/yyyy HHmm",
                "d/M/yyyy HHmm",
        };
        String[] dateFormat = {
                "yyyy/MM/dd",
                "yyyy-MM-dd",
                "yyyy-MM-d",
                "yyyy-M-dd",
                "yyyy-M-d",
                "dd/MM/yyyy",
                "d/MM/yyyy",
                "d/M/yyyy",
        };
        for (String format : dateTimeFormat) {
            try {
                return LocalDateTime.parse(str, DateTimeFormatter.ofPattern(format));
            } catch (DateTimeParseException ignored) {
            }
        }
        for (String format : dateFormat) {
            try {
                LocalDate date = LocalDate.parse(str, DateTimeFormatter.ofPattern(format));
                return date.atStartOfDay();
            } catch (DateTimeParseException ignored) {
            }
        }
        throw new DukeException("Please specify the date and time in YYYY-MM-DD TTTT format.");
    }
}
