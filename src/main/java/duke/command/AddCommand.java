package duke.command;

import duke.DukeException;
import duke.task.Task;

public class AddCommand extends Command {
    private Task task;

    AddCommand(Task task) {
        super.isExit = false;
        this.task = task;
    }

    @Override
    public void execute() throws DukeException {
        Command.taskList.add(this.task);
        Command.storage.save(this.task);
        Command.ui.displayAddTaskMessage(this.task, Command.taskList.size());
    }
}
