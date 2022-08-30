package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The abstract class command which recognizes the users input.
 *
 * @author Leong Jia Hao Daniel
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param ui The ui class which handles the user interface.
     * @param storage The storage class which deals with the file.
     * @param taskList The tasklist that stores the tasks.
     * @return The String that duke says.
     * @throws DukeException throws if there is an error.
     */
    public abstract String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException;

    /**
     * Returns true if the command is an ExitCommand.
     *
     * @return true or false depending on the command.
     */
    public abstract boolean isExit();
}
