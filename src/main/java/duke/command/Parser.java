package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Represents all command parsing logic.
 */
public class Parser {
    public static final String ERROR_UNKNOWN_COMMAND = "I don't know this command!";
    public static final String ERROR_EMPTY_DESCRIPTION = "Description should not be empty.";
    public static final String ERROR_NOT_NUMBER = "%s is not a number. e.g 5 is a number, five is a string.";
    public static final String ERROR_WRONG_USAGE_OF_EVENT =
            "Wrong usage of event.\nUsage: event some description /at some date";
    public static final String ERROR_WRONG_USAGE_OF_DEADLINE =
            "Wrong usage of deadline.\nUsage: deadline some description /by some date";
    private static final String ERROR_NOT_DATE = "%s is not a date.";

    /**
     * Parses user command into Command instance to execute.
     * @param fullCommand String representing the command input of the user.
     * @return Command to be executed.
     * @throws DukeException If the fullCommand is not valid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] inputArray = fullCommand.split(" +", 2);
        String firstWord = inputArray[0];
        String argsString = "";
        if (inputArray.length == 2) {
            argsString = inputArray[1];
        }

        Command resultCommand;
        // commands
        switch (firstWord) {
        case "bye":
            resultCommand = new ExitCommand();
            break;
        case "list":
            resultCommand = new ListCommand();
            break;
        case "mark":
            resultCommand = new MarkCommand(parseInt(argsString));
            break;
        case "unmark":
            resultCommand = new UnmarkCommand(parseInt(argsString));
            break;
        case "todo":
            resultCommand = new AddCommand(parseTodo(argsString));
            break;
        case "deadline":
            resultCommand = new AddCommand(parseDeadline(argsString));
            break;
        case "event":
            resultCommand = new AddCommand(parseEvent(argsString));
            break;
        case "delete":
            ArrayList<Integer> indexes = new ArrayList<>();
            for (String indexString : argsString.split(" +")) {
                Integer indexInt = parseInt(indexString);
                indexes.add(indexInt);
            }

            resultCommand = new DeleteCommand(indexes);
            break;
        case "on":
            resultCommand = new TaskOnDateCommand(parseDate(argsString));
            break;
        case "find":
            resultCommand = new FindCommand(argsString);
            break;
        default:
            throw new DukeException(Parser.ERROR_UNKNOWN_COMMAND);
        }

        return resultCommand;
    }

    /**
     * Parses description into Todo instance.
     *
     * @param description Description of Todo.
     * @return Todo instance.
     * @throws DukeException If description is empty.
     */
    public static Todo parseTodo(String description) throws DukeException {
        if (description.length() == 0) {
            throw new DukeException(Parser.ERROR_EMPTY_DESCRIPTION);
        }
        return new Todo(description);
    }

    /**
     * Parses argsString into Deadline instance.
     *
     * @param argsString String of format "description /by date".
     * @return Deadline instance.
     * @throws DukeException If argsString format is wrong.
     */
    public static Deadline parseDeadline(String argsString) throws DukeException {
        String[] args = argsString.split(" */by *");
        if (args.length != 2) {
            throw new DukeException(Parser.ERROR_WRONG_USAGE_OF_DEADLINE);
        }
        String description = args[0];
        String by = args[1];
        LocalDate byDate = parseDate(by);
        if (description.length() == 0) {
            throw new DukeException(Parser.ERROR_EMPTY_DESCRIPTION);
        }

        return new Deadline(description, byDate);
    }

    /**
     * Parses argsString into Event instance.
     *
     * @param argsString String of format "description /at date".
     * @return Event instance.
     * @throws DukeException If argsString format is wrong.
     */
    public static Event parseEvent(String argsString) throws DukeException {
        String[] args = argsString.split(" */at *");
        if (args.length != 2) {
            throw new DukeException(Parser.ERROR_WRONG_USAGE_OF_EVENT);
        }
        String description = args[0];
        if (description.length() == 0) {
            throw new DukeException(Parser.ERROR_EMPTY_DESCRIPTION);
        }
        String at = args[1];
        LocalDate atDate = parseDate(at);
        return new Event(description, atDate);
    }

    /**
     * Parses String to Integer.
     *
     * @param number String representation of integer.
     * @return Integer with value integer.
     * @throws DukeException If number is not of number format.
     */
    public static int parseInt(String number) throws DukeException {
        try {
            return Integer.parseInt(number);
        } catch (java.lang.NumberFormatException e) {
            throw new DukeException(String.format(Parser.ERROR_NOT_NUMBER, number));
        }
    }

    /**
     * Parses String to Date.
     *
     * @param date String representation of date
     * @return LocalDate with value date.
     * @throws DukeException If date is not of date format.
     */
    public static LocalDate parseDate(String date) throws DukeException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException(String.format(Parser.ERROR_NOT_DATE, date));
        }
    }
}
