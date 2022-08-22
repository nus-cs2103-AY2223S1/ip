package duke.commands;

import duke.ui.Ui;
import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;

public class DeleteCommand extends Command {

    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Task deletedTask = taskList.deleteTask(taskNumber);
        storage.save(taskList);
        ui.printDeleteTask(deletedTask, taskList.getSize());
    }
}
