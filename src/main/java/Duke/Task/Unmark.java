package Duke.Task;

import Duke.Processor.Storage;
import Duke.Processor.TaskList;
import Duke.UI;

/**
 * Class to represent "Unmark" tasks.
 */
public class Unmark extends Task {
    int num;

    /**
     * The constructor for Duke.Task.Mark task.
     *
     * @param num Index of the specified task.
     */
    public Unmark(int num) {
        super("unmark", false);
        this.num = num;
    }

    /**
     * Executes input list task
     * @param task
     * @param ui
     */
    @Override
    public void execute(TaskList task, UI ui, Storage storage) {
        task.markUndone(num);
        ui.showUndoneTask(task, num);
        storage.write(task.getTasks());
    }
}
