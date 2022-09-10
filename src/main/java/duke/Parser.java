package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.SnoozeCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;

/**
 * Parser to parse user input and interpret which commands user are inputting.
 */
public class Parser {
    private static Ui ui = new Ui();

    /**
     * Parses input of users and analysing which command is used.
     *
     * @param inputs User input
     * @return Command referred to the user input
     */
    public static Command parse(String... inputs) throws DukeException {
        assert(inputs != null);
        String[] commands = inputs[0].split(" ", 2);
        switch (commands[0]) {
        case "bye":
            return new ExitCommand(commands[0]);
        case "list":
            return new ListCommand(commands[0]);
        case "mark":
            return getMarkCommand(commands);
        case "unmark":
            return getUnmarkCommand(commands);
        case "delete":
            return getDeleteCommand(commands);
        case "find":
            return getFindCommand(commands);
        case "snooze":
            return getSnoozeCommand(commands);
        default:
            return new AddCommand(inputs[0]);
        }
    }

    /**
     * @param commands list of commands
     * @return new mark command
     * @throws DukeException If index is empty
     */
    private static MarkCommand getMarkCommand(String[] commands) throws DukeException {
        int index;
        if (commands.length != 2 || commands[1].length() < 1) {
            throw new DukeException("The index of a task cannot be empty.");
        }
        try {
            index = Integer.parseInt(commands[1]);
            return new MarkCommand(commands[0], index - 1);
        } catch (NumberFormatException e) {
            throw new DukeException("This index is not a number!");
        }
    }

    /**
     * @param commands list of commands
     * @return new unmark command
     * @throws DukeException If index empty
     */
    private static UnmarkCommand getUnmarkCommand(String[] commands) throws DukeException {
        int index;
        if (commands.length != 2 || commands[1].length() < 1) {
            throw new DukeException("This index of a task cannot be empty.");
        }
        index = Integer.parseInt(commands[1]);
        return new UnmarkCommand(commands[0], index - 1);
    }

    /**
     * @param commands list of commands
     * @return new delete command
     * @throws DukeException If index empty
     */
    private static DeleteCommand getDeleteCommand(String[] commands) throws DukeException {
        int index;
        if (commands.length != 2 || commands[1].length() < 1) {
            throw new DukeException("The index of a task cannot be empty.");
        }
        try {
            index = Integer.parseInt(commands[1]);
            return new DeleteCommand(commands[0], index - 1);
        } catch (NumberFormatException e) {
            throw new DukeException("This index is not a number!");
        }
    }

    /**
     * @param commands list of commands
     * @return new find command
     * @throws DukeException If keyword empty
     */
    private static FindCommand getFindCommand(String[] commands) throws DukeException {
        if (commands.length != 2 || commands[1].length() < 1) {
            throw new DukeException("The keyword of a task cannot be empty.");
        }
        return new FindCommand(commands[0], commands[1]);
    }

    /**
     * @param commands list of commands
     * @return new snooze command
     * @throws DukeException If snooze time empty
     */
    private static SnoozeCommand getSnoozeCommand(String[] commands) throws DukeException {
        int index;
        String newDate;
        if (commands.length != 2 || commands[1].length() < 1) {
            throw new DukeException("The information to snooze a task cannot be empty.");
        }
        String[] snoozeInfo = commands[1].split(" ", 2);
        try {
            index = Integer.parseInt(snoozeInfo[0]);
        } catch (NumberFormatException e) {
            throw new DukeException("This index is not a number!");
        }
        try {
            newDate = snoozeInfo[1];
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Enter a valid date!");
        }
        return new SnoozeCommand(commands[0], index - 1, newDate);
    }
}
