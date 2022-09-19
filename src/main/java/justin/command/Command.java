package justin.command;

import justin.*;

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
     * @return The String message of the Ui.
     */
    public abstract String execute(TaskList list, Ui ui, Storage storage) throws DukeException;


    protected boolean isExit = false;

    /**
     * Returns the boolean value isExit in the Command object.
     * @return boolean isExit.
     */

}
