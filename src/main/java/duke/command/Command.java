package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;

public abstract class Command {

    private boolean isExit = false;

    /*
     * Toggle Command to exit for Duke to stop scanning for next line.
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

    public void execute(Ui ui, TaskList taskList, Storage storage) throws DukeException {}
}
