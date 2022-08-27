package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class MarkCommand implements Command {
    private final String index;

    public MarkCommand(String index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     * Marks task at given index as complete, and shows output to user.
     */
    @Override
    public void execute(TaskList itemList, Ui ui, Storage storage) throws DukeException {
        ui.showToUser(itemList.mark(Integer.parseInt(index)));
    }
}
