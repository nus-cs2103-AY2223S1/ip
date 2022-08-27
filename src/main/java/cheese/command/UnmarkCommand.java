package cheese.command;

import cheese.data.TaskList;
import cheese.exception.CheeseException;
import cheese.storage.Storage;
import cheese.task.Task;
import cheese.ui.Ui;

public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int givenIndex) {
        taskIndex = givenIndex - 1;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws CheeseException {
        Task taskNotDone = taskList.markTaskAsNotDone(taskIndex);
        ui.showMarkTaskAsNotDone(taskNotDone);
        storage.save(taskList);
    }
}
