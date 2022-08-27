package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DeleteCommand implements Command {
    private final String index;

    public DeleteCommand(String index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     * Removes given input index from itemList, and shows the resulting message to the user.
     */
    @Override
    public void execute(TaskList itemList, Ui ui, Storage storage) throws DukeException {
        ui.showToUser(itemList.deleteItem(Integer.parseInt(index)));
    }
}
