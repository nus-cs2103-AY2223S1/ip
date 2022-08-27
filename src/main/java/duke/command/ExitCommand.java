package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to exit duke.
 */
public class ExitCommand extends Command {
    /**
     * Stores the current tasks and close duke.
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.storeTasks(tasks);
        ui.showBye();
    }

    /**
     * Check if the command exit duke.
     * @return
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
