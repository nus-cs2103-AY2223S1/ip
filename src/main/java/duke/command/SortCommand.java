package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * An extension of the Command class, SortCommand, used to sort tasks in chronologically date order in taskList.
 */
public class SortCommand extends Command {

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
     * Sort tasks in taskList, saves the taskList to Duke.txt file, and displays Ui to show update to taskList.
     *
     * @param taskList tasklist that contains tasks to be sorted
     * @param ui ui that displays results of sorting tasks in taskList
     * @param storage storage that saves the taskList
     * @return string that contains ui message
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.sort();
        storage.saveFile(taskList);
        return ui.showSortMessage(taskList);
    }
}
