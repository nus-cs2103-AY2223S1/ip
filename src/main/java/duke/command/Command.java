package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.storage.Storage;
import duke.task.TaskList;


/**
 * Represents a command made by user.
 *
 * @author Derrick Khoo
 */
public abstract class Command {

    /**
     * Returns a boolean that denotes if Duke should exit or not upon handling this command.
     * @return true if and only if this is a bye command
     */
    public boolean isDone() {
        return false;
    }

    /**
     * Handles the command made by user to Duke.
     * @param storage  the <code>Storage</code> dealing with loading and saving tasks in a file
     * @param ui       the user interfaces that deals with user inputs
     * @param taskList the list of tasks
     * @throws DukeException if there is an error when handling the command
     */
    public void handle(Storage storage, Ui ui, TaskList taskList) throws DukeException {};
}
