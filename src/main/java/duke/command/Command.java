package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * Abstract parent class for all commands.
 */
public abstract class Command {
    /**
     * Behaviour of command when run.
     * @param taskList TaskList object of the Duke object for the Command object to use.
     * @param ui Ui object of the Duke object for the Command object to use.
     * @param storage Storage object of the Duke object for the Command object to use.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Whether the Duke object should exit when the Command object is executed.
     * @return Whether the Duke object should exit.
     */
    public abstract boolean isExit();
}
