package duke;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;

import org.apache.commons.text.WordUtils;

import java.util.Arrays;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    private static final String LINE = "──────────────────────────────────────────\n";

    public static final DateTimeFormatter DATETIME_INPUT_FORMAT = DateTimeFormatter.ofPattern("d-M-y HHmm");
    public static final DateTimeFormatter DATETIME_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");

    /**
     * Pretty-prints an output string
     *
     * @param output The string representing the output.
     */
    public static void printMsg(String output) {
        String[] lines = output.split("\n");
        String newStr = Arrays.stream(lines).map(line ->
                        String.format("\t %s%s\n", line.replace(line.stripLeading(), ""),
                                WordUtils.wrap(line, 40, "\n\t ", false)))
                .reduce("", String::concat);
        System.out.printf("\t%s%s\t%s%n", LINE, newStr, LINE);
    }

    /**
     * Parses a {@code Task} from a string (from the Duke data file).
     *
     * @param s The string read from the Duke data file to be parsed.
     * @return the parsed {@code Task}
     * @throws DukeException when the string in the data file is invalid.
     */
    public static Task parseTask(String s) throws DukeException {
        String[] strings = s.split(" \\| ", -1);
        Task task;
        if (!strings[1].equals(" ") && !strings[1].equals("X")) {
            throw new DukeException("Error parsing Task");
        }
        boolean isDone = strings[1].equals("X");
        String description = strings[2];

        switch (strings[0]) {
        case "T":
            if (strings.length > 3) {
                throw new DukeException("Error parsing TodoTask");
            }
            task = new TodoTask(description, isDone);
            break;
        case "D":
            if (strings.length > 4) {
                throw new DukeException("Error parsing DeadlineTask");
            }
            task = new DeadlineTask(description, parseDateTime(strings[3]), isDone);
            break;
        case "E":
            if (strings.length > 4) {
                throw new DukeException("Error parsing EventTask");
            }
            task = new EventTask(description, parseDateTime(strings[3]), isDone);
            break;
        default:
            throw new DukeException("Error parsing Task");
        }
        return task;
    }

    /**
     * Parses a {@code LocalDateTime} from a string.
     * The string must be in the format "d-M-y HHmm".
     *
     * @param dateTime The string to parse.
     * @return The parsed {@code LocalDateTime}.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws DukeException {
        try {
            return LocalDateTime.parse(dateTime, DATETIME_INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException("DateTime should be in the format \"d-M-y HHmm\"");
        }
    }
}
