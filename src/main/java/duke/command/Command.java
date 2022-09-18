package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Class which handles Commands.
 */
public abstract class Command {

    /**
     * Executes the specific command.
     * @param tasks the TaskList with all saved tasks as Task objects
     * @param ui the Ui
     * @param storage the Storage with all saved tasks in String format
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {}

    /**
     * Controls the flow of the while loop. Exits loop when true.
     */
    public abstract boolean isExit();
}
