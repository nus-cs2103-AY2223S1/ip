package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * An extension of the Command class, DeleteCommand, used to delete tasks to taskList.
 */
public class DeleteCommand extends Command {

    private int integer;

    /**
     * Constructor for DeleteCommand class
     *
     * @param integer integer that determines which item is to be deleted
     */
    public DeleteCommand(int integer) {
        this.integer = integer;
    }

    /**
     * Returns false to indicate this command does not cause Duke to exit
     *
     * @return the boolean false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Removes tasks from taskList, saves the taskList to Duke.txt file, and displays Ui to show update to taskList.
     *
     * @param taskList tasklist that tasks are removed from
     * @param ui ui that displays results of user commands
     * @param storage storage that saves the taskList
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskDeleted(taskList, integer - 1);
        taskList.remove(integer);
        storage.saveFile(taskList);

    }
}
