package command;

import task.Task;
import tasklist.TaskList;
import util.Storage;

/**
 * Represents a command to be executed that adds a task based on input from save file.
 *
 * @author Bryan Lim Jing Xiang
 */
public class AddTaskFromStorageCommand extends Command {
    private final Task taskItem;

    /**
     * @param taskItem Task to be added to internal duke list
     */
    public AddTaskFromStorageCommand(Task taskItem) {
        this.taskItem = taskItem;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList list, Storage storage) {
        list.addTask(this.taskItem);
    }
}
