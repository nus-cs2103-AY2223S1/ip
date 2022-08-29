package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * ListCommand is a Command that handles list.
 *
 * @author John Russell Himawan
 * @version CS2103T AY22/23 Sem 1
 */

public class ListCommand extends Command {
    /**
     * Constructor for ListCommand.
     */
    public ListCommand() {

    }

    /**
     * Outputs the current list.
     *
     * @param tasks A TaskList containing the Tasks.
     * @param ui The Ui which handles interactions with the user.
     * @param storage The Storage which handles loading and saving data from the file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayList(tasks);
    }
}
