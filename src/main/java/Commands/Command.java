package Commands;

import Duke.DukeException;
import Duke.TaskList;
import Duke.Ui;
import Duke.Storage;

/**
 * Parent class of all Duke Commands
 */
public abstract class Command {
    private boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public void setExit() {
        this.isExit = true;
    }

    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes the corresponding command
     *
     * @param t
     * @param ui
     * @param storage
     * @return string of the executed command
     * @throws DukeException
     */
    public abstract String execute(TaskList t, Ui ui, Storage storage) throws DukeException;
}
