package duke.commands;

import duke.ui.Ui;
import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;

/**
 * Encapsulates the Command from users
 */
public abstract class Command {
    private boolean isExit = false;

    /**
     * Checks if the command is an Exit Command
     * @return True if it is an Exit Command
     */
    public abstract boolean isExit();

    /**
     * Executes the Command
     * @param ui Prints the messages based on the type of Command
     * @param storage Saves the modified list of tasks
     * @param taskList List of tasks
     * @throws DukeException if invalid inputs are provided
     */
    public abstract void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException;
}
