package duke.command;

import java.io.IOException;

import duke.Ui;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents a Command
 */
public abstract class Command {
    /**
     * Executes the given command
     * @param taskList
     * @param ui
     * @param storage
     * @throws IOException
     * @throws DukeException
     * @see TaskList
     * @see Ui
     * @see Storage
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException;

    /**
     * Returns true if command is instanceof ExitCommand otherwise false
     * @param command
     * @return whether given command is instanceof ExitCommand.
     * @see ExitCommand
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
