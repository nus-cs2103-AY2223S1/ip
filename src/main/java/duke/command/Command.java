package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command.
 */
public abstract class Command {

    /**
     * Returns exit status of programme.
     *
     * @return exit.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes command.
     *
     * @param tasks List of task.
     * @param ui User interface of programme.
     * @param storage Storage of programme.
     * @return duke's response.
     * @throws DukeException based on command executed.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
