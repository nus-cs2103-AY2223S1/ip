package Duke.Task;

import Duke.Processor.Storage;
import Duke.Processor.TaskList;
import Duke.UI;

/**
 * Class to represent "Duke.Task.List" tasks.
 */
public class List extends Task {
    /**
     * The constructor for Duke.Task.List task
     */
    public List() {
        super("list", false);
    }

    /**
     * Executes input list task
     * @param task
     * @param ui
     */
    @Override
    public void execute(TaskList task, UI ui, Storage storage) {
        ui.showListDetails(task);
    }
}
