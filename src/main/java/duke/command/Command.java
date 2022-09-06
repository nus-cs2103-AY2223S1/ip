package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;


/**
 * Represents an abstract command type
 */
public abstract class Command {

    /**
     * Execute will add a task into the tasklist, save the new tasklist in storage
     * and print out what was added
     *
     * @param taskList
     * @param ui
     * @param storage
     */
    public abstract String execute(TaskList taskList, TaskList archiveTaskList, Storage storage,
                                   Storage archiveStorage, Ui ui);
}
