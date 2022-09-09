package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents the command for asking Duke to list its tasks in the TaskList.
 */
public class ListCommand implements Command {

    /**
     * Executes the command by retrieving the list from Duke's TaskList and printing it.
     *
     * @param tasks TaskList which contains all the tasks Duke currently has.
     * @param storage Storage created when starting Duke.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.list();
    }
}
