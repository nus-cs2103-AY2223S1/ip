package cwq.command;

import cwq.storage.Storage;
import cwq.task.Task;
import cwq.task.TasksController;

import java.util.ArrayList;

public class SortTasksCommand extends Command {
    /**
     * Execute MarkTaskCommand
     * @param controller Duke task controller
     * @param taskText content of a task (if any)
     * @param taskTime time of a task (if any)
     * @param taskIndex index of a task (if any)
     * @param keywords keywords for finding (if any)
     * @param storage Duke IO processor
     */
    public String execute(TasksController controller, String taskText, String taskTime, int taskIndex,
                          Storage storage, String ...keywords) {
        assert taskTime.length() == 0 : "The taskTime of should not be used for SortTasksCommand";
        assert taskText.length() == 0 : "The taskText should not be used for SortTasksCommand";
        assert taskIndex == -1 : "The taskIndex should not be used for SortTasksCommand";
        assert keywords[0].equals("") : "The keywords should not be used for SortTasksCommand";
        String response = "";
        ArrayList<Task> sortedTasks = controller.sortTasksByTime();
        response += "Here are your tasks ordered by time:\n";
        response += controller.getTasksString(sortedTasks);
        return response;
    }
}
