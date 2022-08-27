package command;

import task.Task;
import tasklist.TaskList;
import util.Storage;

public class AddTaskFromStorageCommand extends Command {
    private final Task taskItem;

    public AddTaskFromStorageCommand(Task taskItem) {
        this.taskItem = taskItem;
    }

    @Override
    public void execute(TaskList list, Storage storage) {
        list.addTask(this.taskItem);
    }
}
