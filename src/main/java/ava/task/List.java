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
     * @return The response of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() < 1) {
            return "No tasks yet!";
        } else {
            return formatOutput("Here are the tasks in your list:", tasks.toStringArray())
                    + "\n\nJiayous!";
        }
    }
}
