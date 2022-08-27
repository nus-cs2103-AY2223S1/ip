package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ListItemsCommand implements Command {
    /**
     * {@inheritDoc}
     * Shows contents of itemList to user.
     */
    @Override
    public void execute(TaskList itemList, Ui ui, Storage storage) {
        ui.showToUser(itemList.toString());
    }
}
