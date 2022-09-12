package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * CloneCommand class to clone a task based on existing item.
 */
public class CloneCommand extends Command {
    private int taskNo;

    /**
     * Constructor of clone command.
     *
     * @param taskNo The task number to be cloned.
     */
    public CloneCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Clone a task in the tasklist.
     *
     * @param tasks The task to be cloned.
     */
    @Override
    public String execute(TaskList tasks) {
        try {
            Task t = tasks.getTask(taskNo);
            tasks.addTask(t);
            Storage.save(tasks.getTasks());
            return Ui.showCloneTaskMessage(t, tasks.getSize());
        } catch (Exception e) {
            return Ui.showError(e);
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
