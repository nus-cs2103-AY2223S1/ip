package ava.task;

import ava.processor.Storage;
import ava.processor.TaskList;
import ava.Ui;

/**
 * Class to represent "Find" tasks.
 */
public class Find extends Task {
    protected String description;

    /**
     * The constructor for Find task.
     *
     * @param description Task description.
     */
    public Find(String description) {
        super("find", false);
        this.description = description;
    }

    /**
     * Executes process of find task.
     *
     * @param tasks TaskList.
     * @param ui Class to print the ui.
     * @param storage Class to represent the storage.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFindDetails(tasks, this.description);
    }
}
