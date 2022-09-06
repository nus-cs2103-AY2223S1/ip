package blink.command;

import blink.Storage;
import blink.TaskList;
import blink.Ui;

/**
 *  Command object that is created when a recognized command is input.
 */
public abstract class Command {
    /**
     * Runs the command to get the desired outcome.
     *
     * @param tasks TaskList object of current Blink object
     * @param ui Ui object of current Blink object
     * @param storage Storage object of current Blink object
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}
