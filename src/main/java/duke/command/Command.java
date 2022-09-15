package duke.command;

import duke.Storage;
import duke.task.TaskList;

/**
 * Represents a Command
 */
public abstract class Command {

    /**
     * Executes the Command
     */
    public abstract void execute(TaskList taskList, Storage storage);
}
