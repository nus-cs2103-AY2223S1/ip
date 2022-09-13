package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

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
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * @return boolean of whether the app should stop running.
     */
    public abstract boolean isExit();

    /**
     * @param o Other object we are comparing with
     * @return whether each objects are of the same type
     */
    @Override
    public abstract boolean equals(Object o);
}
