package duke.command;

import duke.DukeException;
import duke.util.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class ChangeStatusCommand extends Command {
    int taskIndex;
    boolean isDone;

    public ChangeStatusCommand(Storage storage, Ui ui, TaskList taskList, int taskIndex, boolean isDone) {
        super(storage, ui, taskList);
        this.taskIndex = taskIndex;
        this.isDone = isDone;
    }

    @Override
    public void execute() throws DukeException {
        taskList.changeTaskStatus(taskIndex, isDone);
    }
}