package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.task.Task;
import duke.TaskList;

public class MarkCommand extends Command {
    private int index;
    private boolean isDone;

    public MarkCommand(int index, boolean isDone) {
        super();
        this.index = index;
        this.isDone = isDone;
    }

    @Override
    public String executeWithMessage(TaskList tasks) throws DukeException {
        return tasks.markTask(index, true);
    }
}
