package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidInputException;

/**
 * Command class is a command that can be executed according to its type.
 *
 * @author Eugene Tan
 */
public abstract class Command {
    private boolean isExit;

    /**
     * Constructor for command that sets isExit to be false by default.
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * Sets isExit to true
     */
    public void setExitToTrue() {
        this.isExit = true;
    }

    /**
     * Returns the boolean status of isExit.
     *
     * @return boolean value of isExit.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Runs the command
     *
     * @param tasks Tasklist containing the tasks
     * @param ui Ui handling the user interaction
     * @param storage Storage to store data
     * @throws InvalidInputException if input is invalid
     */
    public abstract void run(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException;
}
