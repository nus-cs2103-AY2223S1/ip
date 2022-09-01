package duke.commands;

import duke.storage.Storage;
import duke.task.List;
import duke.ui.Ui;

/**
 * Lists all the current tasks.
 */
public class ListCommand extends Command {

    public static final String LIST_COMMAND = "list";

    public static final String MESSAGE_SUCCESS = "Aight. Here is your complete tasks.\n";

    public ListCommand() {

    }

    @Override
    public void execute(List tasks, Ui ui, Storage storage) {
        ui.showToUser(MESSAGE_SUCCESS);
        ui.showToUserAsIndexedList(tasks.getTaskList());
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
