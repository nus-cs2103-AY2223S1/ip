package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * An extension of the Command class, ListCommand, used to show tasks in taskList.
 */
public class ListCommand extends Command {

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
     * Shows tasks in taskList
     *
     * @param taskList tasklist that contains tasks to be modified, added, or removed
     * @param ui ui that displays results of user commands
     * @param storage storage that saves or loads the taskList
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTasksInList(taskList);
    }
}
