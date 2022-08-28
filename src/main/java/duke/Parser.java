package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Class containing parsing methods, or conversions.
 */
public class Parser {

    /**
     * Parses date and time string and converts it into a LocalDateTime.
     * @param dateTime string with date and time in format (dd/mm/yyyy hhmm).
     * @return LocalDateTime object containing date and time.
     */
    public static LocalDateTime dateParser(String dateTime) {
        String[] splitDateTime = dateTime.trim().split(" ");
        String[] dateArray = splitDateTime[0].split("/");
        String time = splitDateTime[1];

        return LocalDateTime.of(
                Integer.parseInt(dateArray[2]),
                Integer.parseInt(dateArray[1]),
                Integer.parseInt(dateArray[0]),
                Integer.parseInt(time.substring(0, 2)),
                Integer.parseInt(time.substring(2, 4))
        );
    }

    /**
     * Parses string containing task into a Task object.
     * @param task string containing task.
     * @return Task object.
     */
    public static Task fileStringToTask(String task) {
        String[] taskSplit = task.split("\\|");
        String type = taskSplit[0];
        switch (type) {
            case " T ":
                return new Todo(taskSplit[2].trim(), taskSplit[1].equals("1"));
            case " E ":
                return new Event(taskSplit[2].trim(), taskSplit[3], taskSplit[1].equals("1"));
            case " D ":
                return new Deadline(taskSplit[2].trim(), taskSplit[3], taskSplit[1].equals("1"));
            default:
                return null;
        }
    }

}
