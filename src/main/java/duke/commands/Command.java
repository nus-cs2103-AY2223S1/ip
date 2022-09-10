package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Represents an abstraction superclass to be inherited by other type of command.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param taskList The list of tasks in Duke.
     * @param ui The TextUi class used to print message in Duke.
     * @param storage The storage used to save the tasks in the local file.
     */
    public abstract void execute(TaskList taskList, TextUi ui, Storage storage);

    /**
     * Indicates whether Duke should terminate after the command is executed.
     *
     * @return Boolean to indicate whether Duke should terminate.
     */
    public abstract boolean isExit();
}
