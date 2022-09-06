package ava.command;

import ava.Ui;
import ava.util.Storage;
import ava.util.TaskList;

/**
 * Class to represent "Mark" command.
 */
public class Mark extends Command {
    private final int num;

    /**
     * The constructor for Mark command.
     *
     * @param num Index of the specified task.
     */
    public Mark(int num) {
        this.num = num;
    }

    /**
     * Executes process to mark done the given task.
     *
     * @param tasks TaskList.
     * @param ui Class to print the ui.
     * @param storage Class to read/write to file.
     * @return The response of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markDone(num);
        storage.write(tasks.getTasks());
        return ui.showDoneTask(tasks, num);
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
