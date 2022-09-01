package maria.command;

import maria.TaskManager;
import maria.task.Task;

import java.util.ArrayList;
import java.util.List;

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
        } else {
            // Converts the task list to a multiline string for display
            return "Your tasks are \n" + tasks.stream()
                    .map(task -> "- " + task)
                    .reduce((acc, str) -> acc + "\n" + str).get();
        }


    }
}
