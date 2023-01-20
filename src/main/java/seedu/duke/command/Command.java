package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.operations.Storage;
import seedu.duke.operations.TaskList;
import seedu.duke.operations.Ui;

/**
 * Abstract representation of a Command that is able to
 * perform changes to the TaskList, print messages and
 * update the data file.
 */
public abstract class Command {

    /**
     * Performs the Command's intended function.
     *
     * @param tasks             TaskList of Duke
     * @param ui                Ui of Duke
     * @param storage           Storage of Duke
     * @throws DukeException    Thrown when encountering an error
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Indicates if the Command is the exit command.
     *
     * @return      true only and only if is exit command
     */
    public boolean isExit() {
        return false;
    }
}
