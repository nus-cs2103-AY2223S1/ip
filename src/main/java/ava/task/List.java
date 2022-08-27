package ava.task;

import ava.Ui;
import ava.processor.Storage;
import ava.processor.TaskList;

/**
 * Class to represent "List" tasks.
 */
public class List extends Task {
    /**
     * The constructor for List task.
     */
    public List() {
        super("list", false);
    }

    /**
     * Executes process to show the list of tasks.
     *
     * @param tasks TaskList
     * @param ui Class to print the ui.
     * @param storage Class to write/read commands from file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showListDetails(tasks);
    }
}
