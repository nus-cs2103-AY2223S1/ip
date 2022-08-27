package maria.command;

import maria.TaskManager;
import maria.task.Task;

/**
 * Represents the command for removing a task.
 */
public class CommandRemoveTask extends Command {

    private Task task;

    public CommandRemoveTask(Task task) {
        this.task = task;
    }

    /**
     * Executes the command.
     * @param taskManager The overall-in-charge for all task related affairs
     */
    @Override
    public void execute(TaskManager taskManager) {
        taskManager.getTaskList().remove(this.task);
    }
}
