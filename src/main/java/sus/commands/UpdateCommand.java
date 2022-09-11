package sus.commands;

import sus.DukeException;
import sus.common.Messages;
import sus.storage.StorageFile;
import sus.task.Task;
import sus.task.TaskList;
import sus.ui.TextUi;

/**
 * Update a task in the task list.
 */
public class UpdateCommand extends Command {

    public static final String COMMAND_WORD = "update";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Update a task's description.\n"
            + "\tEx.: " + COMMAND_WORD + " <number> <new description>";

    private final int targetIndex;
    private final String newDescription;

    /**
     * Constructor .
     *
     * @param targetIndex index of task to update
     * @param newDescription new description to be set
     */
    public UpdateCommand(int targetIndex, String newDescription) {
        this.targetIndex = targetIndex;
        this.newDescription = newDescription;
    }

    @Override
    public CommandResult execute(TaskList taskList, TextUi textUi, StorageFile storage) {
        try {
            Task task = taskList.updateTask(targetIndex, newDescription);
            storage.save(taskList);
            return new CommandResult(String.format(Messages.MESSAGE_UPDATED_DESCRIPTION, task));
        } catch (DukeException e) {
            return new CommandResult(e.getMessage());
        }
    }
}
