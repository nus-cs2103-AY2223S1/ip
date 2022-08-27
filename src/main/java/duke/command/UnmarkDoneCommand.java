package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to unmark a done task.
 */
public class UnmarkDoneCommand extends Command {
    private int index;

    /**
     * Constructor for <code>UnmarkDoneCommand</code>
     * @param index
     */
    public UnmarkDoneCommand(int index) {
        this.index = index;
    }

    /**
     * Unmark a done task with at index.
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unmarkDone(index);
        ui.showUnmarkDone(tasks.getTask(index));
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

