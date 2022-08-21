package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Represents a Command
 */
public abstract class Command {

    /**
     * Executes the Command
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
