package duke.command;

import duke.exception.DukeException;
import duke.exception.FileDoesNotExistException;
import duke.exception.StorageOperationException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command {
    private Task taskToAdd;

    public AddCommand(Task task) {
        taskToAdd = task;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        tasks.addTask(taskToAdd);
        storage.save(tasks);
        ui.showAddTask(taskToAdd, tasks.getNumOfRemainingTasks());
    }

    @Override
    public boolean bye() {
        return false;
    }
}
