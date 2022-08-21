package duke.commands;

import duke.DukeException;
import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Delete a task in the task list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a task from the list.\n"
            + "\tEx.: " + COMMAND_WORD + " <number>";

    private final int targetIndex;

    public DeleteCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, StorageFile storage) throws DukeException {
        taskList.deleteTask(targetIndex);
        storage.save(taskList);
    }
}
