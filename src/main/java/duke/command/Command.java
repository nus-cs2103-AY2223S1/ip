package duke.command;

import duke.list.TaskList;
import duke.storage.ListLoader;
import duke.ui.Ui;

/**
 * Represents an input command to the Duke application.
 *
 * @author WR3nd3
 */
public abstract class Command {

    /**
     * Executes relevant procedures to this command to the task list, UI, and list loader.
     *
     * @param tasks duke.list.TaskList containing the tasks on the list.
     * @param ui duke.ui.Ui that interacts with the input of users and output from duke.Duke.
     * @param storage duke.storage.ListLoader that updates duke.list.TaskList information on a separate file.
     */
    public abstract void execute(TaskList tasks, Ui ui, ListLoader storage);

    /**
     * Returns whether the command is an exit command.
     *
     * @return Boolean on whether the command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
