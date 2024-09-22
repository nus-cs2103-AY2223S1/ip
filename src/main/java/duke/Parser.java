package duke;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.NoteCommand;
import duke.exception.DukeException;
import duke.exception.DukeInvalidCommandException;
import duke.exception.DukeInvalidTaskException;

import java.util.Locale;
import java.util.Objects;

/**
 * Makes sense of the user commands.
 *
 * @author Lim Ai Lin
 */
public class Parser {
    private enum Commands {
        LIST,
        MARK,
        UNMARK,
        DEADLINE,
        TODO,
        EVENT,
        DELETE,
        FIND,
        NOTE
    }

    /**
     * Parses the input by the user.
     *
     * @param command The input String to be parsed.
     * @return The Command the user wishes to execute.
     * @throws DukeException
     *          Thrown if user inputs an unknown command.
     */
    public static Command parse(String command) throws DukeException {
        String[] str = command.split("\\s", 2);
        Commands myTask;
        try {
            myTask = Commands.valueOf(str[0].toUpperCase(Locale.ROOT));
        } catch (Exception e) {
            throw new DukeInvalidCommandException();
        }
        assert !Objects.equals(str[0], "");
        assert myTask == Commands.LIST || myTask == Commands.MARK
                || myTask == Commands.UNMARK || myTask == Commands.DEADLINE || myTask == Commands.TODO
                || myTask == Commands.EVENT || myTask == Commands.DELETE || myTask == Commands.FIND
                || myTask == Commands.NOTE;
        return parseCommand(myTask, str);
    }

    private static Command parseCommand(Commands myTask, String[] str) throws DukeException {
        switch (myTask) {
        case LIST:
            return new ListCommand();
        case MARK:
            return new MarkCommand(str, true);
        case UNMARK:
            return new MarkCommand(str, false);
        case DEADLINE:
            return new AddCommand(str, 0);
        case TODO:
            return new AddCommand(str, 1);
        case EVENT:
            return new AddCommand(str, 2);
        case DELETE:
            return new DeleteCommand(str);
        case FIND:
            return new FindCommand(str);
        case NOTE:
            return new NoteCommand(str);
        default:
            throw new DukeInvalidTaskException();
        }
    }
}
