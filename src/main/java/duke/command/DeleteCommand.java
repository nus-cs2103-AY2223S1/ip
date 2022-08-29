package duke.command;

import duke.operations.Storage;
import duke.task.Task;
import duke.operations.TaskList;
import duke.operations.Ui;

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
