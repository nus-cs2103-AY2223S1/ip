package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * An abstract class Command.
 */
public abstract class Command {

    /**
     * An abstract method that is implemented in its
     * children classes returns a boolean depending on whether
     * it is the last command from the user.
     *
     * @return boolean.
     */
    public abstract boolean isExit();

    /**
     * An abstract method that is implemented in its
     * children classes that executes the command from the user
     * differently depending on what command the user gives.
     *
     * @param taskList The ArrayList that contains all the tasks.
     * @param ui The Ui that interacts with the next command from the user.
     * @param storage The Storage used to store the tasks.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
