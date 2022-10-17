package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * The Command class represents instructions based on the user's input.
 *
 * @author Edric Yeo
 */
public abstract class Command {

    /**
     * Returns true if the Command is an ExitCommand.
     *
     * @return False for all commands other than commands that exit the task manager.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns a message to indicate that the Command has been executed.
     *
     * @param tasks   The TaskList instance for the task manager.
     * @param ui      The Ui instance for the task manager.
     * @param storage The Storage instance for the task manager.
     * @return A message to indicate that the Command has been executed.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
