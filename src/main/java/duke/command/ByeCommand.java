package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents a command to exit Duke.
 *
 * @author Derrick Khoo
 */
public class ByeCommand extends Command {

    /**
     * Returns true if command was to exit Duke.
     * @return true
     */
    @Override
    public boolean isDone() {
        return true;
    }

    /**
     * Handles the command to exit Duke.
     * @param storage  the <code>Storage</code> dealing with loading and saving tasks in a file
     * @param ui       the user interfaces that deals with user inputs
     * @param taskList the list of tasks
     * @throws DukeException if there is an error saving to the file
     */

    @Override
    public void handle(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        ui.line();
        System.out.println("Bye. Hope to see you again soon!");
        ui.line();
        storage.saveData(taskList);
    }
}
