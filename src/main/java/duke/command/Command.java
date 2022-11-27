package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents commands for duke to execute actions.
 */
public interface Command {

    /**
     * Executes this command.
     *
     * @param tasks TaskList which contains all the tasks Duke currently has.
     * @param storage Storage created when starting Duke.
     */
    String execute(TaskList tasks, Storage storage);

}
