package duke.command;

import duke.data.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * MarkCommand class
 */
public class MarkCommand extends Command {

    private final int taskIndex;
    private final String action;

    /**
     * Initialise mark command
     *
     * @param action    Mark or Unmark
     * @param taskIndex Index of task to target
     */
    public MarkCommand(String action, int taskIndex) {
        this.taskIndex = taskIndex;
        this.action = action;
    }

    /**
     * Mark task in task list and update disk storage
     *
     * @param storage Disk storage
     * @param tasks   Task list
     * @param ui      Ui to display to users
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        try {
            String res = tasks.mark(action, taskIndex);
            storage.store(tasks);
            return res;
        } catch (IndexOutOfBoundsException e) {
            return ("Task index not found");
        }

    }
}
