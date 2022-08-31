package ava.task;

import ava.Ui;
import ava.processor.Storage;
import ava.processor.TaskList;

/**
 * Class to represent "Find" tasks.
 */
public class Find extends Task {
    protected String description;

    /**
     * The constructor for Find task.
     *
     * @param description predicate
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
     * @return The response of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return formatOutput("Here are the matching tasks in your list:",
                tasks.filter((task) -> task.matchKeyword(description)).toStringArray());
    }
}
