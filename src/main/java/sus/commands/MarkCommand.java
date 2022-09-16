package sus.commands;

import sus.SusException;
import sus.common.Messages;
import sus.storage.StorageFile;
import sus.task.Task;
import sus.task.TaskList;
import sus.ui.TextUi;

/**
 * Marks a task in the task list as done.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks a task as done.\n"
            + "\tEx: " + COMMAND_WORD + " <number>";

    private final int targetIndex;

    /**
     * Constructor.
     *
     * @param targetIndex index of the task to be marked
     */
    public MarkCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(TaskList taskList, TextUi textUi, StorageFile storage) {
        try {
            Task task = taskList.markTask(targetIndex);
            storage.save(taskList);
            return new CommandResult(String.format(Messages.MESSAGE_TASK_UPDATE_STATUS + "\n%s", "done", task));
        } catch (SusException e) {
            return new CommandResult(e.getMessage());
        }
    }
}
