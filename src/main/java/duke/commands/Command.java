package duke.commands;

import duke.data.TaskList;
import duke.data.exceptions.InvalidTaskException;
import duke.storage.Storage;
import duke.storage.exceptions.StorageException;
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
     * @return The response to the Command to be shown to the user.
     * @throws InvalidTaskException If invalid inputs are provided by the user.
     * @throws StorageException If there is an error saving the modified list of tasks.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage)
            throws InvalidTaskException, StorageException;
}
