package duke.parser;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.OnCommand;
import duke.commands.TodoCommand;
import duke.commands.UndoCommand;
import duke.commands.UnmarkCommand;
import duke.exception.DukeException;
import duke.parser.exceptions.InvalidCommandException;
import duke.parser.exceptions.InvalidDateException;
import duke.parser.exceptions.MissingArgumentsException;

/**
 * Identifies user input as invalid or translates them into commands that can be executed.
 */
public class InputParser {
    private static final String INVALID_USER_INPUT = "Invalid user input";
    private enum MissingDetails {
        TASK_NUMBER, DESCRIPTION, DESCRIPTION_AND_DATE, KEYWORD, DATE
    }

    /**
     * Translates the user input into the corresponding command to be executed.
     * @param input The user input.
     * @return The Command matching the user input.
     * @throws DukeException If the user input is invalid.
     */
    public static Command parse(String input) throws DukeException {
        String[] splitInputArray = input.trim().split(" ", 2);
        String commandWord = splitInputArray[0];

        switch (commandWord) {
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case MarkCommand.COMMAND_WORD:
        case UnmarkCommand.COMMAND_WORD:
        case DeleteCommand.COMMAND_WORD:
            return prepareCommandWithNumberAsArgument(splitInputArray, commandWord);
        case TodoCommand.COMMAND_WORD:
            return prepareTodo(splitInputArray);
        case DeadlineCommand.COMMAND_WORD:
        case EventCommand.COMMAND_WORD:
            return prepareDatedTask(splitInputArray, commandWord);
        case FindCommand.COMMAND_WORD:
            return prepareFind(splitInputArray);
        case OnCommand.COMMAND_WORD:
            return prepareOn(splitInputArray);
        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();
        default:
            throw new InvalidCommandException();
        }
    }

    private static Command prepareCommandWithNumberAsArgument(String[] splitInputArray, String command)
            throws MissingArgumentsException, InvalidCommandException {
        verifyInputLength(splitInputArray, MissingDetails.TASK_NUMBER);
        assert splitInputArray.length == 2 : INVALID_USER_INPUT;
        int taskNum = getTaskNumber(splitInputArray[1]);
        switch (command) {
        case "mark":
            return new MarkCommand(taskNum);
        case "unmark":
            return new UnmarkCommand(taskNum);
        case "delete":
            return new DeleteCommand(taskNum);
        default:
            throw new InvalidCommandException();
        }
    }

    private static Command prepareTodo(String[] splitInputArray) throws MissingArgumentsException {
        verifyInputLength(splitInputArray, MissingDetails.DESCRIPTION);
        assert splitInputArray.length == 2 : INVALID_USER_INPUT;
        String description = splitInputArray[1].trim();
        return new TodoCommand(description);
    }

    private static Command prepareDatedTask(String[] splitInputArray, String type)
            throws MissingArgumentsException, InvalidDateException {
        verifyInputLength(splitInputArray, MissingDetails.DESCRIPTION_AND_DATE);
        assert splitInputArray.length == 2 : INVALID_USER_INPUT;
        String details = splitInputArray[1];
        boolean isDeadline = type.equals("deadline");
        String[] splitDetailsArray = details.split(
                isDeadline ? DeadlineCommand.DATE_INDICATOR : EventCommand.DATE_INDICATOR, 2);
        verifyInputLength(splitDetailsArray, MissingDetails.DESCRIPTION_AND_DATE);
        assert splitDetailsArray.length == 2 : INVALID_USER_INPUT;
        String date = splitDetailsArray[1].trim();
        DateParser.verifyDateFormat(date);
        String description = splitDetailsArray[0].trim();
        return isDeadline ? new DeadlineCommand(description, date) : new EventCommand(description, date);
    }

    private static Command prepareFind(String[] splitInputArray) throws MissingArgumentsException {
        verifyInputLength(splitInputArray, MissingDetails.KEYWORD);
        assert splitInputArray.length == 2 : INVALID_USER_INPUT;
        String[] keywords = splitInputArray[1].split(" ");
        return new FindCommand(keywords);
    }

    private static Command prepareOn(String[] splitInputArray) throws DukeException {
        verifyInputLength(splitInputArray, MissingDetails.DATE);
        assert splitInputArray.length == 2 : INVALID_USER_INPUT;
        String date = splitInputArray[1].trim();
        DateParser.verifyDateFormat(date);
        return new OnCommand(date);
    }

    private static void verifyInputLength(String[] splitInputArray, MissingDetails details)
            throws MissingArgumentsException {
        if (splitInputArray.length < 2) {
            switch (details) {
            case TASK_NUMBER:
                throw new MissingArgumentsException("Please provide a task number!");
            case DESCRIPTION:
                throw new MissingArgumentsException("Please provide a task description!");
            case DESCRIPTION_AND_DATE:
                throw new MissingArgumentsException("Please provide a task description and date!");
            case KEYWORD:
                throw new MissingArgumentsException("Please provide keyword(s)!");
            case DATE:
                throw new MissingArgumentsException("Please provide a date!");
            default:
                throw new MissingArgumentsException("Please provide more details!");
            }
        }
    }

    private static int getTaskNumber(String taskNum) throws MissingArgumentsException {
        try {
            return Integer.parseInt(taskNum.trim());
        } catch (NumberFormatException e) {
            throw new MissingArgumentsException("Please provide a valid task number!");
        }
    }
}
