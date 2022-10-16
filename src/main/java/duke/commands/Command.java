package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Represents a command given to duke
 */
public abstract class Command {

    /**
     * Executes the given command
     * @param taskList list of tasks
     * @param ui duke ui
     * @param storage storage file
     * @return
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);



}