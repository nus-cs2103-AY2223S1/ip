package cwq.command;

import cwq.storage.Storage;
import cwq.task.TasksController;

/**
 * Abstract class Command. All commands will inherit from this class.
 */
public abstract class Command {

    /**
     * Execute Command
     * @param controller Duke task controller
     * @param taskText content of a task (if any)
     * @param taskTime time of a task (if any)
     * @param taskIndex index of a task (if any)
     * @param keywords keywords for finding (if any)
     * @param storage Duke IO processor
     */
    public abstract String execute(TasksController controller, String taskText, String taskTime, int taskIndex,
                                   Storage storage, String ...keywords);
}
