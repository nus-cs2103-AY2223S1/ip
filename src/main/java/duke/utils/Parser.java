package duke.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.InvalidCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.exceptions.ParseDateTimeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

/**
 * Handles the parsing of user input.
 *
 * @author sikai00
 */
public class Parser {
    // Constants used by parseCommand and its prepare methods
    private static final String USER_INPUT_DELIMITER = " ";
    private static final InvalidCommand INVALID_NUMBER_FORMAT = new InvalidCommand("Task index should be integers!");
    // Constants used by parseDateTime and its helper methods
    private static final String DATETIME_DELIMITER = "[-:.|/]";
    private static final ParseDateTimeException ERR_WRONG_DATETIME_FORMAT = new ParseDateTimeException("Date and time"
            + "is in the wrong format! Correct format: yyyy-mm-dd HH:MM");

    /**
     * Parses the user input and returns the appropriate Command.
     *
     * @param userInput Raw user input
     * @return Command based on user input
     */
    public Command parseCommand(String userInput) {
        String command = userInput.split(USER_INPUT_DELIMITER, 2)[0].toLowerCase();

        switch (command) {
        case AddCommand.COMMAND_WORD:
            return prepareAdd(userInput);
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(userInput);
        case MarkCommand.COMMAND_WORD:
            return prepareMark(userInput);
        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmark(userInput);
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case FindCommand.COMMAND_WORD:
            return prepareFind(userInput);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            return new InvalidCommand("Sorry, I wasn't programmed to understand this...");
        }
    }

    /**
     * Prepares and returns a new AddCommand. However, if there are errors in the user input, a new InvalidCommand is
     * returned instead.
     * This method parses the user input to distinguish between the different Tasks
     * and returns an AddCommand with the correct Task.
     *
     * @param userInput Raw user input
     * @return AddCommand based on raw user input
     */
    private Command prepareAdd(String userInput) {
        String[] tokens = userInput.split(USER_INPUT_DELIMITER, 3);
        if (tokens.length < 3) {
            return new InvalidCommand("Oh no! Try add <task type> <description>!");
        }

        // TASK_WORDs are all in lowercase, to match all other class identifiers such as COMMAND_WORDs
        String taskType = tokens[1].toLowerCase();
        switch (taskType) {
        case Todo.TASK_WORD:
            String todoDescription = tokens[2].trim();
            Todo newTodo = new Todo(todoDescription, false);
            return new AddCommand(newTodo);
        case Deadline.TASK_WORD:
            try {
                String[] deadlineTokens = tokens[2].split("/by");
                if (deadlineTokens.length < 2) {
                    return new InvalidCommand("Oh no! Try doing 'add deadline <description> /by " + "<date>'!");
                }
                String deadlineDescription = deadlineTokens[0].trim();
                LocalDateTime by = parseDateTime(deadlineTokens[1].trim());
                Deadline newDeadline = new Deadline(deadlineDescription, false, by);
                return new AddCommand(newDeadline);
            } catch (ParseDateTimeException e) {
                return new InvalidCommand(e.getMessage());
            }
        case Event.TASK_WORD:
            try {
                String[] eventTokens = tokens[2].split("/at");
                if (eventTokens.length < 2) {
                    return new InvalidCommand("Oh no! Try doing 'add event <description> /at" + " <date>'!");
                }
                String eventDescription = eventTokens[0].trim();
                LocalDateTime at = parseDateTime(eventTokens[1].trim());
                Event newEvent = new Event(eventDescription, false, at);
                return new AddCommand(newEvent);
            } catch (ParseDateTimeException e) {
                return new InvalidCommand(e.getMessage());
            }
        default:
            return new InvalidCommand("There's no such event type as " + taskType + "!");
        }
    }

    /**
     * Prepares and returns a new DeleteCommand. However, if there are errors in the user input, a new InvalidCommand is
     * returned instead.
     *
     * @param userInput Raw user input
     * @return DeleteCommand based on raw user input
     */
    private Command prepareDelete(String userInput) {
        String[] tokens = userInput.split(USER_INPUT_DELIMITER, 2);
        if (tokens.length < 2) {
            return new InvalidCommand("Oh no! Try delete <task index>!");
        }
        try {
            int taskIndex = Integer.parseInt(tokens[1], 10) - 1;
            return new DeleteCommand(taskIndex);
        } catch (NumberFormatException e) {
            return INVALID_NUMBER_FORMAT;
        }
    }

    /**
     * Prepares and returns a new MarkCommand. However, if there are errors in the user input, a new InvalidCommand is
     * returned instead.
     *
     * @param userInput Raw user input
     * @return MarkCommand based on raw user input
     */
    private Command prepareMark(String userInput) {
        String[] tokens = userInput.split(USER_INPUT_DELIMITER, 2);
        if (tokens.length < 2) {
            return new InvalidCommand("Oh no! Try delete <mark index>!");
        }
        try {
            int taskIndex = Integer.parseInt(tokens[1], 10) - 1;
            return new MarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            return INVALID_NUMBER_FORMAT;
        }
    }

