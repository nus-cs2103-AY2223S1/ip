package duke.commands;

import duke.exceptions.InvalidInputException;
import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Delete a task in the task list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a task from the list.\n"
            + "\tEx.: " + COMMAND_WORD + " <number>";

    private final int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, StorageFile storage) throws Exception {
        taskList.deleteTask(taskNumber);
        storage.saveList(taskList);
    }
}
