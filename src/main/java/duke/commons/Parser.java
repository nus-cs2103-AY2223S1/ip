package duke.commons;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;
import duke.commands.UpdateCommand;
import duke.exceptions.DukeException;

/**
 * This class deals with making sense of the user command.
 * Parses text command from user into instructions understood by Duke.
 */
public class Parser {
    /** This line of code specifies a date string following after */
    public static final String DATE_SPECIFIER = "/by";
    /** This line of code specifies a datetime string following after */
    public static final String DATETIME_SPECIFIER = "/at";

    /** Date format for user input and date stored in storage */
    private static final DateTimeFormatter DATE_DATA_FORMAT = DateTimeFormatter
            .ofPattern("dd/MM/yyyy HHmm");
    /** Date and time format for user input and datetime stored in storage */
    private static final DateTimeFormatter DATETIME_DATA_FORMAT = DateTimeFormatter
            .ofPattern("dd/MM/yyyy");
    /** Date format for message printed by Duke */
    private static final DateTimeFormatter DATE_MESSAGE_FORMAT = DateTimeFormatter
            .ofPattern("hh:mma MMM dd yyyy");
    /** Date and time format for message printed by Duke */
    private static final DateTimeFormatter DATETIME_MESSAGE_FORMAT = DateTimeFormatter
            .ofPattern("MMM dd yyyy");

    private static final String UNDECIPHERABLE_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :(";
    private static final String WRONG_COMMAND_PARAMETER_MESSAGE = "OOPS!!! Wrong command parameters!";
    private static final String INVALID_INDEX_MESSAGE = "OOPS!!! Invalid task index";
    private static final String DATE_FORMAT_PROMPT_MESSAGE = "Please enter a valid date format:\n"
            + "day/month/year -> dd/mm/yyyy";
    private static final String DATETIME_FORMAT_PROMPT_MESSAGE = "Please enter a valid datetime format:\n"
            + "day/month/year (24hour time) -> dd/mm/yyyy HHmm";

    /**
     * Returns a command based on user input specifications.
     *
     * @param input User input.
     * @return Command understood from user input specifications.
     * @throws DukeException If user input is invalid.
     */
    public static Command parseCommand(String input) throws DukeException {
        try {
            String[] parsedSpecifications = input.split(" ", 2);
            switch (parsedSpecifications[0]) {
            case ByeCommand.COMMAND_WORD:
                return new ByeCommand();
            case ListCommand.COMMAND_WORD:
                return new ListCommand();
            case MarkCommand.COMMAND_WORD:
                return new MarkCommand(parseIndex(parsedSpecifications[1].strip()));
            case UnmarkCommand.COMMAND_WORD:
                return new UnmarkCommand(parseIndex(parsedSpecifications[1].strip()));
            case DeleteCommand.COMMAND_WORD:
                return new DeleteCommand(parseIndex(parsedSpecifications[1].strip()));
            case FindCommand.COMMAND_WORD:
                return new FindCommand(parsedSpecifications[1].strip());
            case TodoCommand.COMMAND_WORD:
                return new TodoCommand(parsedSpecifications[1].strip());
            case DeadlineCommand.COMMAND_WORD:
                return parseForDeadline(parsedSpecifications[1].strip());
            case EventCommand.COMMAND_WORD:
                return parseForEvent(parsedSpecifications[1].strip());
            case UpdateCommand.COMMAND_WORD:
                return parseForUpdate(parsedSpecifications[1].strip());
            default:
                throw new DukeException(UNDECIPHERABLE_MESSAGE);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(WRONG_COMMAND_PARAMETER_MESSAGE);
        }
    }

    /**
     * Parses string of user input into index of task.
     *
     * @param input Input string.
     * @return Task index specified by user input.
     * @throws DukeException If input is not an index string.
     */
    private static int parseIndex(String input) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(input) - 1;
            return index;
        } catch (NumberFormatException e) {
            throw new DukeException(INVALID_INDEX_MESSAGE);
        }
    }

    /**
     * Parses user input by understanding specifications to add a deadline task.
     *
     * @param input User input specifying deadline details.
     * @return Deadline command based on parsed user input.
     * @throws DukeException If date format is invalid.
     */
    private static DeadlineCommand parseForDeadline(String input) throws DukeException {
        String[] parameters = input.split(DATE_SPECIFIER, 2);
        return new DeadlineCommand(parameters[0].strip(),
                parseLocalDate(parameters[1].strip()));
    }

    /**
     * Parses user input by understanding specifications to add an event task.
     *
     * @param input User input specifying event details.
     * @return Event command based on parsed user input.
     * @throws DukeException If date time format is invalid.
     */
    private static EventCommand parseForEvent(String input) throws DukeException {
        String[] parameters = input.split(DATETIME_SPECIFIER, 2);
        return new EventCommand(parameters[0].strip(),
                parseLocalDateTime(parameters[1].strip()));
    }

    /**
     * Parses user input by understanding specifications to update a task in TaskList.
     *
     * @param input Input string from user specifying update details.
     * @return Update command based on user input.
     * @throws DukeException If index string is invalid.
     */
    private static UpdateCommand parseForUpdate(String input) throws DukeException {
        String[] parameters = input.split(" ", 2);
        int index = parseIndex(parameters[0].strip());
        return new UpdateCommand(index, parameters[1].strip());
    }

    /**
     * Parses user input of date.
     *
     * @param input User input specifying details of date.
     * @return LocalDate object representing input specifications of date.
     * @throws DukeException If date format is invalid.
     */
    public static LocalDate parseLocalDate(String input) throws DukeException {
        try {
            return LocalDate.parse(input, DATETIME_DATA_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException(DATE_FORMAT_PROMPT_MESSAGE);
        }
    }

    /**
     * Returns date in message format.
     *
     * @param date Date to be formatted.
     * @return Message format of date.
     */
    public static String formatDateToMessage(LocalDate date) {
        return date.format(DATETIME_MESSAGE_FORMAT);
    }

    /**
     * Returns date in data format.
     *
     * @param date Date to be formatted.
     * @return Data format of date.
     */
    public static String formatDateToData(LocalDate date) {
        return date.format(DATETIME_DATA_FORMAT);
    }

    /**
     * Parses user input of date and time.
     *
     * @param input User input specifying details of date and time.
     * @return LocalDateTime representing input specifications of date and time.
     * @throws DukeException If date and time format is invalid.
     */
    public static LocalDateTime parseLocalDateTime(String input) throws DukeException {
        try {
            return LocalDateTime.parse(input, DATE_DATA_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException(DATETIME_FORMAT_PROMPT_MESSAGE);
        }
    }

    /**
     * Returns date and time in message format.
     *
     * @param dateTime Date and time to be formatted.
     * @return Message format of date and time.
     */
    public static String formatDateTimeToMessage(LocalDateTime dateTime) {
        return dateTime.format(DATE_MESSAGE_FORMAT);
    }

    /**
     * Returns date and time in data format.
     *
     * @param dateTime Date and time to be formatted.
     * @return Data format of date and time.
     */
    public static String formatDateTimeToData(LocalDateTime dateTime) {
        return dateTime.format(DATE_DATA_FORMAT);
    }
}
