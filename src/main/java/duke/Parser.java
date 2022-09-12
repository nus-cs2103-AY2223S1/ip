package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import exceptions.EmptyNameException;



public class Parser {
    private static final String DEADLINE_REGEX = "/by";
    private static final String EVENT_REGEX = "/at";

    /**
     * Takes in a command from the user when the user wants to add a task and return the type of the task
     *
     * @param command User input
     * @return Type of Event
     */
    public static String getType(String command) {
        return command.split(" ", 2)[0].trim();

    }

    /**
     * Takes in a user input from user and returns the name of the task
     *
     * @param command User input
     * @return The name of the todo task
     * @throws EmptyNameException
     */
    public static String getTodoName(String command) throws EmptyNameException {
        return command.split(" ", 2)[1].trim();
    }

    /**
     * Takes in a user input from the user and returns the name of the deadline task
     *
     * @param command User input
     * @return Name of deadline task
     */
    public static String getDeadlineName(String command) {
        return command.split(DEADLINE_REGEX, 2)[0].trim().split(" ", 2)[1].trim();
    }

    /**
     * Takes in a user input from the user and returns the date of the deadline task
     *
     * @param command User input
     * @return Date of deadline task
     */
    public static String getDeadlineDate(String command) {
        return command.split(DEADLINE_REGEX, 2)[1].trim();
    }

    /**
     * Takes in a user input from the user and returns the name of the event task
     *
     * @param command User input
     * @return Name of event task
     */
    public static String getEventName(String command) {
        return command.split(EVENT_REGEX, 2)[0].trim().split(" ", 2)[1];
    }

    /**
     * Takes in a user input from the user and returns the date of the event task
     *
     * @param command User input
     * @return Date of event task
     */
    public static String getEventDate(String command) {
        return command.split(EVENT_REGEX, 2)[1].trim();
    }

    /**
     * Takes in a string which is in a date format and convert it to a date
     *
     * @param stringDate date in string form
     * @return LocalDate object
     */
    public static LocalDate convertStringToDate(String stringDate) {
        return LocalDate.parse(stringDate);
    }

    /**
     * Takes in a LocalDate object and convert it into a string based on the format
     *
     * @param date LocalDate object
     * @return string converted to the formatted "MMM dd yyyy" from the date object
     */
    public static String convertDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public static String getFindObject(String command) {
        return command.split(" ", 2)[1].trim();
    }
}
