package duke;

import duke.exceptions.DukeException;

public class DeleteCommand extends Command {

    private int deleteTask;

    public DeleteCommand(int deleteTask) {
        this.deleteTask = deleteTask;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws DukeException {
        Task success = taskList.deleteTask(this.deleteTask);
        ui.showDeleteSuccess(success, taskList.numOfTask());
    }
}
