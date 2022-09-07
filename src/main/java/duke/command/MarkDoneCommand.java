package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to mark a task as done.
 */
public class MarkDoneCommand extends Command {
    private int index;

    /**
     * Constructor for <code>MarkDoneCommand</code>
     * @param index
     */
    public MarkDoneCommand(int index) {
        this.index = index;
    }

    /**
     * Mark a task with at index as done.
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markDone(index);
        ui.showMarkDone(tasks.getTask(index));
    }

    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) {
        tasks.markDone(index);
        return "Nice! I have marked this task as done\n"
                + tasks.getTask(index).toString();
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

