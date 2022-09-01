package maria.command;

import maria.TaskManager;
import maria.task.Task;

/**
 * Represents the command for removing a task.
 */
public class CommandRemoveTask extends Command {

    private final int index;

    public CommandRemoveTask(int index) {
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

        String rtn = "Your task " + task.getName() + " has been removed.";
        taskManager.getTaskList().remove(task);
        return rtn;

    }
}
