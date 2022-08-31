package ava.task;

import ava.Ui;
import ava.processor.Storage;
import ava.processor.TaskList;

/**
 * Class to represent "Unmark" tasks.
 */
public class Unmark extends Task {
    private int num;

    /**
     * The constructor for Mark task.
     *
     * @param num Index of the specified task.
     */
    public Unmark(int num) {
        super("unmark", false);
        this.num = num;
    }

    /**
     * Executes input list task
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
}
