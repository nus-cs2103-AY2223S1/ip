package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * An abstract class that represents any command
 * being input by the user.
 * @author Justin Cheng.
 */
public abstract class Command {
    /**
     * Encapsulates actions taken when
     * a command is called.
     * @param list The TaskList to carry out operations.
     * @param ui The Ui to send outputs.
     * @param storage The Storage to save changes.
     */
    public abstract void execute(TaskList list, Ui ui, Storage storage);

    protected boolean isExit = false;

    /**
     * Returns the boolean value isExit in the Command object.
     * @return boolean isExit.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
