package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Abstract Class that is parent class of AddCommand, DeleteCommand,
 * ExitCommand, ListCommand, MarkCommand and UnmarkCommand.
 */
public abstract class Command {

    /**
     * @param tasks TaskList of all Duke tasks.
     * @param ui Ui object for decorative purposes.
     * @param storage Storage of app information.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * @return boolean of whether the app should stop running.
     */
    public abstract boolean isExit();
}
