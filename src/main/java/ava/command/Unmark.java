package ava.command;

import ava.Ui;
import ava.util.Storage;
import ava.util.TaskList;

/**
 * Class to represent "Unmark" command.
 */
public class Unmark extends Command {
    private final int num;

    /**
     * The constructor for Unmark command.
     *
     * @param num Index of the specified task.
     */
    public Unmark(int num) {
        this.num = num;
    }

    /**
     * Executes process to mark undone the given task.
     *
     * @param tasks TaskList.
     * @param ui Class to print the ui.
     * @param storage Class to write/read commands from file.
     * @return The response of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markUndone(num);
        storage.write(tasks.getTasks());
        return ui.showUndoneTask(tasks, num);
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
