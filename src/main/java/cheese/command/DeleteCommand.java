package cheese.command;

import cheese.data.TaskList;
import cheese.exception.CheeseException;
import cheese.storage.Storage;
import cheese.task.Task;
import cheese.ui.Ui;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int givenIndex) {
        taskIndex = givenIndex - 1;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws CheeseException {
        Task deletedTask = taskList.delete(taskIndex);
        ui.showDeleteTask(deletedTask, taskList.getSize());
        storage.save(taskList);
    }
}
