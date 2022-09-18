package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.operations.Storage;
import seedu.duke.task.Task;
import seedu.duke.operations.TaskList;
import seedu.duke.operations.Ui;

/**
 * Command that handles the "delete" user input.
 * Deletes a Task from TaskList.
 */
public class DeleteCommand extends TaskListCommand {

    /**
     * Constructor for DeleteCommand
     *
     * @param cmd   User input
     */
    public DeleteCommand(String cmd) {
        super(cmd);
    }

    @Override
    void specialisedFunction(TaskList tasks, Ui ui, Storage storage, int taskIndex){
        assert(taskIndex <= tasks.numOfTasks());
        Task removedTask = tasks.removeTask(taskIndex);
        ui.appendMessage(ui.getRemovedTaskMessage(removedTask));
    }
}
