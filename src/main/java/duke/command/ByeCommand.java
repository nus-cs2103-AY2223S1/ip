package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A Command that is used to exit the program.
 *
 * @author Lee Ian Ee
 * @version CS2103T AY22/23 Sem 1
 */

public class ByeCommand extends Command {
    /**
     * Returns the end message.
     * @param list TaskList containing the list of tasks.
     * @param storage Storage that loads and saves to the save file.
     * @return The end message.
     */
    @Override
    public String execute(TaskList list, Storage storage) {
        return Ui.endMessage();
    }
}
