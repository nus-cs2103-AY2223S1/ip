package blink.command;

import blink.Storage;
import blink.TaskList;
import blink.Ui;

/**
 * Unmark command that will indicate a task as marked
 */
public class UnmarkCommand extends Command {

    private int num;

    /**
     * Constructor for unMark command which takes in the
     * number position for task to unmark in TaskList
     *
     * @param num Position of task to unmark in TaskList
     */
    public UnmarkCommand(int num) {
        this.num = num;
    }

    /**
     * TaskList will unmark the task at specified location, then Ui
     * will display the information afterwards to indicate unmarking
     * has been done which is saved in Storage.
     *
     * @param tasks TaskList object of current Blink object
     * @param ui Ui object of current Blink object
     * @param storage Storage object of current Blink object
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unMark(num);
        storage.save(tasks);
        return ui.unMark(tasks, num);
    }

    /**
     * Unmark command does not end the program thus returns false.
     *
     * @return False
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
