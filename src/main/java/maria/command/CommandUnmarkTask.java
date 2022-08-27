package maria.command;

import maria.TaskManager;
import maria.task.Task;

/**
 * Represents the command for unmarking a task as not done.
 */
public class CommandUnmarkTask extends Command {

    private Task task;

    public CommandUnmarkTask(Task task) {
        this.task = task;
    }

    /**
     * Executes the command.
     * @param taskManager The overall-in-charge for all task related affairs
     */
    @Override
    public void execute(TaskManager taskManager) {
        taskManager.getTaskList().get(taskManager.getTaskList().indexOf(this.task)).setIsDone(false);
        taskManager.getTaskList().mutatedTask();
    }
}
