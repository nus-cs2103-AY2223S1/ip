package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Abstract class for a command.
 *
 * @author dexter-sim
 * @version 0.1
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param storage Storage handling the file IO.
     * @param taskList A list of tasks.
     * @param ui A ui to handle printing output.
     * @return A string from the result of execution.
     */
    public abstract String execute(Storage storage, TaskList taskList, Ui ui);

    /**
     * Returns true if the Duke program is closing, false otherwise.
     *
     * @return False.
     */
    public boolean canExit() {
        return false;
    }
}
