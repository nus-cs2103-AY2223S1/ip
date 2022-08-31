package blink.command;

import blink.Storage;
import blink.TaskList;
import blink.Ui;

/**
 * List command that displays all the tasks within the TaskList.
 */
public class ListCommand extends Command {

    /**
     * Ui will display all the tasks found in the TaskList.
     *
     * @param tasks TaskList object of current Blink object
     * @param ui Ui object of current Blink object
     * @param storage Storage object of current Blink object
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks);
    }

    /**
     * List command does not end the program thus returns false.
     *
     * @return False
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
