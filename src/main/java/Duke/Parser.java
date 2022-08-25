package Duke;

import Command.Command;
import Command.ExitCommand;
import Command.ListCommand;
import Command.MarkCommand;
import Command.UnmarkCommand;
import Command.DeleteCommand;
import Command.TodoCommand;
import Command.EventCommand;
import Command.DeadlineCommand;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static java.lang.Integer.parseInt;

/**
 * This class deals with making sense of the user command
 */
public class Parser {

    public static Command parse(String command) throws DukeException {
        String[] input = command.split(" ", 2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM uuuu");
        switch (input[0].toLowerCase(Locale.ROOT)) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                checkInputError(input);
                checkIntInputError(input);
                return new MarkCommand(parseInt(input[1]));
            case "unmark":
                checkInputError(input);
                checkIntInputError(input);
                return new UnmarkCommand(parseInt(input[1]));
            case "delete":
                checkInputError(input);
                checkIntInputError(input);
                return new DeleteCommand(parseInt(input[1]));
            case "todo":
                checkInputError(input);
                return new TodoCommand(input[1]);
            case "event":
                checkInputError(input);
                String[] input2 = input[1].split(" /at ");
                checkInputError(input2);
                return new EventCommand(input2[0], LocalDate.parse(input2[1], formatter));
            case "deadline":
                checkInputError(input);
                String[] input3 = input[1].split(" /by ");
                checkInputError(input3);
                return new DeadlineCommand(input3[0], LocalDate.parse(input3[1], formatter));
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
        if (!isNumber(arr[1]))
            throw new DukeException(Constants.INVALID_INPUT);
    }

    private static boolean isNumber(String str) {
        if (str == null) return false;
        try {
            int x = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
