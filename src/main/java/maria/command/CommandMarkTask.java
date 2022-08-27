package maria.command;

import maria.TaskManager;
import maria.task.Task;

/**
 * Represents the command for marking a task as done.
 */
public class CommandMarkTask extends Command {

    private Task task;

    public CommandMarkTask(Task task) {
        this.task = task;
    }

    /**
     * Executes the command.
     * @param taskManager The overall-in-charge for all task related affairs
     */
    @Override
    public void execute(TaskManager taskManager) {
        taskManager.getTaskList().get(taskManager.getTaskList().indexOf(this.task)).setIsDone(true);
        taskManager.getTaskList().mutatedTask();
    }
}
