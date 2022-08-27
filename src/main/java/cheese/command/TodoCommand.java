package cheese.command;

import cheese.exception.CheeseException;
import cheese.storage.Storage;
import cheese.ui.Ui;
import cheese.data.TaskList;
import cheese.task.Task;
import cheese.task.Todo;

public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws CheeseException {
        Task addedTask = taskList.add(new Todo(description));
        ui.showAddTask(addedTask, taskList.getSize());
        storage.save(taskList);
    }
}
