package stashy.commands;

import stashy.data.exception.DeveloperException;
import stashy.data.exception.StashyException;
import stashy.data.task.TaskList;
import stashy.storage.Storage;
import stashy.ui.Ui;

/**
 * Acts as the base for the specific Command objects,
 * such as Add*Command and DeleteCommand.
 */
public abstract class Command {
    public abstract boolean isExit();

    /**
     * Default execute method.
     *
     * @param tasks The list of tasks
     * @param ui The UI of this application
     * @param storage The storage used for this application
     * @throws StashyException If any exception is caught
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StashyException {
        ui.showIndented(executeString(tasks, ui, storage));
    }

    /**
     * Stringtified execute method. Not applicable here.
     *
     * @param tasks The list of tasks
     * @param ui The UI of this application
     * @param storage The storage used for this application
     * @return The stringtified UI output
     * @throws DeveloperException Since this method should be overriden
     */
    public String executeString(TaskList tasks, Ui ui, Storage storage) throws StashyException {
        // This statement should not be reached on the final product
        throw new DeveloperException("Implement this method in the child class!");
    }
}
