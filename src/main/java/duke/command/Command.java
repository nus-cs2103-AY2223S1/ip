package duke.command;

import duke.DukeException;
import duke.StorageList;
import duke.Ui;

/**
 * The abstract class for all commands.
 */
public abstract class Command {
    protected boolean isExit = false;

    /**
     * Sets the exit flag.
     *
     * @return true if the exit flag is set, false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes the command.
     *
     * @param ui the user interface
     * @param storageList the storage list
     * @throws DukeException if an error occurs
     */
    public abstract void execute(Ui ui, StorageList storageList) throws DukeException;
}
