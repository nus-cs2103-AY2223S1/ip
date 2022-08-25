package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * This abstract class provides the skeletal implementation of a user command
 * and should be the superclass of all user commands.
 */
public abstract class Command {

    /**
     * Executes this Command with the specified TaskList, Ui, and Storage parameters.
     *
     * @param taskList the specified TaskList object.
     * @param ui the specified Ui object.
     * @param storage the specified Storage object.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * An abstract method to be overriden by every Command subclasses which indicating
     * whether the Command is an exit call.
     * @return true if the Command is an exit call.
     */
    public abstract boolean isExit();
}
