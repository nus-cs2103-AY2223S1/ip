package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Mark command class to mark a task as done.
 */
public class MarkCommand extends Command {
    private int taskNo;

    /**
     * Constructor for markCommand class.
     *
     * @param taskNo The task number to be marked.
     */
    public MarkCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Marks the give task as done.
     *
     * @param tasks The task to be executed.
     */
    @Override
    public String execute(TaskList tasks) {
        tasks.markTask(taskNo);;
        try {
            Storage.save(tasks.getTasks());
        } catch (Exception e) {
            return Ui.showError(e);
        }
        return Ui.markTaskMessage(tasks.getTask(taskNo));
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
