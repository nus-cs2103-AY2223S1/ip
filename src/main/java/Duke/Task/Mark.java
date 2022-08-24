package Duke.Task;

import Duke.Processor.Storage;
import Duke.Processor.TaskList;
import Duke.UI;

/**
 * Class to represent "Mark" tasks.
 */
public class Mark extends Task {
    int num;

    /**
     * The constructor for Duke.Task.Mark task.
     *
     * @param num Index of the specified task.
     */
    public Mark(int num) {
        super("mark", false);
        this.num = num;
    }

    /**
     * Executes process to mark done the given task.
     *
     * @param tasks TaskList.
     * @param ui Class to print the ui.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        tasks.markDone(num);
        ui.showDoneTask(tasks, num);
        storage.write(tasks.getTasks());
    }
}
