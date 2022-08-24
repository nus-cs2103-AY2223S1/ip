package seedu.duke.parser;

import java.util.Objects;

import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.InvalidCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.command.MarkCommand;
import seedu.duke.exception.DukeException;

/**
 * The parser class handles the parsing of a user input into a Command object that the program can handle.
 */
public class Parser {

    /**
     * Returns a Command object based on the argument fullCommand.
     *
     * @param fullCommand The user input.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] commands = fullCommand.split(" ", 2);

        if (Objects.equals(commands[0], "bye")) {
            return new ExitCommand();
        } else if (Objects.equals(commands[0], "list")) {
            return new ListCommand();
        } else if (Objects.equals(commands[0], "unmark") || Objects.equals(commands[0], "mark")) {
            try {
                return new MarkCommand(commands[0], commands[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("An index must be provided for mark or unmark commands.");
            }
        } else if (Objects.equals(commands[0], "deadline") || Objects.equals(commands[0], "event")
                || Objects.equals(commands[0], "todo")) {
            if (Objects.equals(commands[0], "deadline")) {
                try {
                    String[] taskDetails = commands[1].split(" /by ");
                    return new AddCommand(commands[0], taskDetails[0], taskDetails[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("The description or date time of a deadline cannot be empty.");
                }
            } else if (Objects.equals(commands[0], "event")) {
                try {
                    String[] taskDetails = commands[1].split(" /at ");
                    return new AddCommand(commands[0], taskDetails[0], taskDetails[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("The description or date time of an event cannot be empty.");
                }
            } else if (Objects.equals(commands[0], "todo")) {
                try {
                    return new AddCommand(commands[0], commands[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
            }
        } else if (Objects.equals(commands[0], "delete")) {
            return new DeleteCommand(Integer.parseInt(commands[1]));
        }

        return new InvalidCommand();
    }
}
