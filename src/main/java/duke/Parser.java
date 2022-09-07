package duke;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;

/**
 * Parser class deals with making sense of the user command.
 */
public class Parser {
    private static final String[] DATE_TIME_FORMATS = {
        "yyyy/MM/dd HHmm",
        "yyyy-MM-dd HHmm",
        "yyyy-MM-d HHmm",
        "yyyy-M-dd HHmm",
        "yyyy-M-d HHmm",
        "dd/MM/yyyy HHmm",
        "d/MM/yyyy HHmm",
        "d/M/yyyy HHmm",
        "dd/M/yyyy HHmm",
    };

    private static final String[] DATE_FORMATS = {
        "yyyy/MM/dd",
        "yyyy-MM-dd",
        "yyyy-MM-d",
        "yyyy-M-dd",
        "yyyy-M-d",
        "dd/MM/yyyy",
        "d/MM/yyyy",
        "d/M/yyyy",
        "dd/M/yyyy",
    };

    private static final String[] DAY_FORMATS = {
        "EEE",
        "EEEE",
    };

    /**
     * Parses a string into type LocalDateTime.
     *
     * @param input a string that represents date and time
     * @return a LocalDateTime representing the date and time
     * @throws DukeException if the given input is not in the specified format
     */
    public static LocalDateTime parseDateTime(String input) throws DukeException {
        LocalDateTime dateTime;
        LocalDateTime currDateTime = LocalDate.now().atStartOfDay();
        String inputFirstLtrCapped = input.substring(0, 1).toUpperCase() + input.substring(1);

        for (String format : DATE_TIME_FORMATS) {
            try {
                dateTime = LocalDateTime.parse(input, DateTimeFormatter.ofPattern(format));
            } catch (DateTimeParseException e) {
                // Proceeds to try the next format
                continue;
            }
            return dateTime;
        }
        for (String format : DATE_FORMATS) {
            try {
                dateTime = LocalDate.parse(input, DateTimeFormatter.ofPattern(format)).atStartOfDay();
            } catch (DateTimeParseException e) {
                // Proceeds to try the next format
                continue;
            }
            return dateTime;
        }
        for (String format : DAY_FORMATS) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                TemporalAccessor accessor = formatter.parse(inputFirstLtrCapped);
                dateTime = currDateTime.with(TemporalAdjusters.next(DayOfWeek.from(accessor)));
            } catch (DateTimeParseException e) {
                // Proceeds to try the next format
                continue;
            }
            return dateTime;
        }
        throw new DukeException("Please specify the date and time in YYYY-MM-DD TTTT format.");
    }

    /**
     * Parses the command typed by the user.
     *
     * @param str the string input by the user
     * @param tasks the TaskList
     * @return the String representation of the response
     * @throws DukeException if command is invalid
     */
    public static String parseCommand(String str, TaskList tasks) throws DukeException {
        String response;
        String[] splitStr = str.split(" ", 2);

        switch (splitStr[0]) {
        case "list":
            response = tasks.printTaskList();
            break;
        case "mark":
            response = tasks.mark(splitStr);
            break;
        case "unmark":
            response = tasks.unmark(splitStr);
            break;
        case "todo":
            response = tasks.addTask(splitStr, Duke.TaskType.TODO);
            break;
        case "deadline":
            response = tasks.addTask(splitStr, Duke.TaskType.DEADLINE);
            break;
        case "event":
            response = tasks.addTask(splitStr, Duke.TaskType.EVENT);
            break;
        case "delete":
            response = tasks.deleteTask(splitStr);
            break;
        case "find":
            response = tasks.findTasks(splitStr);
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means!");
        }
        return response;
    }
}
