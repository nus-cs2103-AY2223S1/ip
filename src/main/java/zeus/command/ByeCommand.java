package zeus.command;

import zeus.Ui;
import zeus.ZeusException;
import zeus.storage.Storage;
import zeus.task.TaskList;

/**
 * Represents a command to exit Zeus.
 *
 * @author Derrick Khoo
 */
public class ByeCommand extends Command {

    /**
     * Returns true if command was to exit Zeus.
     *
     * @return true
     */
    @Override
    public boolean isDone() {
        return true;
    }

    /**
     * Handles the command to exit Zeus.
     *
     * @param storage  the <code>Storage</code> dealing with loading and saving tasks in a file
     * @param ui       the user interfaces that deals with user inputs
     * @param taskList the list of tasks
     * @throws ZeusException if there is an error saving to the file
     */
    @Override
    public String handle(Storage storage, Ui ui, TaskList taskList) throws ZeusException {
        storage.saveData(taskList);
        return "Zeus says:\n" + ui.formatMessage("Bye. Hope to see you again soon!");
    }
}
