package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to list out all the current tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Lists out all the current tasks in the task list.
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }

    /**
     * Check if the command exit duke.
     * @return
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
