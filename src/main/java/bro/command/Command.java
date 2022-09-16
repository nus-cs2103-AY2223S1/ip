package bro.command;

import bro.BroException;
import bro.Storage;
import bro.TaskList;
import bro.Ui;

/**
 * Command class.
 */
public abstract class Command {
    private boolean isExit = false;

    /**
     * Returns the boolean value of the isExit.
     * @return The boolean value.
     */
    public boolean isExit() {
        return this.isExit;
    }
    /**
     * Executes the command according to the input.
     * @param tasklist List of tasks of the user.
     * @param ui To display messages to the user.
     * @param storage Storage of the file.
     * @return the Ui to be displayed.
     * @throws BroException If the input is invalid.
     */
    public abstract String execute(TaskList tasklist, Ui ui, Storage storage) throws BroException;
}
