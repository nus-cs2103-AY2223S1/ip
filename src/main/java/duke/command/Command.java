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
     * Method that checks if the Command is an ExitCommand.
     *
     * @return False for all commands other than commands that exit the task manager.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Method that executes the Command.
     *
     * @param tasks   The TaskList instance for the task manager.
     * @param ui      The Ui instance for the task manager.
     * @param storage The Storage instance for the task manager.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
