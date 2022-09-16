package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Mark command class to mark a task as undone.
 */
public class UnMarkCommand extends Command {
    private int taskNo;

    /**
     * Constructor for unMarkCommand class.
     *
     * @param taskNo The task number to be marked.
     */
    public UnMarkCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Marks the give task as undone.
     *
     * @param tasks The task to be executed.
     */
    @Override
    public String execute(TaskList tasks) {
        tasks.unMarkTask(taskNo);
        try {
            Storage.save(tasks);
        } catch (Exception e) {
            return Ui.showError(e);
        }
        return Ui.unMarkTaskMessage(tasks.getTask(taskNo));
    }
}

