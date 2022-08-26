package duke.commands;

import duke.exceptions.InvalidInputException;
import duke.exceptions.UnableToSaveException;
import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {

    /**
     * {@return true if the command executed is the exit command, false otherwise.}
     */
    public boolean hasEnded() {
        return this instanceof ExitCommand;
    }

    /**
     * Executes the command.
     *
     * @param taskList task list manager
     * @param ui manages Duke's interaction with the user
     * @param storage manages the storage of Duke's data
     * @throws UnableToSaveException unable to save file
     * @throws InvalidInputException invalid input given
     */
    public abstract void execute(TaskList taskList, Ui ui, StorageFile storage) throws UnableToSaveException, InvalidInputException;

}
