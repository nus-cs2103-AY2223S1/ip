package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.TaskList;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public String executeWithMessage(TaskList tasks) throws DukeException {
        return tasks.deleteTask(index);
    }
}