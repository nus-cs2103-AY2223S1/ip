package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * A command that is used to list Tasks.
 *
 * @author Lee Ian Ee
 * @version CS2103T AY22/23 Sem 1
 */

public class ListCommand extends Command {
    /**
     * Returns the toString of list.
     * @param list TaskList containing the list of tasks.
     * @param storage Storage that loads and saves to the save file.
     * @return toString of list.
     */
    @Override
    public String execute(TaskList list, Storage storage) {
        return list.toString();
    }
}
