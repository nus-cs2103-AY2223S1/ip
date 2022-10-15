package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class UnknownCommand extends Command {

    /**
     * Sends a message to the user due to an unknown command being used.
     *
     * @param tasks The <code>TaskList</code> object containing all stored tasks.
     * @param ui The <code>Ui</code> object
     * @param storage The database object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sendMessage("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
