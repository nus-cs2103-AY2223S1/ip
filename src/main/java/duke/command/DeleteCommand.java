package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.TaskList;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(String indexStr) throws DukeException{
        super();
        if (indexStr.length() == 0) {
            throw new DukeException("Oops, no task given to delete.");
        }
        this.index = Integer.parseInt(indexStr) - 1;
    }

    @Override
    public String executeWithMessage(TaskList tasks) throws DukeException {
        return tasks.deleteTask(index);
    }
}