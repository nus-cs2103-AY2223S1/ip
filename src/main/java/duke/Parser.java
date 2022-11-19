package duke;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;


/**
 * Parser is used to parse the user's input and return specific phrases.
 *
 * @author Sean Lam
 */
public class Parser {
    //example input to parse: deadline taskToDo /by 2022-02-03 18:00-19:00
    protected static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yy");
    protected static final DateTimeFormatter INPUT_TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Returns command to execute.
     *
     * @param userInput user input
     * @return command
     */
    public static String getCommand(String userInput) {
        String[] words = userInput.split(" ");
        return words[0];
    }

    /**
     * Returns task's description.
     *
     * @param userInput user input
     * @return description
     */
    public static String getDescription(String userInput) {
        String[] words = userInput.split(" ", 2);
        String[] description = words[1].split(" /");
        return description[0];
    }

    /**
     * Returns date of deadline.
     *
     * @param userInput user input
     * @return date
     */
    public static LocalDate getDate(String userInput) {
        String[] words = userInput.split(" /");
        String[] dateTime = words[1].split(" ");
        return LocalDate.parse(dateTime[1], INPUT_DATE_FORMAT);
    }

    /**
     * Returns start time.
     *
     * @param userInput user input
     * @return start time
     */
    public static LocalTime getFrom(String userInput) {
        String[] words = userInput.split(" /");
        String[] dateTime = words[1].split(" ");
        String[] times = dateTime[2].split("-");
        return LocalTime.parse(times[0], INPUT_TIME_FORMAT);
    }

    /**
     * Returns end time.
     *
     * @param userInput user input
     * @return end time
     */
    public static LocalTime getTo(String userInput) throws DukeException{
        String[] words = userInput.split(" /");
        String[] dateTime = words[1].split(" ");
        String[] times = dateTime[2].split("-");

        if (times.length == 1) {
            throw new DukeException("Invalid input detected. Required input format: " +
                    "event {description} /at dd-MM-yy HH:mm-HH:mm");
        }
        return LocalTime.parse(times[1], INPUT_TIME_FORMAT);
    }

    /**
     * Returns index of task.
     *
     * @param userInput user input
     * @return index
     */
    public static int getIndex(String userInput) {
        return Integer.parseInt(getDescription(userInput)) - 1;
    }

    /**
     * Checks if user input a valid description
     *
     * @param userInput user input
     * @return Whether the description is valid
     */
    public static boolean isInvalidDescription(String userInput) {
        return userInput.split(" ").length <= 1;
    }

    /**
     * Checks if user input a valid DateTime
     *
     * @param userInput user input
     * @return Whether the DateTime is valid
     */
    public static boolean isInvalidDateTime(String userInput) {
        String[] words = userInput.split("/");
        String[] dateTime = words[1].split(" ");
        return dateTime.length <= 1;
    }

    public static void isInvalidInput(String input) throws DukeException {
        if (isInvalidDescription(input)) {
            throw new DukeException("OOPS!!! The description of a task cannot be empty.");
        } else if (Parser.isInvalidDateTime(input)) {
            throw new DukeException("OOPS!!! The time and date of the task cannot be empty.");
        }
    }
}