    /**
     * Prepares and returns a new UnmarkCommand. However, if there are errors in the user input, a new InvalidCommand is
     * returned instead.
     *
     * @param userInput Raw user input
     * @return UnmarkCommand based on raw user input
     */
    private Command prepareUnmark(String userInput) {
        String[] tokens = userInput.split(USER_INPUT_DELIMITER, 2);
        if (tokens.length < 2) {
            return new InvalidCommand("Oh no! Try delete <unmark index>!");
        }
        try {
            int taskIndex = Integer.parseInt(tokens[1], 10) - 1;
            return new UnmarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            return INVALID_NUMBER_FORMAT;
        }
    }

    /**
     * Prepares and returns a new FindCommand. However, if there are errors in the user input, a new InvalidCommand is
     * returned instead.
     *
     * @param userInput Raw user input
     * @return FindCommand based on raw user input
     */
    private Command prepareFind(String userInput) {
        String[] tokens = userInput.split(USER_INPUT_DELIMITER, 2);
        if (tokens.length < 2) {
            return new InvalidCommand("Oh no! Try find <keyword>!");
        }
        return new FindCommand(tokens[1]);
    }

    /**
     * Creates and returns LocalDate objects by parsing user's input for date.
     * User input date should follow the format (yyyy-mm-dd HH:MM), in 24-hour time format.
     * The date and time can have a delimiter of a character from "-:.|/" (characters within the double quotes).
     * The date and time must be separated by a single whitespace.
     *
     * @param dateText User's input of date and time following the format specified.
     * @return LocalDateTime
     * @throws ParseDateTimeException If the datetime user input is invalid
     */
    private static LocalDateTime parseDateTime(String dateText) throws ParseDateTimeException {
        String[] dateTextTokens = dateText.split(USER_INPUT_DELIMITER);
        // Require 2 tokens, as the first token represents date input, the second token represents time input
        if (dateTextTokens.length != 2) {
            throw new ParseDateTimeException("Make sure that you inputted both date and time!");
        }

        String parsedDate = parseDateTokens(dateTextTokens[0]);
        String parsedTime = parseTimeTokens(dateTextTokens[1]);
        // Create the default toString representation (ISO-8601 format, uuuu-MM-dd'T'HH:mm) or
        // (DateTimeFormatter#ISO_LOCAL_DATE_TIME) for easier parsing and creation of LocalDateTime
        String parsedDateTime = parsedDate + "T" + parsedTime;
        try {
            return LocalDateTime.parse(parsedDateTime);
        } catch (DateTimeParseException e) {
            // Unexpected errors, code should never reach here
            throw new ParseDateTimeException(e.getMessage());
        }
    }

    /**
     * Parses raw user input of date and returns a date that forms part of a parsable string to be used by
     * LocalDateTime::parse.
     *
     * @param unparsedDate User's input of date following the format specified.
     * @return Date string usable by LocalDateTime::parse
     * @throws ParseDateTimeException If the datetime user input is invalid
     */
    private static String parseDateTokens(String unparsedDate) throws ParseDateTimeException {
        String[] dateTokens = unparsedDate.split(DATETIME_DELIMITER);
        // Require 3 tokens to represent year, month and day
        if (dateTokens.length != 3) {
            throw ERR_WRONG_DATETIME_FORMAT;
        }
        if (dateTokens[0].length() != 4 || dateTokens[1].length() != 2 || dateTokens[2].length() != 2) {
            throw ERR_WRONG_DATETIME_FORMAT;
        }
        // Delimiter here is not a constant to follow DateTimeFormatter#ISO_LOCAL_DATE_TIME formatting
        return String.join("-", dateTokens);
    }

    /**
     * Parses raw user input of time and returns a time that forms part of a parsable string to be used by
     * LocalDateTime::parse.
     *
     * @param unparsedTime User's input of time following the format specified.
     * @return Date string usable by LocalDateTime::parse
     * @throws ParseDateTimeException If the datetime user input is invalid
     */
    private static String parseTimeTokens(String unparsedTime) throws ParseDateTimeException {
        String[] timeTokens = unparsedTime.split(DATETIME_DELIMITER);
        if (timeTokens.length != 2) {
            throw ERR_WRONG_DATETIME_FORMAT;
        }
        if (timeTokens[0].length() != 2 || timeTokens[1].length() != 2) {
            throw ERR_WRONG_DATETIME_FORMAT;
        }
        // Delimiter here is not a constant to follow DateTimeFormatter#ISO_LOCAL_DATE_TIME formatting
        return String.join(":", timeTokens);
    }
}
