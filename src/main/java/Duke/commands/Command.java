package Duke.commands;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import java.io.IOException;


/**
 * Signifies a command input by the user.
 */

public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks The task list containing all the tasks before the command is executed.
     * @param ui Provides access to the UI of the program.
     * @param storage Provides access to local storage.
     * @throws IOException when there is an problem with the IO.
     * @throws DukeException when there is inappropriate input or save file issues.
     */

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException;


    /**
     * Checks whether the user has input the bye command.
     *
     * @return false if a bye command is given and tru otherwise
     */

    public boolean isExit() {
        return false;
    }
}
