package command;

import exception.DukeException;
import myduke.Storage;
import myduke.TaskList;
import myduke.Ui;

public abstract class Command {

    /**
     * Method that executes the Command.
     *
     * @param tasks   The TaskList instance for the task manager.
     * @param ui      The Ui instance for the task manager.
     * @param storage The Storage instance for the task manager.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
