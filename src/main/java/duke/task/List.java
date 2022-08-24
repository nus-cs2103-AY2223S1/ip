package duke.task;

import duke.Ui;
import duke.processor.Storage;
import duke.processor.TaskList;

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
    public void execute(TaskList task, Ui ui, Storage storage) {
        ui.showListDetails(task);
    }
}
