package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.BatchDescDeleteCommand;
import duke.command.BatchTypeDeleteCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ToDoCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeEmptyDeadlineException;
import duke.exception.DukeEmptyEventException;
import duke.exception.DukeEmptyToDoException;
import duke.exception.DukeException;
import duke.exception.DukeInvalidCommandException;
import duke.exception.DukeInvalidDeadlineSeparatorException;
import duke.exception.DukeInvalidEventSeparatorException;
import duke.exception.DukeInvalidTimeFormatException;
import duke.exception.DukeInvalidTypeException;
import duke.exception.DukeNoIndexException;
import duke.exception.DukeNoKeywordException;

/**
 * The Parser class that parses user commands.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class Parser {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final String SEPARATOR_EVENT = " /at ";
    private static final String SEPARATOR_DEADLINE = " /by ";

    /**
     * Executes user inputs.
     *
     * @param input user Input to be parsed.
     */
    public static Command parse(String input) throws DukeException {

        String[] data = formatInput(input);
        String type = data[0];

        switch (type) {
        case "list":
            return parseListCommand(data);
            // No need for break since it is unreachable
        case "mark":
            return parseMarkCommand(data);
            // No need for break since it is unreachable
        case "unmark":
            return parseUnmarkCommand(data);
            // No need for break since it is unreachable
        case "delete":
            return parseDeleteCommand(data);
            // No need for break since it is unreachable
        case "batchtypedelete":
            return parseBatchTypeDeleteCommand(data);
            // No need for break since it is unreachable
        case "batchdescdelete":
            return parseBatchDescDeleteCommand(data);
            // No need for break since it is unreachable
        case "todo":
            return parseToDoCommand(data);
            // No need for break since it is unreachable
        case "deadline":
            return parseDeadlineCommand(data);
            // No need for break since it is unreachable
        case "event":
            return parseEventCommand(data);
            // No need for break since it is unreachable
        case "find":
            return parseFindCommand(data);
            // No need for break since it is unreachable
        case "help":
            return parseHelpCommand(data);
            // No need for break since it is unreachable
        case "bye":
            return parseExitCommand(data);
            // No need for break since it is unreachable
        default:
            throw new DukeInvalidCommandException();
        }
    }

    private static BatchDescDeleteCommand parseBatchDescDeleteCommand(String[] data) throws DukeException {
        String description = data[1];

        if (description.isEmpty()) {
            throw new DukeNoKeywordException();
        }

        return new BatchDescDeleteCommand(description);
    }

    private static BatchTypeDeleteCommand parseBatchTypeDeleteCommand(String[] data) throws DukeException {
        String type = data[1];

        if (type.isEmpty()) {
            throw new DukeNoKeywordException();
        } else if (!isTaskType(type)) {
            throw new DukeInvalidTypeException();
        } else {
            return new BatchTypeDeleteCommand(type);
        }
    }

    private static ExitCommand parseExitCommand(String[] data) {
        return new ExitCommand();
    }

    private static HelpCommand parseHelpCommand(String[] data) {
        return new HelpCommand();
    }

    private static ListCommand parseListCommand(String[] data) {
        return new ListCommand();
    }

    private static MarkCommand parseMarkCommand(String[] data) throws DukeNoIndexException {
        String description = data[1];

        if (description.isEmpty()) {
            throw new DukeNoIndexException();
        }

        int index = Integer.parseInt(description) - 1;
        return new MarkCommand(index);
    }

    private static UnmarkCommand parseUnmarkCommand(String[] data) throws DukeNoIndexException {
        String description = data[1];

        if (description.isEmpty()) {
            throw new DukeNoIndexException();
        }
        int index = Integer.parseInt(description) - 1;
        return new UnmarkCommand(index);
    }

    private static DeleteCommand parseDeleteCommand(String[] data) throws DukeNoIndexException {
        String description = data[1];

        if (description.isEmpty()) {
            throw new DukeNoIndexException();
        }

        int index = Integer.parseInt(description) - 1;
        return new DeleteCommand(index);
    }

    private static ToDoCommand parseToDoCommand(String[] data) throws DukeEmptyToDoException {
        String description = data[1];

        if (description.isEmpty()) {
            throw new DukeEmptyToDoException();
        }
        return new ToDoCommand(description);
    }

    private static DeadlineCommand parseDeadlineCommand(String[] data) throws DukeException {
        String description = data[1];
        String dateTime = data[2];

        if (description.isEmpty()) {
            throw new DukeEmptyDeadlineException();
        }

        try {
            LocalDateTime by = LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
            return new DeadlineCommand(description, by);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidTimeFormatException();
        }
    }

    private static EventCommand parseEventCommand(String[] data) throws DukeException {
        String description = data[1];
        String dateTime = data[2];

        if (description.isEmpty()) {
            throw new DukeEmptyEventException();
        }

        try {
            LocalDateTime at = LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
            return new EventCommand(description, at);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidTimeFormatException();
        }
    }

    private static FindCommand parseFindCommand(String[] data) throws DukeNoKeywordException {
        String description = data[1];

        if (description.isEmpty()) {
            throw new DukeNoKeywordException();
        }
        return new FindCommand(description);
    }

    /**
     * Formats data from user input for parsing
     *
     * @param input user Input to be parsed.
     * @return A String array of size 3, where 1 = type of Task, 2 = description, 3 = dateTime
     */
    private static String[] formatInput(String input) throws DukeException {
        String type;
        String description;
        String dateTime;

        boolean haveDescription = input.contains(" ");

        if (!haveDescription) {
            type = input.toLowerCase();
            description = "";
            dateTime = "";
        } else {
            String details;
            String[] splitDetails;

            int index = input.indexOf(' ');
            type = input.substring(0, index).toLowerCase();
            details = input.substring(index + 1);

            if (isValidTodo(type, details)) {
                description = details;
                dateTime = "";
            } else if (isValidEvent(type, details)) {
                splitDetails = details.split(SEPARATOR_EVENT);
                description = splitDetails[0];
                dateTime = splitDetails[1];
            } else if (isValidDeadline(type, details)) {
                splitDetails = details.split(SEPARATOR_DEADLINE);
                description = splitDetails[0];
                dateTime = splitDetails[1];
            } else {
                // Else does not specify an error as error is dealt in parse function.
                description = details;
                dateTime = "";
            }
        }
        assert isValidFormat(type, description, dateTime) : "Format Failure: Invalid Format";
        String[] data = new String[]{type, description, dateTime};
        return data;
    }

    /**
     * Returns Validity of Formatted Data.
     *
     * @param type The formatted Type.
     * @param description The formatted Description.
     * @param dateTime The formatted dateTime.
     * @return True is valid Formatted Data, false otherwise.
     */
    private static boolean isValidFormat(String type, String description, String dateTime) {
        return isValidType(type) && isValidDescription(description) && isValidDateTime(dateTime);
    }

    /**
     * Returns validity of the dateTime.
     *
     * @param dateTime The specified dateTime.
     * @return true if valid dateTime, false otherwise.
     */
    private static boolean isValidDateTime(String dateTime) {
        return dateTime != null;
    }

    /**
     * Returns validity of the description.
     *
     * @param description The specified description.
     * @return true if valid description, false otherwise.
     */
    private static boolean isValidDescription(String description) {
        return description != null;
    }

    /**
     * Returns validity of the type.
     *
     * @param type The specified type.
     * @return true if valid type, false otherwise.
     */
    private static boolean isValidType(String type) {
        return type != null;
    }

    /**
     * Returns true if input provides a valid Todo input, false otherwise.
     *
     * @param type The type specified by user input.
     * @param details The details specified by user input.
     * @return true is valid ToDo input, false otherwise.
     * @throws DukeException Exception thrown when input lacks description.
     */
    private static boolean isValidTodo(String type, String details) throws DukeException {
        boolean isTodo = type.equals("todo");
        if (isTodo && details.isEmpty()) {
            throw new DukeEmptyToDoException();
        }

        return isTodo;
    }

    /**
     * Returns true if input provides a valid Event input, false otherwise.
     *
     * @param type The type specified by user input.
     * @param details The details specified by user input.
     * @return true is valid Event input, false otherwise.
     * @throws DukeException Exception thrown when input lacks description.
     */
    private static boolean isValidEvent(String type, String details) throws DukeException {
        boolean isEvent = type.equals("event");
        boolean hasEventSeparator = details.contains(SEPARATOR_EVENT);
        if (isEvent && details.isEmpty()) {
            throw new DukeEmptyEventException();
        } else if (isEvent && !hasEventSeparator) {
            throw new DukeInvalidEventSeparatorException();
        }

        return isEvent && hasEventSeparator;
    }

    /**
     * Returns true if input provides a valid Deadline input, false otherwise.
     *
     * @param type The type specified by user input.
     * @param details The details specified by user input.
     * @return true is valid Deadline input, false otherwise.
     * @throws DukeException Exception thrown when input lacks description.
     */
    private static boolean isValidDeadline(String type, String details) throws DukeException {
        boolean isDeadline = type.equals("deadline");
        boolean hasDeadlineSeparator = details.contains(SEPARATOR_DEADLINE);
        if (isDeadline && details.isEmpty()) {
            throw new DukeEmptyDeadlineException();
        } else if (isDeadline && !hasDeadlineSeparator) {
            throw new DukeInvalidDeadlineSeparatorException();
        }

        return isDeadline && hasDeadlineSeparator;
    }


    private static boolean isTaskType(String type) {
        return isTodo(type) || isDeadline(type) || isEvent(type);
    }

    private static boolean isEvent(String type) {
        return type.equals("event");
    }

    private static boolean isDeadline(String type) {
        return type.equals("deadline");
    }

    private static boolean isTodo(String type) {
        return type.equals("todo");
    }


}
