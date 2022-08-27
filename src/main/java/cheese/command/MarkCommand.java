package cheese.command;

import cheese.data.TaskList;
import cheese.exception.CheeseException;
import cheese.storage.Storage;
import cheese.task.Task;
import cheese.ui.Ui;

public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(int givenIndex) {
        taskIndex = givenIndex - 1;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws CheeseException {
        Task taskDone = taskList.markTaskAsDone(taskIndex);
        ui.showMarkTaskAsDone(taskDone);
        storage.save(taskList);
    }
}
