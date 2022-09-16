package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * DeleteCommand class to delete a task
 */
public class DeleteCommand extends Command {
    private int taskNo;

    /**
     * Constructor of delete command.
     *
     * @param taskNo The task number to be deleted.
     */
    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Deletes task in the tasklist.
     *
     * @param tasks The task to be executed.
     */
    @Override
    public String execute(TaskList tasks) {
        try {
            Task t = tasks.getTask(taskNo);
            tasks.deleteTask(taskNo);
            Storage.save(tasks);
            return Ui.showDeleteTaskMessage(t, tasks.getSize());
        } catch (Exception e) {
            return Ui.showError(e);
        }
    }
}
