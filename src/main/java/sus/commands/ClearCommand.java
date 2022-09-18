package sus.commands;

import sus.SusException;
import sus.common.Messages;
import sus.storage.StorageFile;
import sus.task.TaskList;
import sus.ui.TextUi;

/**
 * Clears the task list
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clears the task list.\n"
            + "\tEx: " + COMMAND_WORD;

    /**
     * Constructor.
     */
    public ClearCommand() {

    }

    @Override
    public CommandResult execute(TaskList taskList, TextUi textUi, StorageFile storage) {
        try {
            taskList.clearTasks();
            storage.save(taskList);
            return new CommandResult(Messages.MESSAGE_TASKS_CLEARED);
        } catch (SusException e) {
            return new CommandResult(e.getMessage());
        }
    }
}
