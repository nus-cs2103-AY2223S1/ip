package duke.commands;

import duke.gui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents an abstraction superclass to be inherited by other type of command.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param taskList The list of tasks in Duke.
     * @param ui The ui class to get the command response.
     * @param storage The storage used to save the tasks in the local file.
     * @return The string response after executing the command.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Indicates whether Duke should terminate after the command is executed.
     *
     * @return Boolean to indicate whether Duke should terminate.
     */
    public abstract boolean isExit();
}
