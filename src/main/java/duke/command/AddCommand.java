package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.TaskList;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    @Override
    public String executeWithMessage(TaskList tasks) throws DukeException {
        return tasks.addTask(this.task);
    }

}
