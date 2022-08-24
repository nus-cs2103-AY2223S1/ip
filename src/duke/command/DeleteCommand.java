package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    private int indexOfTaskToDelete;

    public DeleteCommand(int indexOfTaskToDelete) {
        this.indexOfTaskToDelete = indexOfTaskToDelete;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        Task taskToDelete = tasks.getTasks().get(indexOfTaskToDelete);
        tasks.deleteTask(indexOfTaskToDelete);
        storage.save(tasks);
        ui.showDeletedTask(taskToDelete, tasks.getNumOfRemainingTasks());
    }

    @Override
    public boolean bye() {
        return false;
    }
}
