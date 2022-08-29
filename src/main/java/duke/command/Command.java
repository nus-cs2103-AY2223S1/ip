package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;

import java.io.IOException;

/**
 * Command is a Command that is executed depending on circumstance.
 *
 * @author John Russell Himawan
 * @version CS2103T AY22/23 Sem 1
 */

public abstract class Command {
    protected boolean isExit;

    /**
     * Constructor for Command.
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * Queries whether the program should be exited.
     *
     * @return The boolean representing whether should exit program.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Outputs the proper response from Duke depending on Command type.
     *
     * @param tasks A TaskList containing the Tasks.
     * @param ui The Ui which handles interactions with the user.
     * @param storage The Storage which handles loading and saving data from the file.
     * @throws DukeException The exception thrown when an action is unauthorized by Duke.
     * @throws IOException The exception thrown when accessing files is incorrect.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;
}
