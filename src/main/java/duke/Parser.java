package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class to make sense of user inputs.
 *
 * @author Elbert Benedict
 */
public class Parser {
    /**
     * Converts a date string to a LocalDate instance.
     *
     * @param string the date string.
     * @return The LocalDate instance representing the date string.
     * @throws DukeException If the string is not a valid date.
     */
    public static LocalDate convertToDateObj(String string) throws DukeException {
        try {
            return LocalDate.parse(string);
        } catch (DateTimeException e) {
            throw new DukeException("Please Input a Valid Date");
        }
    }

    /**
     * Returns the string representation of a Localdate instance.
     *
     * @param date the Localdate instance.
     * @return string representation of a Localdate instance.
     */
    public static String printDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Converts a Localdate instance to save file string
     * representation.
     *
     * @param date the Localdate instance.
     * @return the date save file string representation.
     */
    public static String printSaveFileDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Parses user input to a string array.
     *
     * @param input the user input.
     * @return A string array of the parsed input.
     * @throws DukeException If input is not valid.
     */
    public static String[] parseUserInput(String input) throws DukeException {
        String[] parsed;
        //Split by regex to get command
        String[] splitted = input.split("\\s", 2);
        String command = splitted[0];

        switch (command) {
        case "todo":
            //No Description Given
            if (splitted.length < 2) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            //parsed is ["command", "task description"]
            parsed = splitted;
            break;

        case "deadline":
            //No Description Given
            if (splitted.length < 2) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            //some regex to parse the strings correctly
            //0th index: task, 1st index: deadline
            String[] splittedDeadline = splitted[1].split("\\s/by\\s", 2);
            String deadlineTask = splittedDeadline[0];

            //No Description Given
            if (deadlineTask.equals("") || deadlineTask.startsWith("/by")) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }

            //No Deadline Given
            if (splittedDeadline.length == 1) {
                throw new DukeException("please specify a deadline");
            }

            String deadlineDate = splittedDeadline[1];
            parsed = new String[]{command, deadlineTask, deadlineDate};
            break;

        case "event":
            //No Description Given
            if (splitted.length < 2) {
                throw new DukeException("The description of an event cannot be empty.");
            }
            //some regex to parse the strings correctly
            //0th index: event, 1st index: date
            String[] splittedEvent = input.split("\\s", 2)
                    [1].split("\\s/at\\s", 2);
            String eventString = splittedEvent[0];

            //No Description Given
            if (eventString.equals("") || eventString.startsWith("/at")) {
                throw new DukeException("The description of an event cannot be empty.");
            }

            //No Deadline Given
            if (splittedEvent.length == 1) {
                throw new DukeException("please specify a date");
            }

            String eventDate = splittedEvent[1];

            parsed = new String[]{command, eventString, eventDate};
            break;

        case "mark": case "unmark": case "delete":
            //No Index Given
            if (splitted.length < 2) {
                throw new DukeException("No Index Given");
            }
            //parsed is ["command", "${index}"]
            parsed = splitted;
            break;

        case "find":
            //No keyword provided
            if (splitted.length < 2) {
                throw new DukeException("Please provide a keyword");
            }
            //parsed is ["command", "task"]
            parsed = splitted;
            break;

        case "bye":
            parsed = new String[]{"bye"};
            break;

        case "list":
            parsed = new String[]{"list"};
            break;

        default:
            throw new DukeException("Command Not Found!");
        }

        return parsed;
    }
}
