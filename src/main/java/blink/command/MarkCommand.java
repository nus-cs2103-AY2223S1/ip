package blink.command;

import blink.Storage;
import blink.TaskList;
import blink.Ui;

/**
 * Mark command that will indicate a task as marked
 */
public class MarkCommand extends Command{

    private int num;

    /**
     * Constructor for mark command which takes in the
     * number position for task to mark in TaskList
     *
     * @param num Position of task to mark in TaskList
     */
    public MarkCommand(int num) {
        this.num = num;
    }

    /**
     * TaskList will mark the task at specified location, then Ui
     * will display the information afterwards to indicate marking
     * has been done which is saved in Storage.
     *
     * @param tasks TaskList object of current Blink object
     * @param ui Ui object of current Blink object
     * @param storage Storage object of current Blink object
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.mark(num);
        storage.save(tasks);
        return ui.mark(tasks, num);
    }

    /**
     * Mark command does not end the program thus returns false.
     *
     * @return False
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
