package ava.command;

import ava.Ui;
import ava.util.Storage;
import ava.util.TaskList;

/**
 * Class to represent "Find" command.
 */
public class Find extends Command {
    protected final String description;

    /**
     * The constructor for Find command.
     *
     * @param description predicate
     */
    public Find(String description) {
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
        return ui.showFindTasks(tasks, description);
    }

    /**
     * Tests if a command is exit.
     *
     * @return False.
     */
    @Override
    public boolean isBye() {
        return false;
    }
}
