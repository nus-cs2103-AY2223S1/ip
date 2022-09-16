package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;


/**
 * Represents an abstract command type
 */
public abstract class Command {

    /**
     * Returns a string after method adds a task into the tasklist,
     * save the new tasklist in storage and print out what was added
     *
     * @param taskList
     * @param archiveTaskList
     * @param storage
     * @param archiveStorage
     * @param ui
     */
    public abstract String execute(TaskList taskList, TaskList archiveTaskList, Storage storage,
                                   Storage archiveStorage, Ui ui);
}
