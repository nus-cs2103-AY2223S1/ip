package Duke.Task;

import Duke.Processor.Storage;
import Duke.Processor.TaskList;
import Duke.UI;

/**
 * Class to represent "List" tasks.
 */
public class List extends Task {
    /**
     * The constructor for Duke.Task.List task.
     */
    public List() {
        super("list", false);
    }

    /**
     * Executes process to show the list of tasks.
     *
     * @param tasks TaskList
     * @param ui Class to print the ui.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showListDetails(tasks);
    }
}
