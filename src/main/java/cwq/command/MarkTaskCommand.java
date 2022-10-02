package cwq.command;

import cwq.exception.NoSuchTaskException;
import cwq.storage.Storage;
import cwq.task.TasksController;

/**
 * MarkTaskCommand will execute the command of marking a task.
 */
public class MarkTaskCommand extends Command {
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
        assert taskTime.length() == 0 : "The taskTime of should not be used for MarkTaskCommand";
        assert taskText.length() == 0 : "The taskText should not be used for MarkTaskCommand";
        assert taskIndex != -1 : "The taskIndex should not be empty";
        assert keywords[0].equals("") : "The keywords should not be used for MarkTaskCommand";
        String response = "";
        try {
            controller.changeTaskStatus(taskIndex, true);
            response += "Successfully marked! You can see it in your task list as follows:\n";
            response += controller.getTask(taskIndex).toString();
        } catch (NoSuchTaskException e) {
            response = "Your target task doesn't exist. Please try again...";
        }
        return response;
    }
}
