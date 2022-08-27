package duke;
import duke.command.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
    public static Command parse( String s) throws DukeException {
        String[] str = s.split(" ", 2);
        String[] splitDescription;

        switch (str[0]) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "todo":
                try {
                    if (str[1].length() == 0) {
                        throw new DukeException("The description of todo cannot be empty.");
                    }
                    return new TodoCommand(str[1]);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("The description of todo cannot be empty.");
                }
            case "deadline":
                if (str.length != 2) {
                    throw new DukeException("The description of deadline cannot be empty.");
                }
                splitDescription = str[1].split(" /by ", 2);
                if (splitDescription.length != 2) {
                    throw new DukeException("Command are missing either description or date.");
                }
                try {
                    LocalDate date = LocalDate.parse(splitDescription[1]);
                    return new DeadlineCommand(splitDescription[0], date);
                } catch (DateTimeParseException e) {
                    throw new DukeException("Please use yyyy-mm-dd as the date format.");
                }
            case "event":
                if (str.length != 2) {
                    throw new DukeException("The description of event cannot be empty.");
                }
                splitDescription = str[1].split(" /at ", 2);
                if (splitDescription.length != 2) {
                    throw new DukeException("Command are missing either description or remark.");
                }
                return new EventCommand(splitDescription[0], splitDescription[1]);
            case "mark":
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
            case "unmark":
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
            case "delete":
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
            case "find":
                try {
                    if (str[1].length() == 0) {
                        throw new DukeException("The description of find cannot be empty.");
                    }
                    return new FindCommand(str[1]);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("The description of find cannot be empty.");
                }
            default:
                return new InvalidCommand();
        }
    }
}
