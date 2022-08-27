package duke.command;

import duke.DukeException;
import duke.util.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(Storage storage, Ui ui, TaskList taskList, int taskIndex) {
        super(storage, ui, taskList);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() throws DukeException {
        taskList.removeTask(taskIndex);
    }
}
