package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Abstract class Command that represents commands on tasks.
 *
 * @author Elgin
 */
public abstract class Command {
    /**
     * Abstract method that executes the command.
     *
     * @param tasks All tasks present in Duke.
     * @param ui The UI controller that handles interaction between user and Duke.
     * @param storage Storage that stores all tasks on Disk.
     * @return Duke's message to the user.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}
