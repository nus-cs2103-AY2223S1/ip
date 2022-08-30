package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a Command object that is specified by the user.
 */
public abstract class Command {
    protected boolean isExit = false;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if user exits the loop.
     *
     * @return true if loop is exited else false.
     */
    public boolean isExit() {
        return isExit;
    }

    protected void displayCommand(Ui ui, String header, Object body, String footer) {
        ui.show(header);
        ui.show(body);
        ui.show(footer);
    }
}
