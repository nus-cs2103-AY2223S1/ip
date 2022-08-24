package duke.task;

import duke.processor.Storage;
import duke.processor.TaskList;
import duke.Ui;

/**
 * Class to represent "Delete" tasks.
 */
public class Delete extends Task {
    int num;

    /**
     * The constructor for Duke.Task.Delete task
     */
    public Delete(int num) {
        super("delete", false);
        this.num = num;
    }

    /**
     * Executes process to delete a specific task.
     *
     * @param tasks TaskList.
     * @param ui Class to print the ui.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskDeleted = tasks.delete(num);
        ui.showDeleteTask(tasks, taskDeleted);
        storage.write(tasks.getTasks());
    }
}
