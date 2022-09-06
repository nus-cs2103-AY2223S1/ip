package ava.command;

import ava.Ui;
import ava.util.Storage;
import ava.util.TaskList;

/**
 * Class to represent "Sort" command.
 */
public class Sort extends Command {
    /**
     * Executes process to show the list of tasks sorted by date.
     *
     * @param tasks TaskList
     * @param ui Class to print the ui.
     * @param storage Class to write/read commands from file.
     * @return The response of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showSortedTasks(tasks);
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
