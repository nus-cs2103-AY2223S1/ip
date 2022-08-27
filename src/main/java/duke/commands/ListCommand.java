package duke.commands;

import duke.common.Messages;
import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * List all the tasks in the task list to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all tasks as a list with index numbers.\n"
            + "\tEx.: " + COMMAND_WORD;

    @Override
    public CommandResult execute(TaskList taskList, TextUi textUi, StorageFile storage) {
        return new CommandResult(Messages.MESSAGE_TASK_LIST + "\n" + taskList);
    }
}
