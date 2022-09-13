package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;

/**
 * Representation of a command passed to Duke to be processed.
 */
public abstract class Command {

    private boolean isExit = false;

    /**
     * Change if the current command is an exit command.
     */
    public void toggleExit() {

        this.isExit = !isExit;
    }

    /*
     * Return if Command signals exit.
     * @return isExit status of Command
     */
    public Boolean isExit() {

        return this.isExit;
    }

    public abstract String execute(Ui ui, TaskList taskList, Storage storage) throws DukeException;
}
