package cwq.command;

import cwq.storage.Storage;
import cwq.task.TasksController;

/**
 * ExitCommand class will execute the exit command
 */
public class ExitCommand extends Command {

    /**
     * Execute ExitCommand
     * @param controller Duke task controller
     * @param taskText content of a task (if any)
     * @param taskTime time of a task (if any)
     * @param taskIndex index of a task (if any)
     * @param keywords keywords for finding (if any)
     * @param storage Duke IO processor
     */
    public String execute(TasksController controller, String taskText, String taskTime, int taskIndex,
                          Storage storage, String ...keywords) {
        assert taskTime.length() == 0 : "The taskTime of should not be used for ExitCommand";
        assert taskText.length() == 0 : "The taskText should not be used for ExitCommand";
        assert taskIndex == -1 : "The taskIndex should not be used for ExitCommand";
        assert keywords[0].equals("") : "The keywords should not be used for ExitCommand";
        boolean saved = storage.save(controller.getTasks());
        assert saved : "File saving should be successful";
        return "Bye. Hope to see you soon!";
    }
}
