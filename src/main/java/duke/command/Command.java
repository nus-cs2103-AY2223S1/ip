package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulation of a command.
 *
 * @author Sun Ruoxin
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks the list of tasks
     * @param ui the UI
     * @param storage the storage
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns a boolean value represent whether to exit the programme
     * after the command is executed.
     *
     * @return a boolean value represent whether to exit the programme
     */
    public abstract boolean isExit();
}
