package command;

import exception.KobaException;
import mykoba.Storage;
import mykoba.TaskList;
import mykoba.Ui;

/**
 * This class encapsulates the idea of a command.
 */
public abstract class Command {

    /**
     * Method that executes the Command.
     *
     * @param tasks   The TaskList instance for the task manager.
     * @param ui      The Ui instance for the task manager.
     * @param storage The Storage instance for the task manager.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws KobaException;
}
