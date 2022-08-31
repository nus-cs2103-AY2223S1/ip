package duke.parser;

import java.time.LocalDate;
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
import duke.common.Messages;
import duke.data.exception.DukeException;

/**
 * Identifies user input as invalid or translates them into commands that can be executed.
 */
public class Parser {
    private static final String DATE_INPUT_FORMAT = "yyyy-MM-dd";

    private enum MissingDetails {
        TASK_NUMBER, DESCRIPTION, DESCRIPTION_AND_DATE, KEYWORD
    }

    /**
     * Translates the user input into the corresponding command to be executed.
     * @param input The user input.
     * @return The Command matching the user input.
     * @throws DukeException If the user input is invalid.
     */
    public static Command parse(String input) throws DukeException {
        String[] splitInputArray = input.split(" ", 2);
        String commandWord = splitInputArray[0];

        switch (commandWord) {
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case MarkCommand.COMMAND_WORD:
            return prepareMark(splitInputArray);
        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmark(splitInputArray);
        case TodoCommand.COMMAND_WORD:
            return prepareTodo(splitInputArray);
        case DeadlineCommand.COMMAND_WORD:
        case EventCommand.COMMAND_WORD:
            return prepareDatedTask(splitInputArray, commandWord);
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(splitInputArray);
        case FindCommand.COMMAND_WORD:
            return prepareFind(splitInputArray);
        default:
            throw new DukeException(Messages.MESSAGE_INVALID_COMMAND);
        }
    }

    /**
     * Returns a LocalDate object that represents the given date.
     * @param date The date to be parsed into a LocalDate object.
     * @return The LocalDate object representing the given date.
     */
    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_INPUT_FORMAT));
    }

    private static void verifyInput(String[] splitInputArray, MissingDetails details) throws DukeException {
        if (splitInputArray.length < 2) {
            switch (details) {
            case TASK_NUMBER:
                throw new DukeException(Messages.MESSAGE_PROVIDE_TASK_NUMBER);
            case DESCRIPTION:
                throw new DukeException(Messages.MESSAGE_PROVIDE_DESCRIPTION);
            case DESCRIPTION_AND_DATE:
                throw new DukeException(Messages.MESSAGE_PROVIDE_DESCRIPTION_AND_DATE);
            case KEYWORD:
                throw new DukeException(Messages.MESSAGE_PROVIDE_KEYWORD);
            default:
                throw new DukeException(Messages.MESSAGE_PROVIDE_MORE_DETAILS);
            }
        }
    }

    private static int getTaskNumber(String taskNum) throws DukeException {
        try {
            return Integer.parseInt(taskNum);
        } catch (NumberFormatException e) {
            throw new DukeException(Messages.MESSAGE_PROVIDE_TASK_NUMBER);
        }
    }

    private static Command prepareMark(String[] splitInputArray) throws DukeException {
        verifyInput(splitInputArray, MissingDetails.TASK_NUMBER);
        int taskNum = getTaskNumber(splitInputArray[1]);
        return new MarkCommand(taskNum);
    }

    private static Command prepareUnmark(String[] splitInputArray) throws DukeException {
        verifyInput(splitInputArray, MissingDetails.TASK_NUMBER);
        int taskNum = getTaskNumber(splitInputArray[1]);
        return new UnmarkCommand(taskNum);
    }

    private static Command prepareTodo(String[] splitInputArray) throws DukeException {
        verifyInput(splitInputArray, MissingDetails.DESCRIPTION);
        String description = splitInputArray[1];
        return new TodoCommand(description);
    }

    private static void verifyDateFormat(String date) throws DukeException {
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_INPUT_FORMAT));
        } catch (DateTimeParseException e) {
            throw new DukeException(String.format(Messages.MESSAGE_INVALID_DATE_FORMAT, DATE_INPUT_FORMAT));
        }
    }

    private static Command prepareDatedTask(String[] splitInputArray, String type) throws DukeException {
        verifyInput(splitInputArray, MissingDetails.DESCRIPTION_AND_DATE);
        String details = splitInputArray[1];
        boolean isDeadline = type.equals("deadline");
        String[] splitDetailsArray = details.split(isDeadline ? " /by " : " /at ", 2);
        verifyInput(splitDetailsArray, MissingDetails.DESCRIPTION_AND_DATE);
        String date = splitDetailsArray[1];
        verifyDateFormat(date);
        String description = splitDetailsArray[0];
        return isDeadline ? new DeadlineCommand(description, date) : new EventCommand(description, date);
    }

    private static Command prepareDelete(String[] splitInputArray) throws DukeException {
        verifyInput(splitInputArray, MissingDetails.TASK_NUMBER);
        int taskNum = getTaskNumber(splitInputArray[1]);
        return new DeleteCommand(taskNum);
    }

    private static Command prepareFind(String[] splitInputArray) throws DukeException {
        verifyInput(splitInputArray, MissingDetails.KEYWORD);
        String[] keywords = splitInputArray[1].split(" ");
        return new FindCommand(keywords);
    }
}
