package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {
    /**
     * Checks if the command executed exits the program or not.
     *
     * @return Whether the command executed exits the program or not
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command and return the results.
     *
     * @param taskList The TaskList to use.
     * @param ui The Ui to use.
     * @param storage The storage to use.
     * @throws DukeException If the command executed encounters an issue with the parameters.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
