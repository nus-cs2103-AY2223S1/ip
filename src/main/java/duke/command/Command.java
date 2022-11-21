package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * An abstract class for Command.
 */
public abstract class Command {

    /**
     * Executes some command.
     *
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the storage
     * @throws DukeException if command cannot be executed.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Makes sure program continues and not exit.
     * @return boolean indicating not exit.
     */
    public abstract boolean isExit();
}
