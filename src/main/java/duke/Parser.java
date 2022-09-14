package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.*;

/**
 * {@code Parser} parse the user input and perform input sanitization to ensure no invalid input
 */
public class Parser {

    /**
     * The main function to parse user input
     * @param s The user input
     * @return a {@code Command} after performing input sanitization
     * @throws DukeException if user enters an invalid input
     */
    public static Command parse(String s) throws DukeException {
        String[] str = s.split(" ", 2);

        switch (str[0]) {
        case "bye":
            return new ExitCommand();
        case "help":
            return new HelpCommand();
        case "list":
            return new ListCommand();
        case "todo":
            return parseTodo(str);
        case "deadline":
            return parseDeadline(str);
        case "event":
            return parseEvent(str);
        case "mark":
            return parseMark(str);
        case "unmark":
            return parseUnmark(str);
        case "delete":
            return parseDelete(str);
        case "find":
            return parseFind(str);
        default:
            return new InvalidCommand();
        }
    }

    private static TodoCommand parseTodo(String[] str) throws DukeException {
        try {
            if (str[1].length() == 0) {
                throw new DukeException("The description of todo cannot be empty.");
            }
            return new TodoCommand(str[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The description of todo cannot be empty.");
        }
    }

    private static DeadlineCommand parseDeadline(String[] str) throws DukeException {
        if (str.length != 2) {
            throw new DukeException("The description of deadline cannot be empty.");
        }
        String[] splitDescription = str[1].split(" /by ", 2);
        if (splitDescription.length != 2) {
            throw new DukeException("Command are missing either description or date.");
        }
        try {
            LocalDate date = LocalDate.parse(splitDescription[1]);
            return new DeadlineCommand(splitDescription[0], date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please use yyyy-mm-dd as the date format.");
        }
    }

    private static EventCommand parseEvent(String[] str) throws DukeException {
        if (str.length != 2) {
            throw new DukeException("The description of event cannot be empty.");
        }
        String[] splitDescription = str[1].split(" /at ", 2);
        if (splitDescription.length != 2) {
            throw new DukeException("Command are missing either description or remark.");
        }
        return new EventCommand(splitDescription[0], splitDescription[1]);
    }

    private static MarkCommand parseMark(String[] str) throws DukeException {
        try {
            int idx = Integer.parseInt(str[1]);
            try {
                return new MarkCommand(idx - 1);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid index.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Argument of mark cannot be empty.");
        } catch (NumberFormatException e) {
            throw new DukeException("The argument you've input is not an integer");
        }
    }

    private static UnmarkCommand parseUnmark(String[] str) throws DukeException {
        try {
            int idx = Integer.parseInt(str[1]);
            try {
                return new UnmarkCommand(idx - 1);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid index.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Argument of unmark cannot be empty.");
        } catch (NumberFormatException e) {
            throw new DukeException("The argument you've input is not an integer");
        }
    }

    private static DeleteCommand parseDelete(String[] str) throws DukeException {
        try {
            int idx = Integer.parseInt(str[1]);
            try {
                return new DeleteCommand(idx - 1);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid index.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Argument of delete cannot be empty.");
        } catch (NumberFormatException e) {
            throw new DukeException("The argument you've input is not an integer");
        }
    }

    private static FindCommand parseFind(String[] str) throws DukeException {
        try {
            if (str[1].length() == 0) {
                throw new DukeException("The description of find cannot be empty.");
            }
            return new FindCommand(str[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The description of find cannot be empty.");
        }
    }
}
