package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;


/**
 * Represents a command that is used to list out the tasks within a tasklist.
 */
public class ListCommand extends Command {

    /**
     * Returns a string after method list all tasks in the tasklist through
     * tasklist, ui and storage.
     *
     * @param taskList
     * @param archiveTaskList
     * @param storage
     * @param archiveStorage
     * @param ui
     */
    @Override
    public String execute(TaskList taskList, TaskList archiveTaskList, Storage storage,
                          Storage archiveStorage, Ui ui) {
        return ui.list(taskList);
    }
}
