package cwq.command;

import cwq.storage.Storage;
import cwq.task.TasksController;
import cwq.exception.NoSuchTaskException;
/**
 * UnmarkTaskCommand will execute the command of unmarking a task.
 */
public class UnmarkTaskCommand extends Command {

    /**
     * Execute UnmarkTaskCommand
     * @param controller Duke task controller
     * @param taskText content of a task (if any)
     * @param taskTime time of a task (if any)
     * @param taskIndex index of a task (if any)
     * @param keywords keywords for finding (if any)
     * @param storage Duke IO processor
     */
    public String execute(TasksController controller, String taskText, String taskTime, int taskIndex,
                          Storage storage, String ...keywords) {
        assert taskTime.length() == 0 : "The taskTime of should not be used for UnmarkTaskCommand";
        assert taskText.length() == 0 : "The taskText should not be used for UnmarkTaskCommand";
        assert taskIndex != -1 : "The taskIndex should not be empty";
        assert keywords[0].equals("") : "The keywords should not be used for UnmarkTaskCommand";
        String response = "";
        try {
            controller.changeTaskStatus(taskIndex, false);
            response += "Successfully unmarked! You can see it in your task list as follows:\n";
            response += controller.getTask(taskIndex).toString();
        } catch (NoSuchTaskException e) {
            response = "Your target task doesn't exist. Please try again...";
        }
        return response;
    }
}
