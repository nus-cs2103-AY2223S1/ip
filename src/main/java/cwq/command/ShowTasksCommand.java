package cwq.command;

import cwq.storage.Storage;
import cwq.task.TasksController;

/**
 * ShowTasksCommand will execute the command of showing all tasks.
 */
public class ShowTasksCommand extends Command {

    /**
     * Execute ShowTasksCommand
     * @param controller Duke task controller
     * @param taskText content of a task (if any)
     * @param taskTime time of a task (if any)
     * @param taskIndex index of a task (if any)
     * @param keywords keywords for finding (if any)
     * @param storage Duke IO processor
     */
    public String execute(TasksController controller, String taskText, String taskTime, int taskIndex,
                          Storage storage, String ...keywords) {
        assert taskTime.length() == 0 : "The taskTime of should not be used for ShowTaskCommand";
        assert taskText.length() == 0 : "The taskText should not be used for ShowTaskCommand";
        assert taskIndex == -1 : "The taskIndex should not be used for ShowTaskCommand";
        assert keywords[0].equals("") : "The keywords should not be used for ShowTaskCommand";
        return controller.getTasksString(controller.getTasks());
    }
}
