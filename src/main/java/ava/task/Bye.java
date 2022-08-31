package ava.task;

import ava.Ui;
import ava.processor.Storage;
import ava.processor.TaskList;

/**
 * Class to represent "Bye" tasks.
 */
public class Bye extends Task {
    /**
     * The constructor for Bye task.
     */
    public Bye() {
        super("bye", false);
        this.isBye = true;
    }

    /**
     * Cleans up the UI and writes to disk.
     *
     * @param tasks TaskList.
     * @param storage Class to write/read commands from file.
     * @param ui Class to print the ui.
     * @return The response of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printExit();
    }
}
