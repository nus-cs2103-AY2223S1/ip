package maria.command;

import maria.TaskManager;
import maria.task.Task;

/**
 * Represents the command for marking a task as done.
 */
public class CommandMarkTask extends Command {

    private final int index;

    public CommandMarkTask(int index) {
        this.index = index - 1;
    }

    /**
     * Executes the command.
     * @param taskManager The overall-in-charge for all task related affairs
     * @return The display message for the execution
     */
    @Override
    public String execute(TaskManager taskManager) {

        if (this.index >= taskManager.getTaskList().size() || this.index <= 0) {
            return "Index is invalid.";
        }

        Task task = taskManager.getTaskList().get(this.index);

        task.setIsDone(true);
        taskManager.getTaskList().mutatedTask();

        return "Your task " + task.getName() + " has been completed.";

    }
}
