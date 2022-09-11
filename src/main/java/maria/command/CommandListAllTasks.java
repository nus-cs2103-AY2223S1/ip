package maria.command;

import java.util.List;

import maria.TaskManager;
import maria.task.Task;

/**
 * Lists all the tasks that has been added.
 */
public class CommandListAllTasks extends Command {

    /**
     * Executes the command.
     *
     * The command lists all the tasks.
     *
     * @param taskManager The overall-in-charge for all task related affairs
     * @return The display message for the execution
     */
    @Override
    public String execute(TaskManager taskManager) {

        List<Task> tasks = taskManager.getTaskList();

        if (tasks.size() == 0) {
            return "There are no tasks available.";
        }

        // Converts the task list to a multiline string for display
        StringBuilder result = new StringBuilder("Your tasks are \n");

        for (int i = 0; i < tasks.size(); ++i) {
            result.append(i + 1).append(". ")
                    .append(tasks.get(i).toString())
                    .append(i == tasks.size() - 1 ? "" : "\n");
        }

        return result.toString();
    }
}
