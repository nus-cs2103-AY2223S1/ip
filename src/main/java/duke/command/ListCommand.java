package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 *
 * @author Rama Aryasuta Pangestu
 */
public class ListCommand extends Command {
    /**
     * Constructs a command to list all tasks in the task list.
     */
    public ListCommand() {}

    /**
     * Executes the command by printing the list of all tasks in the task list.
     *
     * @param ui       the user interface
     * @param storage  the storage dealing with loading and saving tasks in the save file
     * @param taskList the task list
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        if (taskList.size() > 0) {
            ui.addOutput("Here are the tasks in your list:\n");
            ui.addOutput(taskList.toString());
        } else {
            ui.addOutput("Your list is empty :(\n");
        }
    }

    /**
     * Returns false as this is not an exit command.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
