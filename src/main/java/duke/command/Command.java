package duke.command;

import duke.exception.DukeException;
import duke.exception.UnknownCommandException;
import duke.gui.Ui;
import duke.task.TaskList;
import duke.util.Storage;

/**
 * Represents the command that is executed when the user inputs a command.
 *
 * @author njxue
 * @version v0.1
 */
public abstract class Command {
    /**
     * Executes the corresponding command.
     *
     * @param tasks TaskList containing the list of tasks.
     * @param ui Ui object which interacts with the user.
     * @param storage Storage object which loads and saves tasks.
     * @throws DukeException If an exception occurs.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if the command is an application terminating command.
     *
     * @return True, if the command is an application terminating command, otherwise false.
     */
    public abstract boolean isExit();

    /**
     * Returns the expected format of the command.
     *
     * @param commandString Name of the command.
     * @return Expected format of the command.
     * @throws UnknownCommandException If the name of the command does not match with any of the existing known
     *              commands.
     */
    public static String getCommandFormat(String commandString) throws UnknownCommandException {
        switch (commandString) {
        case "todo":
            return TodoCommand.getFormat();
        case "deadline":
            return DeadlineCommand.getFormat();
        case "event":
            return EventCommand.getFormat();
        case "find":
            return FindCommand.getFormat();
        case "mark":
            return MarkCommand.getFormat();
        case "unmark":
            return UnmarkCommand.getFormat();
        case "delete":
            return DeleteCommand.getFormat();
        case "list":
            return ListCommand.getFormat();
        case "bye":
            return ByeCommand.getFormat();
        case "sort":
            return SortCommand.getFormat();
        default:
            throw new UnknownCommandException(commandString);
        }
    }
}
