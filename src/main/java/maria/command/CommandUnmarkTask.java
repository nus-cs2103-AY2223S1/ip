package maria.command;

import maria.TaskManager;
import maria.task.Task;

/**
 * Represents the command for unmarking a task as not done.
 */
public class CommandUnmarkTask extends Command {

    private final int index;

    public CommandUnmarkTask(int index) {
        this.index = index - 1;
    }

    /**
     * Executes the command.
     * @param taskManager The overall-in-charge for all task related affairs
     * @return The display message for the execution
     */
    @Override
    public String execute(TaskManager taskManager) {

        Task task = taskManager.getTaskList().get(this.index);

        taskManager.getTaskList().get(taskManager.getTaskList().indexOf(task)).setIsDone(false);
        taskManager.getTaskList().mutatedTask();

        return "Your task " + task.getName() + " has been un-completed.";

    }
}
