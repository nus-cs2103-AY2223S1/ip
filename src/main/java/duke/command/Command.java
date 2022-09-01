package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A generic template for commands
 *
 * @author benjytan45678
 * @version 0.1
 */
public abstract class Command {
    private boolean isExit = false;

    /**
     * Return status of program
     */
    public boolean isExit() {

        return this.isExit;
    }

    /**
     * Set status of program to finished
     */
    public void setExit() {

        this.isExit = true;
    }

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
