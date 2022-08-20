package duke.commands;

import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * List all the tasks in the taks list to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all tasks as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public void execute(TaskList taskList, Ui ui, StorageFile storage) {
        ui.showMessages(taskList.toString());
    }
}
