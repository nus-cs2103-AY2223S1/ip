package duke.parser;

import duke.data.exception.DukeException;
import duke.common.Message;
import duke.commands.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final String DATE_INPUT_FORMAT = "yyyy-MM-dd";

    private enum MissingDetails {
        TASK_NUMBER, DESCRIPTION, DESCRIPTION_AND_DATE
    }

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
        default:
            throw new DukeException(Message.INVALID_COMMAND);
        }
    }

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_INPUT_FORMAT));
    }

    private static void verifyInput(String[] splitInputArray, MissingDetails details) throws DukeException {
        if (splitInputArray.length < 2) {
            switch (details) {
            case TASK_NUMBER:
                throw new DukeException(Message.PROVIDE_TASK_NUMBER);
            case DESCRIPTION:
                throw new DukeException(Message.PROVIDE_DESCRIPTION);
            case DESCRIPTION_AND_DATE:
                throw new DukeException(Message.PROVIDE_DESCRIPTION_AND_DATE);
            default:
                throw new DukeException(Message.PROVIDE_MORE_DETAILS);
            }
        }
    }

    private static int getTaskNumber(String taskNum) throws DukeException {
        try {
            return Integer.parseInt(taskNum);
        } catch (NumberFormatException e) {
            throw new DukeException(Message.PROVIDE_TASK_NUMBER);
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
            throw new DukeException(String.format(Message.INVALID_DATE_FORMAT, DATE_INPUT_FORMAT));
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
}
