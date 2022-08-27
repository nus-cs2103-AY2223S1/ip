package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

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
}
