package duke.command;

import duke.DukeException;
import duke.task.Task;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() throws DukeException {
        Task deletedTask = Command.taskList.deleteTask(this.taskIndex);
        Command.ui.printTaskListChange("Noted. I've removed this task:", deletedTask, Command.taskList);
    }
}
