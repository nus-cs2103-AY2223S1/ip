package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * Command is an object that can be executed.
 *
 * @author Lee Ian Ee
 * @version CS2103T AY22/23 Sem 1
 */

public abstract class Command {
    /**
     * Returns the output to be shown to the user after the Command is executed.
     * @param list TaskList containing the list of tasks.
     * @param storage Storage that loads and saves to the save file.
     * @return String to be shown to the user.
     */
    public abstract String execute(TaskList list, Storage storage);
}
