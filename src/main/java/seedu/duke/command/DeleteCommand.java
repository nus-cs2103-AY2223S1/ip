package seedu.duke.command;

import seedu.duke.operations.Storage;
import seedu.duke.task.Task;
import seedu.duke.operations.TaskList;
import seedu.duke.operations.Ui;

public class DeleteCommand extends TaskListCommand {

    public DeleteCommand(String cmd) {
        super(cmd);
    }

    @Override
    void specialisedFunction(TaskList tasks, Ui ui, Storage storage, int taskIndex) {
        Task removedTask = tasks.removeTask(taskIndex);
        ui.showRemoveTask(removedTask);
    }
}
