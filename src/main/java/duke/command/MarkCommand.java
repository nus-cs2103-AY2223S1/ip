package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.task.Task;
import duke.TaskList;

public class MarkCommand extends Command {
    private int index;
    private boolean isDone;

    public MarkCommand(String indexStr, boolean isDone) throws DukeException {
        super();
        if (indexStr.length() == 0) {
            throw new DukeException("Oops, no task given to mark as done.");
        }
        this.index = Integer.parseInt(indexStr) - 1;
        this.isDone = isDone;
    }

    @Override
    public String executeWithMessage(TaskList tasks) throws DukeException {
        return tasks.markTask(index, true);
    }
}
