package duke;

import command.Command;
import command.ExitCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnmarkCommand;
import command.DeleteCommand;
import command.TodoCommand;
import command.EventCommand;
import command.DeadlineCommand;
import command.FindCommand;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.Integer.parseInt;

/**
 * This class deals with making sense of the user command
 */
public class Parser {

    /**
     * Returns the specific command that will execute the specific task according to
     * the user input
     * @@author shaniceng-reused
     * Reused from https://github.com/tinenhao/ip/blob/master/src/main/java/Duke/Parser.java
     * with minor modifications
     *
     * @param command
     * @return Command that program should execute according to
     *         the user input
     * @throws DukeException to show the error of an invalid input or command
     *         given by the user input
     */
    public static Command parse(String command) throws DukeException {
        String[] input = command.split(" ", 2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
        String firstCommand = input[0];
        switch (firstCommand) {
        case "bye":
            assert input.length == 1: Constants.INVALID_BYE_INPUT;
            return new ExitCommand();
        case "list":
            assert input.length == 1: Constants.INVALID_LIST_INPUT;
            return new ListCommand();
        case "mark":
            assert input.length == 2: Constants.INVALID_MARK_INPUT;
            checkInputError(input);
            checkIntInputError(input);
            return new MarkCommand(parseInt(input[1]));
        case "unmark":
            assert input.length == 2: Constants.INVALID_UNMARK_INPUT;
            checkInputError(input);
            checkIntInputError(input);
            return new UnmarkCommand(parseInt(input[1]));
        case "delete":
            assert input.length == 2: Constants.INVALID_DELETE_INPUT;
            checkInputError(input);
            checkIntInputError(input);
            return new DeleteCommand(parseInt(input[1]));
        case "todo":
            assert input.length >= 2: Constants.INVALID_TODO_INPUT;
            checkInputError(input);
            return new TodoCommand(input[1]);
        case "event":
            assert input.length >= 5: Constants.INVALID_EVENT_INPUT;
            checkInputError(input);
            String[] input2 = input[1].split(" /at ");
            checkInputError(input2);
            return new EventCommand(input2[0], LocalDateTime.parse(input2[1], formatter));
        case "deadline":
            assert input.length >= 5: Constants.INVALID_DEADLINE_INPUT;
            checkInputError(input);
            String[] input3 = input[1].split(" /by ");
            checkInputError(input3);
            return new DeadlineCommand(input3[0], LocalDateTime.parse(input3[1], formatter));
        case "find":
            assert input.length == 2: Constants.INVALID_FIND_INPUT;
            checkInputError(input);
            return new FindCommand(input[1]);
        default:
            throw new DukeException(Constants.INVALID_COMMAND);
        }
    }

    private static void checkInputError(String[] arr) throws DukeException {
        if (arr.length == 1) {
            throw new DukeException(Constants.INVALID_INPUT);
        }
    }

    private static void checkIntInputError(String[] arr) throws DukeException {
        if (!isNumber(arr[1])) {
            throw new DukeException(Constants.INVALID_INPUT);
        }
    }

    private static boolean isNumber(String str) {
        if (str == null) {
            return false;
        }
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
