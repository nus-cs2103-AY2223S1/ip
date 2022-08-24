package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidIndexException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {

    private boolean toMark;
    private int indexOfTaskToMark;

    public MarkCommand(boolean toMark, int indexOfTaskToDelete) {
        this.toMark = toMark;
        this.indexOfTaskToMark = indexOfTaskToDelete;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        if (indexOfTaskToMark <= 0 || indexOfTaskToMark > tasks.getNumOfRemainingTasks()) {
            throw new InvalidIndexException();
        }

        Task taskToMark = tasks.getTasks().get(indexOfTaskToMark);
        if (toMark) {
            tasks.markTask(indexOfTaskToMark);
        } else {
            tasks.unmarkTask(indexOfTaskToMark);
        }
        storage.save(tasks);
        ui.showTask(taskToMark);
    }

    @Override
    public boolean bye() {
        return false;
    }
}
