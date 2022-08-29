package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * An extension of the Command class, MarkCommand, used to mark tasks as done in taskList.
 */
public class MarkCommand extends Command {

    private int integer;

    /**
     * Constructor for MarkCommand class
     *
     * @param integer integer that determines which item is to be marked as done
     */
    public MarkCommand(int integer) {
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
     * Marks tasks in taskList, saves the taskList to Duke.txt file, and displays Ui to show update to taskList
     *
     * @param taskList tasklist that contains tasks to be marked as done
     * @param ui ui that displays results of marking tasks in taskList
     * @param storage storage that saves the taskList
     * @return string that contains ui message
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markAsDone(integer);
        storage.saveFile(taskList);
        return ui.showMarkAsDone(taskList, integer);
    }
}
