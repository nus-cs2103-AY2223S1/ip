package duke.task;

import duke.processor.Storage;
import duke.processor.TaskList;
import duke.Ui;

/**
 * Class to represent "Duke.Task.Delete" tasks.
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
     * Executes input list task
     * @param task
     * @param ui
     */
    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        Task taskDeleted = task.delete(num);
        ui.showDeleteTask(task, taskDeleted);
        storage.write(task.getTasks());
    }
}
