package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a command according to the user input provided.
 */
public abstract class Command {
    /**
     * Executes the Command.
     * @param taskList List of tasks.
     * @param ui Shows messages to the user based on the Command executed.
     * @param storage Saves the modified list of tasks.
     * @throws DukeException If invalid inputs are provided or if there is an error saving the
     *         modified list of tasks.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
